package com.revature.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.revature.dao.AccountTypeDao;
import com.revature.dao.BankAccountDao;
import com.revature.dao.BankUserDao;
import com.revature.dao.DAO;
import com.revature.pojos.AccountType;
import com.revature.pojos.BankAccount;
import com.revature.pojos.BankUser;
import com.revature.pojos.InsufficientFundsException;
import com.revature.service.BankAccountService;
import com.revature.service.BankAccountTypeService;
import com.revature.service.BankUserService;

public class BankApp {

	private static BankUserService uService = new BankUserService();
	private static BankAccountService aService = new BankAccountService();
	private static BankAccountTypeService typeService = new BankAccountTypeService();
	private static BankAccount currentAccount = new BankAccount();
	private static BankUser currentUser = new BankUser();
	private static AccountType currentType = new AccountType();
	private static List<BankAccount> listAccounts;
	private static int userId = 0 ;
	private static int accountId = 0;

	public static void main(String[] args) {
		
		run();
		
	}
	
	
	/*
	 * Start the program, ask to log in or register an account.
	 */
	public static void run() {
		
		System.out.println("          Welcome to your mobile banking app!"
				+ "\n \n 1. To log in, enter 1: "
				+ "\n 2. To register or open an account with us, enter 2:");
				
		Scanner scan = new Scanner(System.in);
		
		int option = 0;
		
		try {
			option = scan.nextInt();
			switch(option) {
			case 1: login(); break;
			case 2: registerAccount(); break;
			default: 
				System.out.println("Please enter a number on our menu \n");
				run(); break;
			}
		}
		catch(InputMismatchException e) {
			System.out.println("Sorry! You must enter a number from our menu \n");
			run(); 
		}
		
	}
 	
	
	
	/*
	 * Let user log in to account
	 * If valid, find the user's account
	 */
	public static void login() {
		
		Scanner scan = new Scanner(System.in);
				
		System.out.println("Enter your username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		
		
		System.out.println("Please wait, connecting to database.......... \n");
		
		//Check username and password
		currentUser = uService.checkUserNamePassword(username, password);
		
		
		if(currentUser != null) {
			
			userId = currentUser.getUserId(); //get the currentUserId
			
			listOfAccounts();
			afterLoginMenu();
		} 
		
		else {
			System.out.println("You entered wrong username or password. \n");
			run(); // start run again
		}
		
	}
	
	/*
	 * Find user's list of account
	 */
	public static void listOfAccounts() {
		
		listAccounts = aService.findAllAccount(userId);
			
		int numOfAccount = listAccounts.size();
		int id;
			if(numOfAccount==1) {  // User has only 1 account
				currentAccount = listAccounts.get(0);
				id = currentAccount.getTypeId();
				findAccountType(id);
		
			}
			else {  // User has more than one account.
				
				double balance;
				String accountType;
				
				System.out.println("This is a list of your accounts: \n");
				for(int i = 0; i < listAccounts.size(); i++) {
					
					id = listAccounts.get(i).getTypeId();
					balance = listAccounts.get(i).getBalance();
					findAccountType(id);
					accountType = currentType.getAccountType();
					
					System.out.println(i+1 + " ." + accountType + " account, balance: $" + balance+ "\n");
				}
				selectAccount();
			}
	}
	
	/*
	 * use typeid to find accounttype 'Checking or saving'
	 * return to currentType obj.
	 */
	public static void findAccountType(int id) {
		currentType = typeService.findById(id);
	}
	
	
	/**
	 * Let user select from a list of her account if she has more than one
	 */
	public static void selectAccount() {
		
		try {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Please select your account to begin with, or enter 0 to exit: ");
			
			int choice = scan.nextInt();
			
			if(choice==0) System.exit(0);
			else currentAccount = listAccounts.get(choice-1);
			
		
		} catch(InputMismatchException e) {
			System.out.println("Please enter a correct account order! \n");
			selectAccount();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Please enter correct number: \n");
			selectAccount();
		}
		
	}
	
	
	/**
	 * After successfully login, display the selection menu.
	 */
	public static void afterLoginMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n                Welcome " + currentUser.getFirstName() 
				+ "\n    Your " + currentType.getAccountType() + " account has a balance of $ " + currentAccount.getBalance() + "\n"
				+ "\n 1. To deposit, enter 1: "
				+ "\n 2. To withdraw, enter 2: "
				+ "\n 3. To view a list of your accounts, enter 3: "
				+ "\n 4. To create another bank account, enter 4:"
				+ "\n 5. To log out, enter 5: ");

