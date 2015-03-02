package com.richlosardo.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.richlosardo.MusicCollection;

public class SynchronizationExample {
	
	private MusicCollection musicCollection;
	
	public SynchronizationExample() {
		musicCollection = new MusicCollection();
	}
	
	public void updateMusicCollection() {
		Runnable addRunnable = new Runnable() {
			@Override
			public void run() {
				int addCdCount = (int) (Math.random()*10);
				int addMp3Count = (int) (Math.random()*10);
				int addRecordCount = (int) (Math.random()*10);
				synchronized (musicCollection) {
					System.out.println("adding " + addCdCount + " cds, " + addMp3Count + " mp3s, " + addRecordCount + " records");
					musicCollection.addToMusicCollection(addCdCount, addMp3Count, addRecordCount);
				}
			}
		};
		Runnable removeRunnable = new Runnable() {
			@Override
			public void run() {
				int removeCdCount = (int) (Math.random()*10);
				int removeMp3Count = (int) (Math.random()*10);
				int removeRecordCount = (int) (Math.random()*10);
				synchronized (musicCollection) {
					System.out.println("removing " + removeCdCount + " cds, " + removeMp3Count + " mp3s, " + removeRecordCount + " records");
					musicCollection.removeFromMusicCollection(removeCdCount, removeMp3Count, removeRecordCount);
				}
			}
		};
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i=0; i<50; i++) {
			executorService.execute(addRunnable);
			executorService.execute(removeRunnable);
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (musicCollection) {
			musicCollection.printMusicCollection();
		}
	}
	
	public static void main(String[] args) {
		SynchronizationExample synchronizationExample = new SynchronizationExample();
		synchronizationExample.updateMusicCollection();
	}

}
