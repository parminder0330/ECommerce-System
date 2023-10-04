//Parminder Singh Khehra
//501082375

public class CartItem {
	private Product product;
	private String productOptions;	
	//cartitem class
	public CartItem(Product product, String productOptions) {
		this.product = product;
		this.productOptions = productOptions;		
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public String getProductO()
	{
		return this.productOptions;
	}
	public String getProductId() {
		return this.product.getId();
	}
	
// prints cart items
	public void print()
	{
		System.out.printf("\nProduct Id: %3s Product Name: %12s ", product.getId(), product.getName());
	}

}
