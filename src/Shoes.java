//Parminder Singh Khehra
//501082375

import java.util.ArrayList;

public class Shoes extends Product 
{
int a6Blackstock;
int a7Blackstock;
int a8Blackstock;
int a9Blackstock;
int a10Blackstock;
int a6Brownstock;
int a7Brownstock;
int a8Brownstock;
int a9Brownstock;
int a10Brownstock;
// shoes class which extends product

//creates arrays
ArrayList<String> sizes = new ArrayList<String>();
ArrayList<Integer> stock = new ArrayList<Integer>();
  
  public Shoes(String name, String id,int stock, int a6blackstock, int a7blackstock, int a8blackstock, int a9blackstock, int a10blackstock, int a6brownstock, int a7brownstock, int a8brownstock, int a9brownstock, int a10brownstock, double price)
  {
	  super(name, id, price, stock, Product.Category.SHOES);
	 
	  // creates all variables
	  this.a6Blackstock = a6blackstock;
	  this.a7Blackstock = a7blackstock;
	  this.a8Blackstock = a8blackstock;
	  this.a9Blackstock = a9blackstock;
	  this.a10Blackstock = a10blackstock;
	  this.a6Brownstock = a6brownstock;
	  this.a7Brownstock = a7brownstock;
	  this.a8Brownstock = a8brownstock;
	  this.a9Brownstock = a9brownstock;
	  this.a10Brownstock = a10brownstock;
	  
	  	sizes.add("6Brown");
	  	sizes.add("7Brown");
	  	sizes.add("8Brown");
	  	sizes.add("9Brown");
	   	sizes.add("10Brown");
	   	sizes.add("6Black");
	   	sizes.add("7Black");
	   	sizes.add("8Black");
	   	sizes.add("9Black");
	   	sizes.add("10Black");
	  
  }

  
  public boolean validOptions(String productOptions)
  {
	  //checks for validity
	  for (int i = 0; i <sizes.size(); i++) {
		  if (productOptions.contains(sizes.get(i))) {
			  return true;
		  }
	  }
	   	return false;
 
  }
  
  public int getStockCount(String productOptions)
  //stock count
	{
	  if (sizes.contains(productOptions)) {
		  if (productOptions.contains( "6Brown")) {
			  return a6Brownstock;
		  }
		  if (productOptions.contains( "7Brown")) {
			  return a7Brownstock;
		  }		
		  if (productOptions.contains( "8Brown")) {
			  return a8Brownstock;
		  }
		  if (productOptions.contains( "9Brown")) {
			  return a9Brownstock;
		  }
		  if (productOptions.contains( "10Brown")) {
			  return a10Brownstock;
		  }
		  if (productOptions.contains( "6Black")) {
			  return a6Blackstock;
		  }
		  if (productOptions.contains( "7Black")) {
			  return a7Blackstock;
		  }
		  if (productOptions.contains( "8Black")) {
			  return a8Blackstock;
		  }
		  if (productOptions.contains( "9Black")) {
			  return a9Blackstock;
		  }
		  if (productOptions.contains( "10Black")) {
			  return a10Blackstock;
		  }		  
	  }
	  
	return 1;
	}

public void setStockCount(int stockCount, String productOptions)
	{
	  if (sizes.contains(productOptions)) {
		  if (productOptions.contains( "6Brown")) {
			  a6Brownstock = stockCount;
		  }
		  if (productOptions.contains( "7Brown")) {
			   a7Brownstock = stockCount;
		  }		
		  if (productOptions.contains( "8Brown")) {
			   a8Brownstock = stockCount;
		  }
		  if (productOptions.contains( "9Brown")) {
			   a9Brownstock = stockCount;
		  }
		  if (productOptions.contains( "10Brown")) {
			   a10Brownstock = stockCount;
		  }
		  if (productOptions.contains( "6Black")) {
			   a6Blackstock = stockCount;
		  }
		  if (productOptions.contains( "7Black")) {
			   a7Blackstock = stockCount;
		  }
		  if (productOptions.contains( "8Black")) {
			   a8Blackstock = stockCount;
		  }
		  if (productOptions.contains( "9Black")) {
			   a9Blackstock = stockCount;
		  }
		  if (productOptions.contains( "10Black")) {
			   a10Blackstock = stockCount;
		  }
		  	}
		  
	}

public void reduceStockCount(String productOptions)
	{
	  if (sizes.contains(productOptions)) {
		  if (productOptions.contains( "6Brown")) {
			  a6Brownstock --;
		  }
		  if (productOptions.contains( "7Brown")) {
			   a7Brownstock --;
		  }		
		  if (productOptions.contains( "8Brown")) {
			   a8Brownstock --;
		  }
		  if (productOptions.contains( "9Brown")) {
			   a9Brownstock --;
		  }
		  if (productOptions.contains( "10Brown")) {
			   a10Brownstock --;
		  }
		  if (productOptions.contains( "6Black")) {
			   a6Blackstock --;
		  }
		  if (productOptions.contains( "7Black")) {
			   a7Blackstock --;
		  }
		  if (productOptions.contains( "8Black")) {
			   a8Blackstock --;
		  }
		  if (productOptions.contains( "9Black")){
			   a9Blackstock --;
		  }
		  if (productOptions.contains( "10Black")) {
			   a10Blackstock --;
		  }
		  	}
	  

	}

  public void print()
  {
  	//prints shoe order
	  System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f ", this.getId(), this.getCategory(), this.getName(), this.getPrice());
  }
}