		int option = 0;
	
		try {
			option = scan.nextInt();
			switch(option) {
			case 1: deposit(); afterLoginMenu(); break;
			case 2: withdraw(); afterLoginMenu(); break;
			case 3: listOfAccounts(); afterLoginMenu(); break;
			case 4: createNewAccount(); break;
			case 5: logout(); break;
			default:
				System.out.println("Please enter a number on our menu \n");
				afterLoginMenu(); break;
			}
		}
		catch(InputMismatchException e) {
			System.out.println("Sorry! You must enter a number from our menu \n");
			afterLoginMenu(); 
		}
	}
	
	
	/*
	 * deposit to account
	 */
	public static void deposit() {
		
		Scanner scan = new Scanner(System.in);
		double amount = 0;
		try {
			System.out.println("Enter deposit amount: $");
			amount = scan.nextDouble();
			currentAccount.deposit(amount);
			updateBankAccount();
			System.out.println("Your account has been deposited $ " + amount  + "\n");
		} 
		catch(InputMismatchException e) {
			System.out.println("You entered wrong input format! \n");
			deposit();
		}
	}
	
	/*
	 * withdraw from current account, throw exception if withdraw more than balance.
	 */
	public static void withdraw() {
		
		Scanner scan = new Scanner(System.in);
		double amount = 0;
		try {
			System.out.println("Enter amount you want to withdraw: $");
			amount = scan.nextDouble();
			currentAccount.withdraw(amount);
			updateBankAccount();
		
		} 
		catch(InputMismatchException e) {
			System.out.println("You entered wrong input format! \n");
			withdraw();
		}
		catch(InsufficientFundsException e) {
			System.out.println("Sorry, you are short $" + e.getAmount());
			System.out.println("Your request is cancelled" + "\n");
			afterLoginMenu();
		}
	}
	
	/**
	 * User create a new bank account
	 */
	public static void createNewAccount() {
		
		Scanner scan = new Scanner(System.in);
		
		try {
		System.out.println("-----Creating your new bank account---------\n");
		System.out.println("Enter account type, 1 for checking, 2 for saving: ");
		int accountType = scan.nextInt();
		System.out.println("Enter amount you want to deposit to your new account: ");
		double balance = scan.nextDouble();
		
		BankAccount bAccount = new BankAccount(1, accountType, userId, balance);
		currentAccount = bAccount;
		aService.createNewAccount(currentAccount);
		
		System.out.println("Your account has been successfully created \n");
		listOfAccounts();
		afterLoginMenu();
		
		} catch(InputMismatchException e) {
			System.out.println("Please enter correct number format: \n");
			createNewAccount();
		}
	}
	
	/**
	 * let user register, create username, password.
	 */
	public static void registerAccount() {
		
		BankUser user;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter your first name:");
		String firstname = scan.nextLine();
		System.out.println("Enter your last name: ");
		String lastname = scan.nextLine();
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		
		System.out.println("Please wait, validating your username........ \n");
		
		boolean foundUser = uService.checkUserNameExist(username);
		
		
		if(foundUser) {
			System.out.println(username + " has been taken, please choose another username: \n");
			registerAccount();
		} else {
			user = new BankUser(1, firstname, lastname, username, password);
			uService.registerUser(user);
			
			
			currentUser = uService.checkUserNamePassword(username, password); // return currentUser
			userId = currentUser.getUserId();
			System.out.println(firstname + " ,your username and password have been created! \n");
			createNewAccount();
		}
	}
	
	/*
	 * user log out, reset all static fields.
	 */
	public static void logout() {
		
		currentAccount = null;
		currentUser = null;
		listAccounts = null;
		currentType = null;
		userId = 0;
		System.exit(0);
		//run();
	}
	
	/*
	 * update user's bank account, after deposit, withdraw.
	 */
	public static void updateBankAccount() {
		
		aService.update(currentAccount);
	}
	
}
