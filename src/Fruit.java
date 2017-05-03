import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Concrete class fruit that implements visitable
 * 
 * @author yuhu
 *
 */
public class Fruit implements Visitable {

	private String fruitType;
	private String provider;
	private double weight;
	private double price;
	private Date expireDate;

	/**
	 * constructor
	 * 
	 * @param fruitType
	 *            type of fruit
	 * @param provider
	 *            fruit provider
	 * @param weight
	 *            weight
	 * @param price
	 *            price
	 * @param expireDate
	 *            expiration date
	 */
	public Fruit(String fruitType, String provider, double weight, double price, String expireDate) {
		this.fruitType = fruitType;
		this.provider = provider;
		this.weight = weight;
		this.price = price;
		try {
			this.expireDate = new SimpleDateFormat("dd/MM/yyyy").parse(expireDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * getter method
	 * 
	 * @return fruitType
	 */
	public String getFruitType() {
		return fruitType;
	}

	/**
	 * getter method
	 * 
	 * @return provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * getter method
	 * 
	 * @return return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * getter method
	 * 
	 * @return return price
	 */
	public double getPrice() {
		return price;
	}

	@Override
	/**
	 * visitor pattern
	 */
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

	/**
	 * getter method
	 * 
	 * @return return expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * to string print out the information of the fruit.
	 */
	public String toString() {
		return "Fruit type: " + fruitType + " Fruit provider: " + provider + " Fruit weight: " + weight
				+ " fruit price: " + price;
	}
}
