package application;
import java.util.*;

public interface MediaRentInt {
	void addCustomer(String name, String address, String plan, String id, String mobile);

	void addMovie(String code,String title, int numCopy, String rating);

	void addGame(String code,String title, int numCopy, double weight);

	void addAlbum(String code,String title, int numCopy, String artist, String songs);
	
	void setLimitedPlanLimit(int value);

	String getAllCustomersInfo();

	String getAllMediaInfo();

	boolean addToCart(String customerName, String mediaTitle);

	boolean removeFromCart(String customerName, String mediaTitle);

	String processRequests();

	boolean returnMedia(String id, String code);


}
