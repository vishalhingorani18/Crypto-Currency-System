package com.exchange;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class CryptoCurrency extends Thread implements Serializable
{
   private double price;
   private Date time;
   private int percentDifference;
   private String currencyName;
   private int multiplier; 
   private double currentSeconds;
   private double recentSeconds=0;
   private double recentPrice=0;
   private Calendar cal;
   private SimpleDateFormat sdf;
   private  Random rand = new Random();
   
   
   public CryptoCurrency(String currencyName, int multiplier) 
   {
	   this.currencyName=currencyName;
	   this.multiplier=multiplier;
   }
   
   public void run()
   {
	   cal = Calendar.getInstance();
       sdf = new SimpleDateFormat( );
	   recentSeconds=cal.get(Calendar.SECOND);
	   recentPrice = rand.nextInt(multiplier) + 1;
	   
	   
	   
	   
   
	   while(true)
	   {
		   
		   cal = Calendar.getInstance();
	       sdf = new SimpleDateFormat("HH:mm:ss");
	      
		   price = rand.nextInt(multiplier) + 1;
		  
		   currentSeconds=cal.get(Calendar.SECOND);
		 
		   if(Math.abs((currentSeconds-recentSeconds))>4)
	       {
			
	    	   percentDifference=(int) (((price-recentPrice)/recentPrice)*100);
	    	   recentSeconds=currentSeconds;
	    	   recentPrice=price;
	    	   //System.out.println(percentDifference+"%");
	       }
		   
		   try 
		   {
			Thread.sleep(2000);
		   } 
		   catch (InterruptedException e) 
		   {
			e.printStackTrace();
		   }
		   
		   
		   System.out.println(currencyName+" price: "+price);
	   }
   }

public double getPrice()
{
	return price;
}

public Date getTime() {
	return time;
}

public int getPercentDifference() {
	return percentDifference;
}



public String getCurrencyName() {
	return currencyName;
}

   
   
}
