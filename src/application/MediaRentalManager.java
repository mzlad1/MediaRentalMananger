package application;

import java.util.*;

public class MediaRentalManager implements MediaRentInt {
	
	ArrayList<Customer> customer = new ArrayList<>();
	ArrayList<Media> media = new ArrayList<>();
	private int LimitedPlanLimit = 2;

	public MediaRentalManager() {
	}

//	public MediaRentalManager(ArrayList<Customer> customer, ArrayList<Media> media) {
//		this.customer = customer;
//		this.media = media;
//	}

	public ArrayList<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(ArrayList<Customer> customer) {
		this.customer = customer;
	}

	public ArrayList<Media> getMedia() {
		return media;
	}

	public void setMedia(ArrayList<Media> media) {
		this.media = media;
	}

	@Override
	public void addCustomer(String name, String address, String plan, String id, String mobile) {
		customer.add(new Customer(name, address, plan, id, mobile));

	}

	@Override
	public void addMovie(String code, String title, int numCopy, String rating) {
		media.add(new Movie(code, title, numCopy, rating));

	}

	@Override
	public void addAlbum(String code, String title, int numCopy, String artist, String songs) {

		media.add(new Album(code, title, numCopy, artist, songs));

	}

	@Override
	public void addGame(String code, String title, int numCopy, double weight) {
		
		media.add(new Game(code, title, numCopy, weight));

	}

	@Override
	public void setLimitedPlanLimit(int value) {
		this.LimitedPlanLimit = value;

	}

	public Customer searchByID(String id) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId().equals(id)) {
				return customer.get(i);
			}
		}
		return null;
	}

	public Media searchByCode(String code) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode().equals(code)) {
				return media.get(i);
			}
		}
		return null;
	}

	public Movie findMovie(String code) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode().equals(code) && media.get(i) instanceof Movie) {
				return (Movie) media.get(i);
			}
		}
		return null;
	}

	public Album findMusic(String code) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode().equals(code) && media.get(i) instanceof Album) {
				return (Album) media.get(i);
			}
		}
		return null;
	}

	public Game findGame(String code) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode().equals(code) && media.get(i) instanceof Game) {
				return (Game) media.get(i);
			}
		}
		return null;
	}

	public String printIntrested(String id) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId().equals(id)) {
				return customer.get(i).printIntrested();
			}
		}
		return null;
	}

	public String printRented(String id) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId().equals(id)) {
				return customer.get(i).printRented();
			}
		}
		return null;
	}

	@Override
	public String getAllCustomersInfo() {
		System.out.println("========> All customers info <========");
		String AllCustomer = "";
		for (int i = 0; i < customer.size(); i++) {
			AllCustomer += customer.get(i).toString();
		}
		return AllCustomer;
	}

	@Override
	public String getAllMediaInfo() {
		String AllMedia = "";
		for (int i = 0; i < media.size(); i++) {
			AllMedia += media.get(i).toString();
		}
		return AllMedia;
	}

	@Override
	public boolean addToCart(String id, String code) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId().equals(id)) {
				for (int j = 0; j < customer.get(i).getInterested().size(); j++) {
					if (customer.get(i).getInterested().get(j).equals(code)) {
						return false;
					}
				}
				customer.get(i).getInterested().add(code);
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean removeFromCart(String customerName, String mediaTitle) {

		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getName().equals(customerName)) {
				customer.get(i).interested.remove(mediaTitle);
				System.out.println("[" + mediaTitle + "]" + " " + "this media title has been removed from the cart");
				return true;
			}
		}
		System.out.println("[" + customerName + "]" + " " + "This customer name is not found");
		return false;

	}

	@Override
	public String processRequests() {
		StringBuilder process = new StringBuilder();
		Collections.sort(customer);
		for (int i = 0; i < customer.size(); i++) {
			for (int k = 0; k < customer.get(i).getInterested().size(); k++) {
				if (customer.get(i).getPlan().equalsIgnoreCase("unlimited") || customer.get(i).getRented().size() < LimitedPlanLimit) {
					for (int j = 0; j < media.size(); j++) {
						if (media.get(j).getCode().equals(customer.get(i).getInterested().get(k))) {
							if (media.get(j).getNumCopy() > 0) {
								customer.get(i).getRented().add(customer.get(i).getInterested().get(k));
								media.get(j).setNumCopy(media.get(j).getNumCopy() - 1);
								process.append("Sending {").append(customer.get(i).getInterested().get(k))
										.append("] To [").append(customer.get(i).getName()).append("]\n");
								customer.get(i).getInterested().remove(k);
								k--;
								break;
							} else
								continue;
						}
					}
				} else
					break;
			}
		}
		return process.toString();
	}

	@Override
	public boolean returnMedia(String id, String code) {
		boolean returned = false;
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId().equals(id)) {
				for (int j = 0; j < customer.get(i).getRented().size(); j++) {
					if (customer.get(i).getRented().get(j).equals(code)) {
						customer.get(i).getRented().remove(j);
						returned = true;
					}
				}
				if(returned) {
					customer.get(i).setRented(customer.get(i).getRented());
					for(int j=0;j<media.size();j++) {
						if(media.get(j).getCode().equals(code)) {
							media.get(j).setNumCopy(media.get(j).getNumCopy() + 1);
							break;
						}
					}
				}
			}
		}
		return returned;
	}

}
