import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Concrete class Snack that implements visitable
 * 
 * @author yuhu
 *
 */
public class Snack implements Visitable {

	private double price;
	private String snackType;
	private String brand;
	private Date expireDate;

	/**
	 * constructor
	 * 
	 * @param snackType
	 *            type of snack
	 * @param brand
	 *            brand
	 * @param price
	 *            price
	 * @param expireDate
	 *            expiration date.
	 */
	public Snack(String snackType, String brand, double price, String expireDate) {
		this.snackType = snackType;
		this.brand = brand;
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
	 * @return return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * getter method
	 * 
	 * @return return snack type
	 */
	public String getSnackType() {
		return snackType;
	}

	/**
	 * getter method
	 * 
	 * @return return brand.
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * getter method
	 * 
	 * @return return expire date.
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * toString method print out all the information of the snack.
	 */
	public String toString() {
		return "snack type: " + snackType + "snack brand: " + brand + " snack price: " + price + " snack date: "
				+ expireDate;
	}

	@Override
	/**
	 * visitor pattern. call visitor.
	 */
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
