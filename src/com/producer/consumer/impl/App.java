package com.producer.consumer.impl;

import com.custom.blocking.queue.impl.MyBlockingQueueImpl;

public class App 
{
	public static void main(String[] args) 
	{
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;

        MyBlockingQueueImpl<Integer> queue = new MyBlockingQueueImpl<>(BOUND);

        for (int i = 0; i < N_PRODUCERS; i++) {
            new Thread(new NumberProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new NumberConsumer(queue, poisonPill)).start(); 
        }
    }
}
