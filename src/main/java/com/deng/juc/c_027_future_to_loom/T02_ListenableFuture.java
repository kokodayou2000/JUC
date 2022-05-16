package com.deng.juc.c_027_future_to_loom;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.Executors;

public class T02_ListenableFuture {
    public static void main(String[] args) {
        ListeningExecutorService service =
        MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<Integer> future = service.submit(()->{
           return 8;
        });

        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
            throwable.printStackTrace();
            }
        },service);

        service.shutdown();

    }
}
