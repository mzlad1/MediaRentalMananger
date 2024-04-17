package application;
import java.util.*;

public class Movie extends Media implements Comparable<Media>{
	String rating;

	public Movie(String code, String title, int numCopy, String rating) {
		super(code,title,numCopy);
		this.rating = rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}

	@Override
	public String toString() {
		return super.toString() + "Movie => " + "Rate : " + rating + "\n"
								 +"_____________________________________________________________________________"+"\n";
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
