package javafiles;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import jdbc.Admin;
public class MainClass {
	public static boolean validateAdmin(int adminId, String password) {
		HashMap<Integer,String> adminDetails = new HashMap<Integer,String>();
		adminDetails.put(12345,"electricity12");
		adminDetails.put(67890,"electricity23");
		if(adminDetails.containsKey(adminId)) {
			if(adminDetails.get(adminId).equalsIgnoreCase(password)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner scanner = new Scanner(System.in);
		int choice;
		System.out.println("---------WELCOME TO ELECTRICITY BILL CALCULATOR---------");
		System.out.println("Choose your role");
		System.out.println("1) Administrator");
		System.out.println("2) Consumer");
		choice = scanner.nextInt();
		switch (choice) {
		case 1: {
			boolean rule = true;
			while(rule) {
				System.out.print("Enter Admin ID :");
				int adminId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Password :");
				String pass = scanner.nextLine();
				if(validateAdmin(adminId,pass)) {
					rule = false;
					boolean rule2 = true;
					String answer;
					int option;
					System.out.println("Welcome Admin!");
					while(rule2) {
						System.out.println("Choose an option: ");
						System.out.println("1) Add units consumed in a month for a consumer");
						System.out.println("2) Generate bills for all consumers");
						System.out.println("3) Generate bills for an area");
						System.out.println("4) Generate bills for a city");
						System.out.println("5) Generate bills for a month");
						System.out.println("6) Generate bills for an year");
						option = scanner.nextInt();
						switch(option) {
						case 1:{
							Admin.addUnitsConsumed();
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if(answer.equalsIgnoreCase("n")) {
								rule2 = false;
							}else {
								rule2 = true;
							}
							break;
						}
						case 2:{
							Admin.generateBillsForAllCustomers();
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if(answer.equalsIgnoreCase("n")) {
								rule2 = false;
							}else {
								rule2 = true;
							}
							break;
						}
						case 3:{
							Admin.generateBillsForAnArea();
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if(answer.equalsIgnoreCase("n")) {
								rule2 = false;
							}else {
								rule2 = true;
							}
							break;
						
						}
						case 4:{
							Admin.generateBillsForACity();
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if(answer.equalsIgnoreCase("n")) {
								rule2 = false;
							}else {
								rule2 = true;
							}
							break;
						}
						case 5:{
							Admin.generateBillByMonth();
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if(answer.equalsIgnoreCase("n")) {
								rule2 = false;
							}else {
								rule2 = true;
							}
							break;
						}
						case 6:{
							Admin.generateBillByYear();
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if(answer.equalsIgnoreCase("n")) {
								rule2 = false;
							}else {
								rule2 = true;
							}
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + option);
						}
					}
				}else {
					System.out.println("ERROR! entered details are wrong please enter correct details");
				}
			}
			break;
		}
		case 2: {
			System.out.println("Choose option");
			System.out.println("1) New User");
			System.out.println("2) Existing User");
			int option1 = scanner.nextInt();
			switch(option1) {
				case 1:break;
				case 2:break;
				default:{
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
			}
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}

}
