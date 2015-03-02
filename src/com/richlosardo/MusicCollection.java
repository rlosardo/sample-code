package com.richlosardo;

public class MusicCollection {

	private int cdCount;
	private int mp3Count;
	private int recordCount;
	
	public MusicCollection() {
	}
	
	public void addToMusicCollection(int addCdCount, int addMp3Count, int addRecordCount) {
		cdCount+= addCdCount;
		mp3Count+= addMp3Count;
		recordCount+= addRecordCount;
	}
	
	public void removeFromMusicCollection(int removeCdCount, int removeMp3Count, int removeRecordCount) {
		cdCount-= removeCdCount;
		mp3Count-= removeMp3Count;
		removeRecordCount-= removeRecordCount;
		if (cdCount < 0) {
			cdCount = 0;
		}
		if (mp3Count < 0) {
			mp3Count = 0;
		}
		if (recordCount < 0) {
			removeRecordCount = 0;
		}
	}
	
	public void printMusicCollection() {
		System.out.println("cdCount = " + cdCount);
		System.out.println("mp3Count = " + mp3Count);
		System.out.println("recordCount = " + recordCount);
	}
	
}
