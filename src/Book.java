//Parminder Singh Khehra
//501082375



/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product 
{
  private String author;
  private String title;
  private int year;
  
  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;
  
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author,int year)
  {
	  super(name, id, price, 100000, Product.Category.BOOKS);
	  this.title = title;
	  this.author = author;
	  this.paperbackStock = paperbackStock;
	  this.hardcoverStock = hardcoverStock;
	  this.year = year;
	  // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	 // Set category to BOOKS 
  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions) //checks for book options
  {
	  if (productOptions.contains("Paperback") || productOptions.contains("Hardcover") || productOptions.contains("EBook") || productOptions.contains(" ")) {
		  return true;
	  }
	  else {
		  return false;
	  }
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false
  	
  }
  public String getAuthor() //bonus part for author year
	{
		return this.author;
	}
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
	  if (productOptions == "Paperback") {
		  return paperbackStock;
	  }
	  if (productOptions == "Hardcover") {
		  return hardcoverStock;
	  }
	  if (productOptions == "EBook") {
		  return super.getStockCount(productOptions);
	  }
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.
  	return 1;
	}
  public int getYear() //bonus part for author year
  {
	  return year;
  }
  public void setStockCount(int stockCount, String productOptions)
	{
	  if (productOptions == "Paperback") {
		  stockCount = paperbackStock;
	  }
	  if (productOptions == "Hardcover") {
		  stockCount = hardcoverStock;
	  }
	  if (productOptions == "EBook") {
		  super.setStockCount(stockCount, productOptions);
	  }
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
	  if (productOptions == "Paperback" ) {
		  paperbackStock--;
		  	  }
	  if (productOptions == "Hardcover") {
		  hardcoverStock--;
	  }
	  if (productOptions == "EBook") {
		  super.reduceStockCount(productOptions);
	  }
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
	  System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f Book Title: %-20s Author: %-2s Year:  %-2s ", this.getId(), this.getCategory(), this.getName(), this.getPrice(), title, author, year);
  }
}
