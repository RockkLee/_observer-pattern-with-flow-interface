package idv.sheng.collection_observer_dp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class CollectionPublisher implements Flow.Publisher<String> {
    private int count = 0;// 需要的计数值
    private List<String> dataContainer;
    private final long interval;//发送间隔
    private boolean isCanceled; // 是否被取消

    public CollectionPublisher(long interval, List<String> dataContainer) {
        this.interval = interval;
        this.dataContainer = dataContainer;
        isCanceled = false;
    }

    @Override
    public void subscribe (Flow.Subscriber<? super String> subscriber) {
        new Thread (() -> {
        try {
            subscriber.onSubscribe(new CollectionSubscription());
            for (int i = 0; i < count; i++) {
                if (isCanceled)
                    subscriber.onComplete();
                subscriber.onNext(this.dataContainer.get(i));
                Thread.sleep(interval);
            }
            subscriber.onComplete();
        } catch (Exception e) { subscriber.onError(e);
        }
        }).start();
    }

    private class CollectionSubscription implements Flow.Subscription {
        //設定要從dataContainer讀多少筆資料?
        @Override
        public void request(long n) {
            count = Long.valueOf(n).intValue();
        }
        @Override
        public void cancel() {
            isCanceled = true;
        }
    }
}
