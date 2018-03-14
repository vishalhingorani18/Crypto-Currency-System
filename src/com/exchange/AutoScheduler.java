package com.exchange;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AutoScheduler extends Schedule {
	
	
	private boolean investmentType;
	private double[] percentageDivision = {0.0,0.0,0.0};
	private double[] growthDivision = {0.0,0.0,0.0};;
	private boolean roi;
	private double percentROI;
	private double increaseAmountPercentage;
	private boolean divideInvestment;
	private double[] amountDivision = {0.0,0.0,0.0};
	private double increaseROIAmount;
	private boolean [] growthFlag = {false, false, false};
	private String bankname;
	private double [] quantity= {0.0,0.0,0.0} ; 
	private CurrencySystem system;
	private CryptoCurrency [] currency;
	private String paymentType;
	private double originalAmount;
	
	
	public String getPaymentType() {
		return paymentType;
	}
	
	public double getOriginalAmount() {
		return originalAmount;
	}
	
	public void setOriginalAmount(double amount) {
		originalAmount = getAmount();
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public String getBankname() {
		return bankname;
	}
	

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public double getAmount1() {
		return amountDivision[0];
	}
	public double getAmount2() {
		return amountDivision[1];
	}
	public double getAmount3() {
		return amountDivision[2];
	}
	
	public boolean getRoi() {
		return roi;
	}

	public void setRoi(boolean roi) {
		this.roi = roi;
	}

	
	public boolean[] getGrowthFlag() {
		return growthFlag;
	}

	public void setGrowthFlag(boolean[] growthFlag) {
		this.growthFlag = growthFlag;
	}

	public double getIncreaseROIAmount() {
		return increaseROIAmount;
	}

	public void setIncreaseROIAmount(double increaseROIAmount) {
		this.increaseROIAmount = increaseROIAmount;
	}

	public double[] getAmountDivision() {
		return amountDivision;
	}

	public void setAmountDivision(double[] amountDivision) {
		this.amountDivision = amountDivision;
	}

	public boolean isInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(boolean investmentType) {
		this.investmentType = investmentType;
	}

	public double[] getPercentageDivision() {
		return percentageDivision;
	}

	public void setPercentageDivision(double[] percentageDivision) {
		this.percentageDivision = percentageDivision;
	}

	public double[] getGrowthDivision() {
		return growthDivision;
	}

	public void setGrowthDivision(double[] growthDivision) {
		this.growthDivision = growthDivision;
	}
	public double getQuantity1() {
		return quantity[0];
	}
	public double getQuantity2() {
		return quantity[1];
	}
	public double getQuantity3() {
		return quantity[2];
	}
	public void setQuantity(double[] quantityDivision) {
		this.quantity = quantityDivision;
	}
	public double getPercentROI() {
		return percentROI;
	}

	public void setPercentROI(double percentROI) {
		this.percentROI = percentROI;
	}

	public double getIncreaseROI() {
		return increaseAmountPercentage;
	}

	public void setIncreaseROI(double increaseAmountPercentage) {
		this.increaseAmountPercentage = increaseAmountPercentage;
	}

	public boolean isDivideInvestment() {
		return divideInvestment;
	}

	public void setDivideInvestment(boolean divideInvestment) {
		this.divideInvestment = divideInvestment;
	}
	

	public int getDuration() {
		return duration;
	}


	public AutoScheduler() {
		
	}
	
	public AutoScheduler(double amount, boolean investmentType, boolean divideInvestment,
			double [] percentageDivision, double [] growthDivision, double increaseAmountPercentage,
			 double percentROI, int duration, User user, boolean roi, String name, Date date,CurrencySystem currencySystem,
	CryptoCurrency [] cryptoCurrencies)
	{
			
		
		super(amount, duration, user);
		this.system=currencySystem;
		this.currency=cryptoCurrencies;
		this.investmentType = investmentType;
		this.divideInvestment = divideInvestment;
		this.percentageDivision = percentageDivision;
		this.growthDivision = growthDivision;
		this.increaseAmountPercentage = increaseAmountPercentage;
		this.percentROI = percentROI;
		this.roi = roi;
		this.bankname = name;
		
				
  	  if(bankname.contains("Card"))
		  paymentType="card";
	  else 
		  paymentType="bank";

	
		//if(roi)
		//	increaseInvestment();
		
		if(investmentType)
			autoInvest();
		else {
			percentInvest();
		}
		quantityDivision();
	}
	
	public boolean getInvestmentType() {
		return investmentType;
	}
	public boolean getROI() {
		return roi;
	}
	public void amountDivision() {
		amountDivision[0] = (getAmount() * percentageDivision[0])/100;
		amountDivision[1] = (getAmount() * percentageDivision[1])/100;
		amountDivision[2] = (getAmount() * percentageDivision[2])/100;
	
	}
	
	public void  quantityDivision()
	{
		
		double [] temp= {0.0, 0.0, 0.0};
		
		currency = system.cryptoInfo();
		
			temp[0] = currency[0].getPrice();
			temp[1] = currency[1].getPrice();
			temp[2] = currency[2].getPrice();
		
		quantity[0] = amountDivision[0]/temp[0];
		quantity[1] = amountDivision[1]/temp[1];
		quantity[2] = amountDivision[2]/temp[2];
		

	}
	
	
	public boolean autoInvest() {	
		
		double[] actualPercentage = {currency[0].getPercentDifference(),currency[1].getPercentDifference(),currency[2].getPercentDifference()};

		
		// all positive  50-30-20
		if(actualPercentage[0] > 0 && actualPercentage[1] > 0 && actualPercentage[2] > 0) 
		{
			
			if(actualPercentage[0] > actualPercentage[1] && actualPercentage[0] > actualPercentage[2]){
				percentageDivision[0] = 50;
				if(actualPercentage[1] > actualPercentage[2]){
						percentageDivision[1] = 30;
						percentageDivision[2] = 20;
				}
				else
				{
					percentageDivision[2] = 30;
					percentageDivision[1] = 20;
				}
				
			}
			
			
			else if(actualPercentage[1] > actualPercentage[2] && actualPercentage[1] > actualPercentage[0]){
				percentageDivision[1] = 50;
				if(actualPercentage[0] > actualPercentage[2]){
						percentageDivision[0] = 30;
						percentageDivision[2] = 20;
				}
				else
				{
					percentageDivision[2] = 30;
					percentageDivision[0] = 20;
				}
				
			}
			
			else if(actualPercentage[2] > actualPercentage[0] && actualPercentage[2] > actualPercentage[1]){
				percentageDivision[2] = 50;
				if(actualPercentage[0] > actualPercentage[1]){
						percentageDivision[0] = 30;
						percentageDivision[1] = 20;
				}
				else
				{
					percentageDivision[1] = 30;
					percentageDivision[0] = 20;
				}
				
			}	
			
			//33-33-33
			else if(actualPercentage[0] == actualPercentage[1] && actualPercentage[1]== actualPercentage[2] ){
				percentageDivision[0]= 33.3;
				percentageDivision[1] = 33.3;
				percentageDivision[2] =33.3;
				
			}
			
			//50-25-25
			else if(actualPercentage[0] > actualPercentage[1]  && actualPercentage[0] > actualPercentage[2] && actualPercentage[1]== actualPercentage[2]) {
				percentageDivision[0]= 50;
				percentageDivision[1] = 25;
				percentageDivision[2] =25;
			}
			
			else if(actualPercentage[1] > actualPercentage[0]  && actualPercentage[1] > actualPercentage[2] && actualPercentage[0]== actualPercentage[2]) {
				percentageDivision[0]= 25;
				percentageDivision[1] = 50;
				percentageDivision[2] =25;
			}
			
			else if(actualPercentage[2] > actualPercentage[0]  && actualPercentage[2] > actualPercentage[1] && actualPercentage[0]== actualPercentage[1]) {
				percentageDivision[0]= 25;
				percentageDivision[1] = 25;
				percentageDivision[2] =50;
			}
			
			
			
			//40-40-20
			else if(actualPercentage[0] == actualPercentage[1]  && actualPercentage[0] > actualPercentage[2]) {
				percentageDivision[0]= 40;
				percentageDivision[1] = 40;
				percentageDivision[2] =20;
			}
			
			else if(actualPercentage[0] == actualPercentage[2]  && actualPercentage[0] > actualPercentage[1]) {		
				percentageDivision[0]= 40;
				percentageDivision[1] = 20;
				percentageDivision[2] =40;
			}
			
			else if(actualPercentage[1] == actualPercentage[2]  && actualPercentage[1] > actualPercentage[0]) {		
				percentageDivision[0]= 20;
				percentageDivision[1] = 40;
				percentageDivision[2] =40;
			}
			
		}
		//0-0-0
		else if(actualPercentage[0] <= 0 && actualPercentage[1] <= 0 && actualPercentage[2] <= 0) {
			percentageDivision[0]= 0;
			percentageDivision[1] = 0;
			percentageDivision[2] =0;
		}
		
		//100
		else if(actualPercentage[0] > 0 && actualPercentage[1] <= 0 && actualPercentage[2] <= 0) 
		{
			percentageDivision[0]= 100;
		}
		else if(actualPercentage[0] <= 0 && actualPercentage[1] > 0 && actualPercentage[2] <= 0) 
		{
			percentageDivision[1]= 100;
		}
		else if(actualPercentage[0] <= 0 && actualPercentage[1] <= 0 && actualPercentage[2] > 0) 
		{
			percentageDivision[2]= 100;
		}
		
		
		//60-40
		else if(actualPercentage[0] > 0 && actualPercentage[1] > 0 && actualPercentage[2] <= 0) {
			if(actualPercentage[0] > actualPercentage[1]) {
				percentageDivision[0] = 60;
				percentageDivision[1] = 40;
				
			}
			else if(actualPercentage[1] > actualPercentage[0]) {
				percentageDivision[1] = 60;
				percentageDivision[0] = 40;
				
			}
			
			else if(actualPercentage[0] == actualPercentage[1]) {
				percentageDivision[0] = 50;
				percentageDivision[1] = 50;
				
			}
			
		}
		
		else if(actualPercentage[0] > 0 && actualPercentage[1] <= 0 && actualPercentage[2] > 0) {
			if(actualPercentage[0] > actualPercentage[2]) {
				percentageDivision[0] = 60;
				percentageDivision[2] = 40;
				
			}
			else if(actualPercentage[2] > actualPercentage[0]) {
				percentageDivision[2] = 60;
				percentageDivision[0] = 40;
				
			}
			
			else if(actualPercentage[0] == actualPercentage[2]) {
				percentageDivision[0] = 50;
				percentageDivision[2] = 50;
				
			}
			
		}
		else if(actualPercentage[0] <= 0 && actualPercentage[1] > 0 && actualPercentage[2] > 0) {
			if(actualPercentage[1] > actualPercentage[2]) {
				percentageDivision[1] = 60;
				percentageDivision[2] = 40;
				
			}
			else if(actualPercentage[2] > actualPercentage[1]) {
				percentageDivision[2] = 60;
				percentageDivision[1] = 40;
				
			}
			
			else if(actualPercentage[1] == actualPercentage[2]) {
				percentageDivision[1] = 50;
				percentageDivision[2] = 50;
				
			}
			
		}
		
		for(int i=0;i<percentageDivision.length;i++)
		{
		
			amountDivision[i] = percentageDivision[i] * getAmount() /100;
		}
		
		
	
		return true;
	}
	
	public boolean percentInvest() {
		
		for(int i=0;i<percentageDivision.length;i++)
		{
			amountDivision[i] =( percentageDivision[i] * getAmount()) /100;
		}
		
		double[] actualPercentage = {currency[0].getPercentDifference(),currency[1].getPercentDifference(),currency[2].getPercentDifference()};

		for(int i=0;i<growthFlag.length;i++){
			if(growthDivision[i] < actualPercentage[i]) {
				growthFlag[i] = true;
			}
		}
		
		if(divideInvestment)
		{
			for(int i=0;i<growthFlag.length;i++)
			{
				if(!growthFlag[i])
				{
					amountDivision[i] = 0;
				}
			}
		}
		
	
		return true;
	}
	
	
	public boolean increaseInvestment()
	{
		double temp = (getAmount() * increaseAmountPercentage)/100 + getAmount();
		
		Wallet[] wallet=user.getWallet();
		
		double investment=wallet[0].getInvestment()+wallet[1].getInvestment() +wallet[2].getInvestment();
		double roi=((currency[0].getPrice()+currency[1].getPrice()+currency[2].getPrice()- investment)/investment)*100;

		if(roi > percentROI)
		{
			setAmount(temp) ;
		}
		
		
		
		return true;
	}
	
	
	

}
