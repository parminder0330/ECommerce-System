//Parminder Singh Khehra
//501082375

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		try {
		ECommerceSystem amazon = new ECommerceSystem();
		

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("BOOKSBYAUTHOR"))	// List all books for author bonus part
			{
				String author = "";
				System.out.println("Please provide author's name");
				if (scanner.hasNextLine()) {
					author = scanner.nextLine();
				amazon.booksByAuthor(author);
				}
			else {
				System.out.println("Author not provided");
				}
				 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				try {
				amazon.createCustomer(name, address);
				}				
				catch(InvalidCustomerNameException e)			
				{
					System.out.println(e.getMessage());
				}				
				catch(InvalidCustomerAddressException e)				
				{
					System.out.println(e.getMessage());				
				}
				}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
        
					System.out.print("Order Number: ");
					// Get order number from scanner
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();
					// Ship order to customer (see ECommerceSystem for the correct method to use)
				    try{
				    	amazon.shipOrder(orderNumber);
				    }
				    catch(InvalidOrderNumberException e) {
				    	System.out.println(e.getMessage());
				    }
				
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
				try {
				amazon.printOrderHistory(customerId);
				}
				catch(UnknownCustomerException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			else if (action.equalsIgnoreCase("STATS")) // List all products with stats about how many times it has been ordered
			{
				String productId = "";			
				try {
					amazon.stats();
				}
				catch(UnknownProductException e) {
					System.out.println(e.getMessage());
				}
			}
			
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";
			

				System.out.print("Product Id: ");
			  // Get product Id from scanner
				if(scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				try { 
				String oNo= amazon.orderProduct(productId, customerId, " ");
				}
				catch(UnknownCustomerException e) {
					System.out.println(e.getMessage());
				}
				catch(UnknownProductException e) {
					System.out.println(e.getMessage());
				}
				catch(InvalidProductOptionsException e) {
					System.out.println(e.getMessage());
				}
				catch(ProductOutOfStockException e) {
					System.out.println(e.getMessage());
				}
				// Print Order Number string returned from method in ECommerceSystem
				//not needed, print statement is built into method
			}
			else if (action.equalsIgnoreCase("ADDTOCART")) // adds to cart for a customer
			{
				String productId = "";
				String customerId = "";
				String productOptions = " ";
				

				System.out.print("Product Id: ");
			  // Get product Id from scanner
				if(scanner.hasNextLine()) 
					productId = scanner.nextLine();
		    	   		
		    		if (amazon.checkCategory(productId)) {
		    			System.out.print("Product Options(Hardcover,Paperback): ");
		  				if(scanner.hasNextLine()) { 
		  					productOptions = scanner.nextLine();
		    		}}
				
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				
				 try { 
					 String oNo= amazon.addToCart(productId, customerId, productOptions);
					 System.out.println(oNo);
						}
						catch(UnknownCustomerException e) {
							System.out.println(e.getMessage());
						}
						catch(UnknownProductException e) {
							System.out.println(e.getMessage());
						}
						catch(InvalidProductOptionsException e) {
							System.out.println(e.getMessage());
						}
						catch(ProductOutOfStockException e) {
							System.out.println(e.getMessage());
						}
				// Print Order Number string returned from method in ECommerceSystem
				 
				//not needed, print statement is built into method
			}
			else if (action.equalsIgnoreCase("PRINTCART")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();				
				try {
				amazon.printCart(customerId);
				}								
				catch(UnknownCustomerException e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("REMCARTITEM")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";
				String productId = "";

				System.out.print("Customer Id: ");
				
				// Get customer Id from scanner
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();	
				System.out.print("Product Id: ");
				if(scanner.hasNextLine())
					productId = scanner.nextLine();
				try {
				amazon.remCartItem(customerId, productId);
				System.out.println("Product "+productId+ " removed for customer ID "+ customerId);
				}								
				catch(UnknownCustomerException e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDERITEMS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";
				String productId = "";

				System.out.print("Customer Id: ");
				
				// Get customer Id from scanner
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();	

				try {
				amazon.orderItems(customerId);
				System.out.println("Products in cart have been ordered for customer ID "+ customerId);
				}								
				catch(UnknownCustomerException e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if(scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book format and store in options string
				if(scanner.hasNextLine())
					options = scanner.nextLine();

				try { 
					String orderb = amazon.orderProduct(productId, customerId, options);
					}
					catch(UnknownCustomerException e) {
						System.out.println(e.getMessage());
					}
					catch(UnknownProductException e) {
						System.out.println(e.getMessage());
					}
					catch(InvalidProductOptionsException e) {
						System.out.println(e.getMessage());
					}
					catch(ProductOutOfStockException e) {
						System.out.println(e.getMessage());
					}
							}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productId = "";
				String customerId = "";
				String options = "";
				
				System.out.print("Product Id: ");
				// get product id
				if(scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if(scanner.hasNextLine())
					customerId = scanner.nextLine();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				if(scanner.hasNextLine())
					options = scanner.nextLine();
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if(scanner.hasNextLine())
					options += scanner.nextLine();
				
				//order shoes
				
				try { 
					String oboo = amazon.orderProduct(productId, customerId, options);
					}
					catch(UnknownCustomerException e) {
						System.out.println(e.getMessage());
					}
					catch(UnknownProductException e) {
						System.out.println(e.getMessage());
					}
					catch(InvalidProductOptionsException e) {
						System.out.println(e.getMessage());
					}
					catch(ProductOutOfStockException e) {
						System.out.println(e.getMessage());
					}
			}
			
			
			
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				if(scanner.hasNextLine())
					orderNumber = scanner.nextLine();
				// cancel order. Check for error
				try {
				amazon.cancelOrder(orderNumber);
				}
				catch(InvalidOrderNumberException e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort and print products by price
			{
				amazon.printByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort and print products by name (alphabetic)
			{
				amazon.printByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			System.out.print("\n>");
		}
	}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
}
}



