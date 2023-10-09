package idv.sheng.collection_observer_dp;

import java.util.concurrent.Flow;

public class CollectionSubscriber implements Flow.Subscriber<String> {
    private Flow.Subscription subscription;
    private int count;

    public CollectionSubscriber(int count) {
        this.count = count;
    }
    @Override
    public void onSubscribe (Flow. Subscription subscription) {
        this.subscription = subscription;
        /* 订阅时请求第一组 */
        int requestCount = Math.min(10, count);
        count -= requestCount;
        subscription.request(requestCount);
    }

    @Override
    public void onNext (String item) {
        System.out.println(Thread.currentThread().getName() + "-onNext: " + item);
    }
    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError: " + throwable);
    }
    @Override
    public void onComplete() {
        System.out.println("onComplete...");
    }
}