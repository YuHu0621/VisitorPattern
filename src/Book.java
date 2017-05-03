/**
 * Concrete class book that implements visitable
 * 
 * @author yuhu
 *
 */
public class Book implements Visitable {
	private String iSBN;
	private double weight;
	private double price;
	private String title;

	/**
	 * Constructor
	 * 
	 * @param iSBN
	 *            isbn number
	 * @param weight
	 *            weight of the book
	 * @param price
	 *            price
	 * @param title
	 *            title
	 * 
	 */
	public Book(String iSBN, double weight, double price, String title) {
		this.iSBN = iSBN;
		this.weight = weight;
		this.price = price;
		this.title = title;
	}

	/**
	 * getter method
	 * 
	 * @return return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * getter method
	 * 
	 * @return return ISBN number
	 */
	public String getISBN() {
		return iSBN;
	}

	/**
	 * getter method
	 * 
	 * @return return weight.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * getter method
	 * 
	 * @return return price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * toString print out the information of the book.
	 */
	public String toString() {
		return "ISBN: " + iSBN + ". Title: " + title + ". Book price: " + price + ". Weight: " + weight;
	}

	@Override
	/**
	 * visitor pattern. call visitor
	 */
	public double accept(Visitor visitor) {
		return visitor.visit(this);

	}

}
