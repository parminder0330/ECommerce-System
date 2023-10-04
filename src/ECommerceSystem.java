//Parminder Singh Khehra
//501082375

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    private TreeMap<String, Product> products = new TreeMap<String, Product>();
    
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = "ERROR";
    
    // Random number generator
    Random random = new Random();
    //reads the file and throws filenotfounderror if file not found and exits systems
    public ECommerceSystem() throws FileNotFoundException
    {
    	try {
		ArrayList<Product>list = new ArrayList<Product>();
    	File my_file = new File("products.txt");
    	String name = "";
    	double price = 0.0;
    	int stock = 0;
    	int paperback = 0;
    	int hardcover = 0;
    	int count = 1;
    	Product.Category cate = null;
    	String id = "";
    	String title = "";
    	String author = "";
    	int year = 0;
    	Scanner test = new Scanner(my_file);
    	boolean bookss = false ;
    	while(test.hasNextLine()) {
    		String te = test.nextLine();    		
    		if(count == 1&& te.contains("BOOKS") ) {    			
    			bookss = true;
    			cate = Product.Category.valueOf(te);
    			count ++;
    	    }
    		if ( count == 2 && bookss) {
    			name  = test.nextLine();
    			count++;    	
    		}    		
    		if ( count == 3 && bookss) {
    			price = Double.parseDouble(test.nextLine());
    			count++;   
    		}
    		if ( count == 4 && bookss) {
    			paperback = test.nextInt();
    			hardcover = test.nextInt();
    			count++; 
    		}
    		if ( count == 5 && bookss) {    	
    			String tee = test.nextLine();
    			String tee1 = test.nextLine();
    			String[] splitt = tee1.split(":"); 
    			int count11 = 0;    			
    			title = splitt[0];    			
    			author = splitt[1];    		
    			year = Integer.parseInt(splitt[2]);
    			id = generateProductId();
    			count = 1;
    			products.put(id,new Book(name, id, price, paperback, hardcover, title, author,year));
    			bookss = false;
    		}  		    		    		
    		if(count == 1&& !te.contains("BOOKS") ) {    			
    			bookss = false;
    			cate = Product.Category.valueOf(te);
    			count ++;
    		}    		
    		if ( count == 2 && !bookss) {
    			name  = test.nextLine();
    			count++;
    		}    		
    		if ( count == 3 && !bookss) {
    			price = Double.parseDouble(test.nextLine());
    			count++;    		
    		}
    		if ( count == 4 && !bookss) {
    			stock = Integer.parseInt(test.nextLine());
    			count++;    		
    		}
    		if ( count == 5 && !bookss) {
    			
    			count = 1;
    			id = generateProductId();
    			te = test.nextLine();
    			products.put(id, new Product(name, id, price, stock, cate));  //creates a map for products  		
    		}    				
    	}}
    	catch(FileNotFoundException exception){
            System.out.println("Error processing file: " + exception);
            System.exit(0);
        	}
        	
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }    
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    public String getErrorMessage()
    {
    	return errMsg;
    }
    
    public void printAllProducts()
    {
    	for (Product p : products.values())
    		p.print();
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {    	
    	for (Product b : products.values())
    		if (b.getCategory() == Product.Category.BOOKS) {
     			b.print();
    		}    		
    }    
    // Print all current orders
    public void printAllOrders()
    {
    	for (ProductOrder o : orders)
    		o.print();
    }
 // Print all books by author bonus part
    public void booksByAuthor(String author)
    {
    	ArrayList<Book> books = new ArrayList<Book>();
    	for (Product b : products.values())
    		if (b.getCategory() == Product.Category.BOOKS) {
    		    {
    			if (((Book) b).getAuthor().contains(author))
     			books.add((Book) b);
    		}
//sorter to print it by year
		    	Comparator<Book> year = new Comparator<Book>() {
		    		public int compare(Book one, Book two) {
		    			if (one.getYear() > two.getYear())
		    				return 1;
		    			else 
		    				return -1;
		    		}
		    	};
		  	  Collections.sort(books, year);
		    	}    	
    	for (Book b : books) {
    		
     			b.print();
    		}   		
    }    
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for (ProductOrder s : shippedOrders)
    		s.print();
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c : customers)
    		c.print();
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId) throws UnknownCustomerException
    {
    		boolean check11 = false;
    	for (int i = 0; i < customers.size(); i++) {
    		if (customers.get(i).getId().contains(customerId)) {
    			System.out.println("Current Orders of Customer " + customerId);
    			for (int j = 0; j < orders.size(); j++) {
    				//print current orders
    				if (customerId.contains(orders.get(j).getCustomer().getId())) {    					
    					orders.get(j).print();
    					check11 = true;
    				}
    			}
    			System.out.println("\nShipped Orders of Customer " + customerId);    			
    			for (int k = 0; k < shippedOrders.size(); k++) {
    				
    				if (customerId.contains(shippedOrders.get(k).getCustomer().getId())) {
    					//print shipped orders
    					shippedOrders.get(k).print();   
    					check11 = true;
    				}
    			}    			
    		}    		
    	}    	    
    	if(check11 == false) {
			throw new UnknownCustomerException("Invalid Customer ID"); 
    	}
    }     
    //Add to cart method to add products in cart objects for each customer
    public String addToCart(String productId, String customerId, String productOptions) throws UnknownCustomerException, UnknownProductException, InvalidProductOptionsException, ProductOutOfStockException
    {
    	boolean check = false;
    	Customer cust1 = null;    	
    	for (int i = 0; i < customers.size(); i++) {    		
    		if (customerId.contains(customers.get(i).getId())) {
    		//checks for customer id
    		    cust1 = customers.get(i);
    			check = true;
      		}
   		   	}
    	if (check == false) {    	
    		throw new UnknownCustomerException("Unknown Customer");
    	} 
        boolean check1 = false;    	
    	Product prod1 = null;  	
    	//checks for product id through the map
    	Set<String> keySet = products.keySet();
    	for(String key : products.keySet()) {    		
    		if (productId.contains(key)){
    			prod1 = products.get(key);
    			check1 = true;

    		}
    	}
    	if (check1 == false) {
    		throw new UnknownProductException("Unknown Product");
    	}  

    	if (prod1.getCategory() == Product.Category.BOOKS) {
        	boolean check2 = false;    	

        		if (prod1.validOptions(productOptions)) {
        			check2 = true;    				
        	}
            	if (check2 == false) {
            		throw new InvalidProductOptionsException("Invalid Product Options");
            	}  	
    	}  	   	
    	boolean check3 = false;
    	if (prod1.getStockCount(productOptions) > 0) {
    		check3 = true;
    	}
    	else if(check3 = false) {
    		throw new ProductOutOfStockException("Product out of stock");
    	}     
    	// adds to cart
    	CartItem CI = new CartItem(prod1, productOptions);
    	Cart cartyboi = cust1.getCart();    	
    	cartyboi.additem(CI);
    	return (prod1.getName() + " added to cart for " +cust1.getName());    	    	
    }
   public void remCartItem(String customerId, String productId) throws UnknownCustomerException, UnknownProductException
    {
	   //removes an item from a customer's cart
    	boolean bit3 = false;
    	boolean bit4 = false;
    	
    	for (int i = 0; i < customers.size(); i++) {
    		if (customerId.contains(customers.get(i).getId())) {	
    			Customer cust12 = customers.get(i);
    			bit3 =true;
    			Cart cartyy = cust12.getCart();
    			ArrayList<CartItem> itemsList  = cartyy.itemsList();
    				for (int j = 0; j < cartyy.size(); j++) { 				
        		       		        		
        			if(productId.contains(itemsList.get(j).getProductId())) {
        				bit4 = true;
        			cartyy.remove(itemsList.get(j));        			
        			}}
    		}    		   		
    	}
    	if(bit3 == false) {
    		throw new UnknownCustomerException("Invalid customer ID");
    	}
    	if(bit4 == false) {
    		throw new UnknownProductException("Invalid product ID");
    	}
    }   
    //prints customer's cart
    public void printCart(String customerId) throws UnknownCustomerException
    {
    	boolean bit1 = false;
    	for (int i = 0; i < customers.size(); i++) {
    		if (customerId.contains(customers.get(i).getId())) {
    			Customer cust1 = customers.get(i);
    			
    			Cart carty = cust1.getCart();
    			ArrayList<CartItem> itemsList  = carty.itemsList();
    				for (int j = 0; j < carty.size(); j++) {
    				
    				//print current orders
    				  	itemsList.get(j).print();    				  	
    					bit1 = true;
    				}
    			}		    			
    	}
    	if(bit1 == false) {
    	throw new UnknownCustomerException("Invalid Customer");
    	}
    	}
    //order all the items in the cart and then empties the cart
    public void orderItems(String customerId) throws UnknownCustomerException
    {
    	boolean check50 = false;
    	Customer cust20 = null;
    	Product prod20 = null;
    	String productOptions = " ";
    	
       	int new12 = 0;
    	for (int i = 0; i < customers.size(); i++) {
    		if (customerId.contains(customers.get(i).getId())) {    
    			cust20 = customers.get(i);
    			Cart caaart = cust20.getCart();
    			if(caaart.size()>0) {
    				
    		for (int j = 0; j < caaart.size(); j++) {
    			CartItem CII = caaart.itemsList().get(j);    		
    					prod20 = CII.getProduct();
    					productOptions = CII.getProductO();    	    		
    	    			check50 = true;
    	    			String orderNumber = generateOrderNumber();
    	    	    	ProductOrder test2 = new ProductOrder(orderNumber, prod20, cust20, productOptions);
    	    	    	orders.add(test2);
    	    	    	prod20.soldOne();
    	    	    	prod20 = null;
    	    	    	new12++;
    	    		}}
    			
    			for(int k = new12-1; k >= 0 ; k--) {
    				CartItem CII = caaart.itemsList().get(k);    		
					prod20 = CII.getProduct();
    	    		caaart.remove(CII);
    	    		
    	    	}
    		}
      		}
    		if(check50 == false) {
    			throw new UnknownCustomerException("Invalid Customer");
    		}   	
    	}    
    //orders the product
    public String orderProduct(String productId, String customerId, String productOptions) throws UnknownCustomerException, UnknownProductException, InvalidProductOptionsException, ProductOutOfStockException
    {
    	boolean found = false;
    	Customer custn = null;
    	
    	for (int i = 0; i < customers.size(); i++) {    		
    		if (customerId.contains(customers.get(i).getId())) {
    		//checks for customer id
    		    custn = customers.get(i);
    			found = true;    			
    		}    		
    	}
    	if (found == false) {    	
    		throw new UnknownCustomerException("Unknown Customer");
    	}    	
    	boolean found1 = false;    	
    	Product prodn = null;
    	
    	Set<String> keySet = products.keySet();
    	for(String key : products.keySet()) {    		
    		if (productId.contains(key)){
    			prodn = products.get(key);
    			found1 = true;

    		}
    	}
    	if (found1 == false) {
    		throw new UnknownProductException("Unknown Product");
    	}
 
    	boolean found2 = false;    	
    	// valid options check
    		if (prodn.validOptions(productOptions)) {
    			found2 = true;    				
    	}
    	if (found2 == false) {
    		throw new InvalidProductOptionsException("Invalid Product Options");
    	}

    	boolean found3 = false;
    	if (prodn.getStockCount(productOptions) > 0) {
    		found3 = true;
    	}
    	else if(found3 = false) {
    		throw new ProductOutOfStockException("Product out of stock");
    	}

// creates the actual order
    	String orderNumber = generateOrderNumber();
    	ProductOrder test2 = new ProductOrder(orderNumber, prodn, custn, productOptions);
    	orders.add(test2);
    	prodn.soldOne();
    	prodn.reduceStockCount(productOptions);
    	System.out.println("Order #" +orderNumber);
    	return "Order #" +orderNumber;    	
    }
    //gives out stats for products, tells us what products have been ordered most
    public void stats() {
    	
    	Set<String> keySet = products.keySet();
    	for(String key : products.keySet()) {   
    		
    		int x = products.get(key).soldNo();
    		String name = products.get(key).getName();
    		String id =products.get(key).getId();
    		System.out.println("Product Name "+ name + " Product Id "+ id + " Number of times Ordered "+ x);
    	}    		
    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address) throws InvalidCustomerNameException, InvalidCustomerAddressException
    {
    	if (name == "" || name == null) {
    		throw new InvalidCustomerNameException("Invalid Customer Name");
    		//checks for name
    	}
    	if (address == "" || address == null) {
    		throw new InvalidCustomerAddressException("Invalid Customer Address");
    		//checks for address    	
    	}
    	else {
    		  		  		
    		customers.add(new Customer(generateCustomerId(), name, address)); 
    		
    	}
    }
    //ships the order
    public ProductOrder shipOrder(String orderNumber) throws InvalidOrderNumberException
    {
    	
    	for (int i = 0; i < orders.size(); i++) {    		
    		if (orderNumber.contains(orders.get(i).getOrderNumber())) {		   			
    	    	shippedOrders.add(orders.get(i));    	    	
    	    	orders.remove(i);
    			for (int j = 0; j < shippedOrders.size(); j++) {
    	    		if (orderNumber.contains(shippedOrders.get(j).getOrderNumber())) {		
    	    			shippedOrders.get(j).print();
    	    				return shippedOrders.get(j);
    	    						}
    	    		// ships order
      			}
    		}
    	}
    	throw new InvalidOrderNumberException("Order number not found");  	
      }
