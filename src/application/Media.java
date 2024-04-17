package application;

import java.util.*;

public abstract class Media implements Comparable<Media> {
	String title;
	int numCopy;
	String code;

	public Media(String code, String title, int numCopy) {
		this.code = code;
		this.title = title;
		this.numCopy = numCopy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumCopy() {
		return numCopy;
	}

	public void setNumCopy(int numCopy) {
		this.numCopy = numCopy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "Code : " + code + "\n" + "Media Title : " + title + " || " + "Number Of Copies : " + numCopy + "\n";
	}

}
