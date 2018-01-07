package com.custom.blocking.queue.impl;

import java.util.LinkedList;
import java.util.List;

import com.custom.blocking.queue.MyBlockingQueue;

public class MyBlockingQueueImpl<E> implements MyBlockingQueue<E>
{

	private List<E> queue;
	private int  maxSize ;
	
	public MyBlockingQueueImpl()
	{
		this.maxSize = Integer.MAX_VALUE;
		queue = new LinkedList<E>();
	}
	
	public MyBlockingQueueImpl(int maxSize)
	{
		this.maxSize = maxSize;
		queue = new LinkedList<E>();
	}

	@Override
	public synchronized void put(E e) throws InterruptedException 
	{
		if (e == null)
			throw new NullPointerException();

		while(this.queue.size() == this.maxSize)
		{
			this.wait();
		}
		if (this.queue.size() == 0)
			this.notifyAll();

		this.queue.add(e);
	}

	@Override
	public synchronized E take() throws InterruptedException 
	{
		E item;
		while(this.queue.size() == 0)
		{
			this.wait();
		}
		if (this.queue.size() == this.maxSize)
			this.notifyAll();

		item = this.queue.remove(0); 

		return item;
	}
}
