package com.company;

import rx.Observable;
import rx.Observer;
import rx.internal.util.InternalObservableUtils;
import rx.observables.AsyncOnSubscribe;

import java.util.Date;

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/09/16.
 */
public class MainRx {

    public static void main(String[] args) {
        hello("Pippo", "Pluto");
    }

    public static void hello(String... names) {
        Observable<String> from = Observable.from(names);
        from.subscribe(s -> {
            System.out.println("Hello 1" + s + "!");
        });
        from.subscribe(s -> {
            System.out.println("Hello 2" + s + "!");
        });
        from.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("OnNext " + s);
            }
        });

        Observable<Integer> asyncObs = Observable.create(new AsyncOnSubscribe<String, Integer>() {
            /**
             * Executed once when subscribed to by a subscriber (via {@link #call(Subscriber)})
             * to produce a state value. This value is passed into {@link #next(Object, long, Observer)
             * next(S state, Observer <T> observer)} on the first iteration. Subsequent iterations of
             * {@code next} will receive the state returned by the previous invocation of {@code next}.
             *
             * @return the initial state value
             */
            @Override
            protected String generateState() {
                return "GENERATED STATE " + new Date();
            }

            /**
             * Called to produce data to the downstream subscribers. To emit data to a downstream subscriber
             * call {@code observer.onNext(t)}. To signal an error condition call
             * {@code observer.onError(throwable)} or throw an Exception. To signal the end of a data stream
             * call {@code observer.onCompleted()}. Implementations of this method must follow the following
             * rules.
             * <p>
             * <ul>
             * <li>Must not call {@code observer.onNext(t)} more than 1 time per invocation.</li>
             * <li>Must not call {@code observer.onNext(t)} concurrently.</li>
             * </ul>
             * <p>
             * The value returned from an invocation of this method will be passed in as the {@code state}
             * argument of the next invocation of this method.
             *
             * @param state     the state value (from {@link #generateState()} on the first invocation or the
             *                  previous invocation of this method.
             * @param requested the amount of data requested. An observable emitted to the observer should not
             *                  exceed this amount.
             * @param observer  the observer of data emitted by
             * @return the next iteration's state value
             */
            @Override
            protected String next(String state, long requested, Observer<Observable<? extends Integer>> observer) {
                return state + " " + requested + " " + observer;
            }
        });
        asyncObs.startWith(5);
        asyncObs.subscribe(s -> {
            System.out.println("asyncObs " + s + "!");
        });


    }
}
