package com.richlosardo.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.richlosardo.MusicCollection;

public class ConcurrentHashMapExample {

	private Map<Integer, MusicCollection> musicCollectionMap;
	private AtomicInteger idCounter;
	
	public ConcurrentHashMapExample() {
		musicCollectionMap = new ConcurrentHashMap<Integer, MusicCollection>();
		idCounter = new AtomicInteger();
	}
	
	public void runExample() {
		Runnable addRunnable = new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<100; i++) {
					int addCdCount = (int) (Math.random()*10);
					int addMp3Count = (int) (Math.random()*10);
					int addRecordCount = (int) (Math.random()*10);
					Integer id = idCounter.addAndGet(1);
					MusicCollection musicCollection = new MusicCollection(id);
					musicCollection.addToMusicCollection(addCdCount, addMp3Count, addRecordCount);
					musicCollectionMap.put(id, musicCollection);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Runnable printRunnable = new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<50; i++) {
					Integer id = (int) (Math.random()*100);
					MusicCollection musicCollection = musicCollectionMap.get(id);
					if (musicCollection != null) {
						System.out.println("found music collection id " + id + ":");
						musicCollection.printMusicCollection();
						System.out.println();
					}
					else {
						System.out.println("could not find music collection with id " + id + "\n");
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(addRunnable);
		Thread t2 = new Thread(printRunnable);
		t.start();
		t2.start();
	}
	
	public static void main(String[] args) {
		ConcurrentHashMapExample concurrentHashMapExample = new ConcurrentHashMapExample();
		concurrentHashMapExample.runExample();
	}
}
