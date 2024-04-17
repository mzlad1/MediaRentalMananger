package application;
import java.util.*;

public class Game extends Media implements Comparable<Media> {
	double weight;

	public Game(String code, String title, int numCopy, double weight) {
		super(code, title, numCopy);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return super.toString() + "Game => " + "Weight : "+ weight+"\n"+
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
