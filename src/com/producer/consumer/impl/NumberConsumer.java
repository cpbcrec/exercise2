package com.producer.consumer.impl;

import com.custom.blocking.queue.impl.MyBlockingQueueImpl;

public class NumberConsumer implements Runnable 
{
	private MyBlockingQueueImpl<Integer> queue;
	private final int poisonPill;

	public NumberConsumer(MyBlockingQueueImpl<Integer> queue, int poisonPill) {
		this.queue = queue;
		this.poisonPill = poisonPill;
	}
	public void run() {
		try {
			while (true) {
				Integer number = queue.take();
				if (number.equals(poisonPill)) {
					return;
				}
				String result = number.toString();
				System.out.println(Thread.currentThread().getName() + " result: " + result);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
