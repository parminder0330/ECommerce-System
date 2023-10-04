//Parminder Singh Khehra
//501082375

import java.util.ArrayList;

public class Cart {

 
	private String productId; 
	private String name;
	private String orderNumber;
	private Product product;	
	private Customer customer;
	private ArrayList<CartItem> items = new ArrayList<CartItem>();
	
	//cart class
	public Cart( String productId)
	{
		this.productId = productId;
	}
	
	public Product getProduct()
	{
		return product;
	}

	public String getproductId()
	{
		return productId;
	}
	public void setproductId(String productId)
	{
		this.productId = productId;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getOrderNumber()
	{
		return orderNumber;
	}
	//checks equality
	public boolean equals(Object other)
	{
		Cart otherC = (Cart) other;
		return this.productId.equals(otherC.productId);
	}
	//adds item to items list
	public void additem(CartItem i) {
		items.add(i);
	}
	//size of the list
	public int size() {
		return items.size();
	}
	//list of cart items
	public ArrayList<CartItem> itemsList(){
		return items;
	}
	//removes item from cart
	public void remove(CartItem i) {
		for(int j =0; j <items.size(); j++) {
			if(i.equals(items.get(j))) {
				items.remove(i);
			}
		}
	
	}
}
