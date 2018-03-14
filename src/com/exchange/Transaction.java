package com.exchange;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class Transaction implements Serializable, Comparator<Transaction>, Comparable<Transaction> {

	private double price;
	private Double transactionAmount;
	private Double transactionQuantity;
	private float transactionFee;
	private String transactionType;
	private String[] bankPayment;
	private String[] cardPayment;
	private Date time;
	private int transactionId;
	private CryptoCurrency[] cryptoCurrency;
	private Payment payment;
	private Wallet[] wallet;
	private String paymentType;
	private String emailID;
	private String transactionCurrency;

	public Transaction() {

	}

	public Transaction(String emailID, CryptoCurrency[] currency) {
		this.emailID = emailID;
		this.cryptoCurrency = currency;
	}

	public Transaction(String emailID, CryptoCurrency[] currency, String transactionType, String paymentType,
			int transactionID) throws IOException, ClassNotFoundException {
		this.emailID = emailID;
		this.transactionId = transactionID;
		this.paymentType = paymentType;
		this.cryptoCurrency = currency;
		this.transactionType = transactionType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public float getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(float transactionFee) {
		this.transactionFee = transactionFee;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String[] getBankPayment() {
		return bankPayment;
	}

	public void setBankPayment(String[] bankPayment) {
		this.bankPayment = bankPayment;
	}

	public String[] getCardPayment() {
		return cardPayment;
	}

	public void setCardPayment(String[] cardPayment) {
		this.cardPayment = cardPayment;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void writeFile(HashMap<String, Double> details, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(details);

		fos = new FileOutputStream(emailID + "Wallet.dat");
		oos = new ObjectOutputStream(fos);
		oos.writeObject(wallet);

	}

	public boolean sellCommit(double quantity, double amount, HashMap<String, Double> details, String currency,
			String bankName) throws IOException, ClassNotFoundException {

		transactionQuantity = quantity;
		transactionAmount = amount;
		transactionCurrency = currency;

		double currentBalance = details.get(bankName);
		

		if (quantity > 0 && amount > 0) {

			// amount calculation if quantity given
			FileInputStream fis = new FileInputStream(emailID + "Wallet.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			wallet = (Wallet[]) ois.readObject();
			boolean commit=false;

			System.out.println("Current Balance" + currentBalance);

			if (currency.equalsIgnoreCase("bitcoin") && wallet[0].getQuantity() >= quantity) {
				currentBalance += amount;
				details.put(bankName, currentBalance);
				wallet[0].subCurrency(quantity);
				commit=true;

			} else if (currency.equalsIgnoreCase("ethereum") && wallet[1].getQuantity() >= quantity) {
				currentBalance += amount;
				details.put(bankName, currentBalance);
				wallet[1].subCurrency(quantity);
				commit=true;
			} else if (currency.equalsIgnoreCase("litecoin") && wallet[2].getQuantity() >= quantity) {
				currentBalance += amount;
				details.put(bankName, currentBalance);
				wallet[2].subCurrency(quantity);
				commit=true;
			}

			System.out.println("Current balance in "+ bankName+ " after selling:" +currentBalance);
			return commit;
		}

		else {
			System.out.println("No balance");
			return false;
		}

	}

	public boolean buyCurrency(String bankName, double amount, double quantity, String currency, Payment payment)
			throws ClassNotFoundException, IOException {

		System.out.println("Trade Amount: "+amount);
		amount=amount+amount*0.02;
		System.out.println("Trade Amount after 2% transaction fees: "+amount);
		
		transactionQuantity = quantity;
		transactionAmount = amount;
		transactionCurrency = currency;

		double currentBalance = 0;
		HashMap<String, Double> details;

		FileInputStream fis = new FileInputStream(emailID + "Wallet.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		wallet = (Wallet[]) ois.readObject();

		if (payment instanceof BankAccount) {
			BankAccount ba = (BankAccount) payment;
			details = ba.getBankAccount();
			currentBalance = details.get(bankName);
			
			if (quantity > 0 && amount > 0) {

				if (currentBalance > amount) {

					// amount calculation if quantity given

					if (currency.equalsIgnoreCase("bitcoin")) {

						currentBalance -= amount;
						details.put(bankName, currentBalance);
						wallet[0].addCurrency(quantity);
						wallet[0].setInvestment(amount);

					} else if (currency.equalsIgnoreCase("ethereum")) {

						currentBalance -= amount;
						details.put(bankName, currentBalance);
						wallet[1].addCurrency(quantity);
						wallet[1].setInvestment(amount);

					} else {

						currentBalance -= amount;
						details.put(bankName, currentBalance);
						wallet[2].addCurrency(quantity);
						wallet[2].setInvestment(amount);

					}

					FileOutputStream fos = new FileOutputStream(emailID + "Wallet.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(wallet);

					fos = new FileOutputStream(emailID + "Bank.dat");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(details);
					
					System.out.println("Current balance in "+ bankName+ " after buying:" +currentBalance);
					return true;
					
				} else {
					System.out.println("No balance in bank");
					return false;
				}
			}

		} else if (payment instanceof CreditCard) {
			CreditCard ca = (CreditCard) payment;
			details = ca.getCardAccount();
			currentBalance = details.get(bankName);
			if (quantity > 0 && quantity > 0) {

				if (currentBalance > amount) {

					// amount calculation if quantity given

					if (currency.equalsIgnoreCase("bitcoin")) {

						currentBalance -= amount;
						details.put(bankName, currentBalance);
						wallet[0].addCurrency(quantity);
						wallet[0].setInvestment(amount);


					} else if (currency.equalsIgnoreCase("ethereum")) {

						currentBalance -= amount;
						details.put(bankName, currentBalance);
						wallet[1].addCurrency(quantity);
						wallet[1].setInvestment(amount);

					} else {

						currentBalance -= amount;
						details.put(bankName, currentBalance);
						wallet[2].addCurrency(quantity);
						wallet[2].setInvestment(amount);

					}

					FileOutputStream fos = new FileOutputStream(emailID + "Credit.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(details);

					fos = new FileOutputStream(emailID + "Wallet.dat");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(wallet);
					
					System.out.println("Current balance in "+ bankName+ " after buying:" +currentBalance);


					return true;
				} else {
					System.out.println("No balance in bank");
					return false;
				}
			}

		}
		return false;
	}

	public boolean sellCurrency(String bankName, double amount, double quantity, String currency, Payment payment)
			throws ClassNotFoundException, IOException {

		HashMap<String, Double> details;

		if (payment instanceof BankAccount) {
			BankAccount ba = (BankAccount) payment;
			details = ba.getBankAccount();

			boolean commit=sellCommit(quantity, amount, details, currency, bankName);
			writeFile(details, emailID + "Bank.dat");

			return commit;
		}

		else if (payment instanceof CreditCard) {
			CreditCard ca = (CreditCard) payment;
			details = ca.getCardAccount();

			boolean commit=sellCommit(quantity, amount, details, currency, bankName);
			writeFile(details, emailID + "Credit.dat");

			return commit;
		}
		return false;
	}

	public void convert(double quantity, String currency1, String currency2, double toQuantity)
			throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(emailID + "Wallet.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		wallet = (Wallet[]) ois.readObject();

		if (currency1.equalsIgnoreCase("bitcoin") && currency2.equalsIgnoreCase("ethereum")) {
			wallet[1].addCurrency(toQuantity);
			wallet[0].subCurrency(quantity);
		}
		if (currency1.equalsIgnoreCase("bitcoin") && currency2.equalsIgnoreCase("litecoin")) {
			wallet[2].addCurrency(toQuantity);
			wallet[0].subCurrency(quantity);
		}
		if (currency1.equalsIgnoreCase("ethereum") && currency2.equalsIgnoreCase("bitcoin")) {
			wallet[0].addCurrency(toQuantity);
			wallet[1].subCurrency(quantity);

		}

		if (currency1.equalsIgnoreCase("ethereum") && currency2.equalsIgnoreCase("litecoin")) {
			wallet[2].addCurrency(toQuantity);
			wallet[1].subCurrency(quantity);

		}

		if (currency1.equalsIgnoreCase("litecoin") && currency2.equalsIgnoreCase("bitcoin")) {
			wallet[0].addCurrency(toQuantity);
			wallet[2].subCurrency(quantity);

		}

		if (currency1.equalsIgnoreCase("litecoin") && currency2.equalsIgnoreCase("ethereum")) {
			wallet[1].addCurrency(toQuantity);
			wallet[2].subCurrency(quantity);

		}
		FileOutputStream fos = new FileOutputStream(emailID + "Wallet.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(wallet);

	}

	@Override
	public int compare(Transaction t1, Transaction t2) {
		return (t1.transactionAmount.compareTo(t2.transactionAmount));
	}

	@Override
	public int compareTo(Transaction t) {
		return (this.transactionQuantity.compareTo(t.transactionQuantity));
	}

	public String toString() {

		return "ID: "+ transactionId + " Currency: " + transactionCurrency + " Quantity: " + transactionQuantity + " Amount: " + transactionAmount + " Payment Type: "
				+ paymentType + " Trransaction Type: " + transactionType;
	}

}
