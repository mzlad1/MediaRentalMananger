package application;
import java.util.*;

public class Album extends Media implements Comparable<Media>{
	String artist;
	String songs;

	public Album(String code, String title, int numCopy, String artist, String songs) {
		super(code, title, numCopy);
		this.artist = artist;
		this.songs = songs;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}

	public String getArtist() {
		return artist;
	}

	public String getSongs() {
		return songs;
	}

	@Override
	public String toString() {
		return super.toString() + "Album => " + "Artist : " + artist + " || " + "Songs : " + songs + "\n"+
				                  "_____________________________________________________________________________"+"\n";
	}
	@Override
	public int compareTo(Media o) {

		if (title.compareTo(o.getTitle()) > 0)
			return 1;
		else if (title.compareTo(o.getTitle()) < 0)
			return -1;
		else
			return 0;
	}
}
