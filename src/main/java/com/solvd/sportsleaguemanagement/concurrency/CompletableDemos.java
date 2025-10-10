package com.solvd.sportsleaguemanagement.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableDemos {
    private static final Logger LOGGER = LogManager.getLogger(CompletableDemos.class);

    public static void runBasicPipeline() {
        CompletableFuture.supplyAsync(() -> {
                    sleep(300);
                    return 42;
                }).thenApply(x -> x * 2)
                .thenApply(x -> "Result = " + x)
                .thenAccept(LOGGER::info)
                .join();
    }

    public static void runCombineExample() {
        var a = CompletableFuture.supplyAsync(() -> { sleep(200); return 10; });
        var b = CompletableFuture.supplyAsync(() -> { sleep(250); return 5; });

        a.thenCombine(b, Integer::sum)
                .thenApply(sum -> "Sum = " + sum)
                .thenAccept(LOGGER::info)
                .join();
    }

    public static void runWithTimeoutAndFallback() {
        CompletableFuture.supplyAsync(() -> { sleep(1200); return "slow"; })
                .orTimeout(700, TimeUnit.MILLISECONDS)
                .exceptionally(ex -> "fallback")
                .thenAccept(s -> LOGGER.info("Completed with: {}", s))
                .join();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
