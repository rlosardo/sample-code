package com.richlosardo.swing.library;

public class MusicObject implements Comparable<MusicObject> {

	private String artist;
	private String title;
	private String album;
	private String genre;
	private Integer track;
	private Integer year;
	private String smallImageName;
	
	public MusicObject(String artist, String title, String album, String genre, Integer track, Integer year, String smallImageName) {
		this.artist = artist;
		this.title = title;
		this.album = album;
		this.genre = genre;
		this.track = track;
		this.year = year;
		this.smallImageName = smallImageName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSmallImageName() {
		return smallImageName;
	}

	public void setSmallImageName(String smallImageName) {
		this.smallImageName = smallImageName;
	}

	@Override
	public int compareTo(MusicObject o) {
		int compare = genre.compareTo(o.getGenre());
		if (compare == 0) {
			compare = artist.compareTo(o.getArtist());
		}
		if (compare == 0) {
			compare = album.compareTo(o.getAlbum());
		}
		if (compare == 0) {
			compare = track.compareTo(o.getTrack());
		}
		return compare;
	}

	public Integer getTrack() {
		return track;
	}

	public void setTrack(Integer track) {
		this.track = track;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
