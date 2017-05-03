/**
 * Concrete class clothing that implements visitable.
 * 
 * @author yuhu
 *
 */
public class Clothing implements Visitable {

	private String brand;
	private String clothingType;
	private double price;

	/**
	 * consturctor
	 * 
	 * @param clothingType
	 *            type of clothes
	 * @param brand
	 *            brand name
	 * @param price
	 *            price
	 */
	public Clothing(String clothingType, String brand, double price) {
		this.clothingType = clothingType;
		this.brand = brand;
		this.price = price;

	}

	/**
	 * getter method
	 * 
	 * @return return clothType
	 */
	public String getClothingType() {
		return clothingType;
	}

	/**
	 * getter method
	 * 
	 * @return return brand name
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * getter method
	 * 
	 * @return return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * toString method print out the information of the clothing item.
	 */
	public String toString() {
		return "Clothes type: " + clothingType + ". Brand: " + brand + ". Clothing price: " + price;
	}

	@Override
	/**
	 * visitor pattern. call visitor.
	 */
	public double accept(Visitor visitor) {
		return visitor.visit(this);

	}

}
