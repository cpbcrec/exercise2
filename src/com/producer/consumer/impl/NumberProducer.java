package com.producer.consumer.impl;

import java.util.concurrent.ThreadLocalRandom;
import com.custom.blocking.queue.impl.MyBlockingQueueImpl;

public class NumberProducer implements Runnable 
{
	MyBlockingQueueImpl<Integer> numbersQueue;
	private int poisonPill;
    private int poisonPillPerProducer;
     
    public NumberProducer(MyBlockingQueueImpl<Integer> numbersQueue, int poisonPill, int poisonPillPerProducer) 
    {
        this.numbersQueue = numbersQueue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }
    
    @Override
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
     
    private void generateNumbers() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
        }
        for (int j = 0; j < poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
        }
     }
}
