import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class test {
	private int productId = 700;
	
	Random random = new Random();
	
    private String generateProductId()
    {
    	return "" + productId++;
    }
	
	public static void main(String[] args) throws FileNotFoundException {
	

	    
		HashMap<String, Product> produc = new HashMap<String, Product>();
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
    			id = "5";
    			count = 1;
    			produc.put(id,new Book(name, id, price, paperback, hardcover, title, author,year));
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
    			id = "5";
    			count = 1;
    			te = test.nextLine();
    			produc.put(name, new Product(name, id, price, stock, cate));    		
    		}    				
    	}System.out.println(produc);    
    	}
	}