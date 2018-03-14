package com.exchange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CreditCard extends Payment {

	private HashMap<String, Double> cardAccount;
	private String emailID;

	public CreditCard(String emailID) throws IOException {
		userName = "user";
		password = "user";
		this.emailID = emailID;
		File f = new File(emailID + "Credit.dat");

		if (!f.exists()) 
		{

			cardAccount = new HashMap<String, Double>();
			FileOutputStream fos = new FileOutputStream(emailID + "Credit.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// cardAccount.put("N",0.0);

			oos.writeObject(cardAccount);

		}
		
		System.out.println("Total amount available in credit card: $20,000");


	}

	public void setcreditCardAccount(String cardName) throws IOException, ClassNotFoundException 
	{
		FileInputStream fis=new FileInputStream(emailID+"Credit.dat");
		ObjectInputStream ois=new ObjectInputStream(fis);
		cardAccount=(HashMap<String, Double>)ois.readObject();

		cardAccount.put(cardName, 20000.00);
		FileOutputStream fos = new FileOutputStream(emailID + "Credit.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(cardAccount);
	}

	public HashMap<String, Double> getCardAccount() throws IOException, ClassNotFoundException {

		FileInputStream fis = new FileInputStream(emailID + "Credit.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		cardAccount = (HashMap<String, Double>) ois.readObject();

		return cardAccount;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