//cancels the order
    public void cancelOrder(String orderNumber) throws InvalidOrderNumberException
    {
    	boolean check10 = false;
    	for (int i = 0; i < orders.size(); i++) {
    		if (orderNumber.contains(orders.get(i).getOrderNumber())) {		
    			
    			orders.remove(i);
        		System.out.println("Order " +orderNumber+ " cancelled");
    			   check10 = true;			
    			
    		}
    		 
    }
    	if(check10 == false) {
		throw new InvalidOrderNumberException("Order number not found");
    	}
 	}
       // Sort and print products by increasing price
    public void printByPrice()
    {
    	ArrayList<Product> temp = new ArrayList<Product>();	
    	for (Product pa : products.values())
    		temp.add(pa);
    	Comparator<Product> price = new Comparator<Product>() {
    		public int compare(Product one, Product two) {
    			if (one.getPrice() > two.getPrice())
    				return 1;
    			else 
    				return -1;
    		}
    	};
  	  Collections.sort(temp, price);
  	  for(Product pa1 : temp) {
  		pa1.print();
  	  }
  	  
    	}
    
    
    // Sort and print products alphabetically by product name
    public void printByName()
    {
    	
    	ArrayList<Product> temp1 = new ArrayList<Product>();	
    	for (Product paa : products.values())
    		temp1.add(paa);
    	Comparator<Product> name = new Comparator<Product>() {
    		public int compare(Product one, Product two) {
    			return one.getName().compareTo(two.getName());
    		}
    	};
  	  Collections.sort(temp1, name);
  	  for(Product pa11 : temp1) {
  		pa11.print();
  	  }
    }
    
        
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
    	Comparator<Customer> name = new Comparator<Customer>() {
    		public int compare(Customer one, Customer two) {
    			return one.getName().compareTo(two.getName());
    		}
    	};
  	  Collections.sort(customers, name);
    }
    //method to check if product type is book and then asks for productoptions
	public boolean checkCategory(String mykey) {
		Product prod1 = null;
		Set<String> keySet = products.keySet();
		for(String key : products.keySet()) {   
			if (mykey.contains(key)){
				prod1 = products.get(mykey);
				if (prod1.getCategory() == Product.Category.BOOKS) {
					
			return true;
			}
		}				
	}
		return false;		
	}
}

//All exception classes defined below

 class UnknownCustomerException extends RuntimeException { 
	public UnknownCustomerException(String errorMessage) {
        super(errorMessage);
    }
}
 class UnknownProductException extends RuntimeException { 
	public UnknownProductException(String errorMessage) {
        super(errorMessage);
    }
}
 
 class InvalidProductOptionsException extends RuntimeException { 
	public InvalidProductOptionsException(String errorMessage) {
        super(errorMessage);
    }
}
 
 class ProductOutOfStockException extends RuntimeException { 
	public ProductOutOfStockException(String errorMessage) {
        super(errorMessage);
    }
}
 
 class InvalidCustomerNameException extends RuntimeException { 
	public InvalidCustomerNameException(String errorMessage) {
        super(errorMessage);
    }
}
 
 class InvalidCustomerAddressException extends RuntimeException { 
	public InvalidCustomerAddressException(String errorMessage) {
        super(errorMessage);
    }
}
 
 class InvalidOrderNumberException extends RuntimeException { 
	public InvalidOrderNumberException(String errorMessage) {
        super(errorMessage);
    }
}
 