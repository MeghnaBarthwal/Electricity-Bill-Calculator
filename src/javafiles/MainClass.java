package javafiles;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import jdbc.Admin;
import jdbc.ConsumerUtility;

public class MainClass {
	public static boolean validateAdmin(int adminId, String password) {
		HashMap<Integer, String> adminDetails = new HashMap<Integer, String>();
		adminDetails.put(12345, "electricity12");
		adminDetails.put(67890, "electricity23");
		if (adminDetails.containsKey(adminId)) {
			if (adminDetails.get(adminId).equalsIgnoreCase(password)) {
				return true;
			} else {
				return false;
			}
		} else {
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
				while (rule) {
					System.out.print("Enter Admin ID :");
					int adminId = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter Password :");
					String pass = scanner.nextLine();
					if (validateAdmin(adminId, pass)) {
						rule = false;
						boolean rule2 = true;
						String answer;
						int option;
						System.out.println("Welcome Admin!");
						while (rule2) {
							System.out.println("Choose an option: ");
							System.out.println("1) Add units consumed in a month for a consumer");
							System.out.println("2) Generate bills for all consumers");
							System.out.println("3) Generate bills for an area");
							System.out.println("4) Generate bills for a city");
							System.out.println("5) Generate bills for a month");
							System.out.println("6) Generate bills for an year");
							option = scanner.nextInt();
							switch (option) {
								case 1: {
									Admin.addUnitsConsumed();
									System.out.println("Do you want to continue y or n");
									answer = scanner.next();
									scanner.nextLine();
									if (answer.equalsIgnoreCase("n")) {
										rule2 = false;
									} else {
										rule2 = true;
									}
									break;
								}
								case 2: {
									Admin.generateBillsForAllCustomers();
									System.out.println("Do you want to continue y or n");
									answer = scanner.next();
									scanner.nextLine();
									if (answer.equalsIgnoreCase("n")) {
										rule2 = false;
									} else {
										rule2 = true;
									}
									break;
								}
								case 3: {
									Admin.generateBillsForAnArea();
									System.out.println("Do you want to continue y or n");
									answer = scanner.next();
									scanner.nextLine();
									if (answer.equalsIgnoreCase("n")) {
										rule2 = false;
									} else {
										rule2 = true;
									}
									break;

								}
								case 4: {
									Admin.generateBillsForACity();
									System.out.println("Do you want to continue y or n");
									answer = scanner.next();
									scanner.nextLine();
									if (answer.equalsIgnoreCase("n")) {
										rule2 = false;
									} else {
										rule2 = true;
									}
									break;
								}
								case 5: {
									Admin.generateBillByMonth();
									System.out.println("Do you want to continue y or n");
									answer = scanner.next();
									scanner.nextLine();
									if (answer.equalsIgnoreCase("n")) {
										rule2 = false;
									} else {
										rule2 = true;
									}
									break;
								}
								case 6: {
									Admin.generateBillByYear();
									System.out.println("Do you want to continue y or n");
									answer = scanner.next();
									scanner.nextLine();
									if (answer.equalsIgnoreCase("n")) {
										rule2 = false;
									} else {
										rule2 = true;
									}
									break;
								}
								default:
									throw new IllegalArgumentException("Unexpected value: " + option);
							}
						}
					} else {
						System.out.println("ERROR! entered details are wrong please enter correct details");
					}
				}
				break;
			}
			case 2: {
				System.out.println("Choose option");
				System.out.println("1) New User? Register");
				System.out.println("2) Existing User");
				int option1 = scanner.nextInt();
				switch (option1) {
					case 1: {
						Consumer cobj = ConsumerUtility.newRegistration();
						if (cobj == null) {
							System.out.println("Sorry, registration failed.");
							return;
						}
						System.out.println("Your Consumer ID: " + cobj.getId());
						System.out.println("Your Password: " + cobj.getPassword());
					}
						break;
					case 2: {
						Consumer cobj = ConsumerUtility.consumerLogin();
						if (cobj == null) {
							System.out.println("Invalid id or password");
							return;
						}
						String ch = "yes";
						while (ch.equalsIgnoreCase("yes") || ch.equalsIgnoreCase("y")) {
							System.out.println("Welcome " + cobj.getName());
							System.out.println("Choose an option");
							System.out.println("1. Get bill by month and year");
							System.out.println("2. Get bills by year");
							System.out.println("3. Get all your bills");
							System.out.print("Enter your choice: ");
							int option2 = scanner.nextInt();
							scanner.nextLine();
							switch (option2) {
								case 1: {
									System.out.print("Enter month: ");
									String month = scanner.nextLine();
									System.out.print("Enter year: ");
									int year = scanner.nextInt();
									ConsumerUtility.getBillByMonth(month, year, cobj.getId());
								}
									break;
								case 2: {
									System.out.print("Enter year: ");
									int year = scanner.nextInt();
									ConsumerUtility.getBillsByYear(year, cobj.getId());
								}
									break;
								case 3: {
									ConsumerUtility.getAllBills(cobj.getId());
								}
									break;
								default:
									System.out.println("Invalid choice");
							}
							System.out.print("Do you wish to continue?");
							ch = scanner.nextLine();
						}
					}
						break;
					default: {
						System.out.println("Invalid choice");
					}
				}
			}
				break;
			default:
				System.out.println("Invalid choice");
		}
		System.out.println("Thank you for using Electricity Bill Calculator.");
		return;
	}
}