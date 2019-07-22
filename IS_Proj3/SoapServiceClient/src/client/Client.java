package client;

import java.util.Scanner;

import artifact.MyWebService;
import artifact.MyWebServiceService;


public class Client {
	public static void main(String[] args) {
		MyWebServiceService as = new MyWebServiceService();
		MyWebService asp = as.getMyWebServicePort();
		
		Scanner sc = new Scanner(System.in);
		menu(asp, sc);
	}
	
	static void menu(MyWebService asp, Scanner sc) {
		System.out.println("--- Followers Menu ---");
		System.out.println("1. Add Follower");
		System.out.println("2. Remove Follower");
		System.out.println("3. List all followers");
		System.out.println("4. List project 2 users");
		System.out.println("0. Exit Application");
		
		switch (sc.nextInt()) {
			case 1:
				addFollower(asp, sc);
				menu(asp, sc);
				break;
				
			case 2:
				removeFollower(asp, sc);
				menu(asp, sc);
				break;
				
			case 3:
				System.out.println();
				System.out.println("Processing your request...");
				System.out.println(asp.listAll());
				System.out.println();
				menu(asp, sc);
				break;
			
			case 4:
				System.out.println();
				System.out.println("Processing your request...");
				System.out.println(asp.listUsers());
				System.out.println();
				menu(asp, sc);
				break;
			case 0:
				System.out.println("Bye :)");
				sc.close();
				System.exit(0);
				break;
				
			default:
				menu(asp, sc);
				break;
		}
	}
	
	static void addFollower(MyWebService asp, Scanner sc) {
		System.out.println("Email?");
		String email = sc.next();
		System.out.println("Car Brand?");
		String brand = sc.next();
		System.out.println("Min Price?");
		String min = sc.next();
		System.out.println("Max Price?");
		String max = sc.next();
		
		System.out.println();
		System.out.println("Processing your request...");
		System.out.println(asp.addFollower(email.toLowerCase(), brand.toLowerCase(), Integer.parseInt(min), Integer.parseInt(max)));
		System.out.println();
	}
	
	static void removeFollower(MyWebService asp, Scanner sc) {
		System.out.println("Email?");
		String email = sc.next();
		System.out.println("Car Brand?");
		String brand = sc.next();
		
		System.out.println();
		System.out.println("Processing your request...");
		System.out.println(asp.deleteFollower(email.toLowerCase(), brand.toLowerCase()));
		System.out.println();
	}
}
