package idv.sheng;


import idv.sheng.collection_observer_dp.CollectionPublisher;
import idv.sheng.collection_observer_dp.CollectionSubscriber;
import idv.sheng.count_observer_dp.CountPublisher;
import idv.sheng.count_observer_dp.CountSubscriber;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //countObserverDpTest();
        collectionObserverDpTest();
    }

    public static void countObserverDpTest() throws InterruptedException {
        CountPublisher publisher = new CountPublisher(500);
        CountSubscriber subscriber = new CountSubscriber(10);
        publisher.subscribe(subscriber);
        Thread.currentThread().join();
    }

    public static void collectionObserverDpTest() throws InterruptedException {
        CollectionPublisher publisher = new CollectionPublisher(500, Arrays.asList(
                "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten", "eleven",
                "twelve", "thirteen", "fourteen", "fifteen"
        ));
        CollectionSubscriber subscriber = new CollectionSubscriber(10);
        publisher.subscribe(subscriber);
        publisher.subscribe(new CollectionSubscriber(10));
        Thread.currentThread().join();
    }
}