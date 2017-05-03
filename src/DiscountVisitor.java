import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Discount visitor have different discount algorithm for book, fruit, snack and
 * clothing
 * 
 * @author yuhu
 *
 */
public class DiscountVisitor implements Visitor {

	@Override
	/**
	 * Book has 50 percent of for special offer special offer are stroed in
	 * discountBooks set.
	 */
	public double visit(Book b) {
		Set<String> discountBooks = new HashSet<String>();
		discountBooks.add("123456");
		discountBooks.add("132456");
		discountBooks.add("654321");
		discountBooks.add("215231");
		discountBooks.add("012345");
		double discount = 0;
		if (discountBooks.contains(b.getISBN())) {
			System.out.print("This book has discount. ");
			discount = 0.5;
		} else {
			System.out.print("This book doesn't have discount. ");
		}
		double price = b.getPrice();
		double discountPrice = price * (1.0 - discount);
		System.out.println(b.getTitle() + " price after discount: " + discountPrice);
		return discountPrice;
	}

	@Override
	/**
	 * Fruit has 80 percent of for those that are going bad.
	 */
	public double visit(Fruit f) {
		Date expDate = f.getExpireDate();
		double price = f.getPrice();
		double weight = f.getWeight();
		Calendar now = GregorianCalendar.getInstance();

		// if the fruit is three day before it expires, 80 percent of.
		now.add(Calendar.DAY_OF_MONTH, 3);
		double discount;
		if (expDate.after(now.getTime())) {
			System.out.print("This fruit doesn't have discount. ");
			discount = 0;
		} else {
			System.out.print("This fruit is going bad! It has discount. ");
			discount = 0.8;
		}
		double discountPrice = price * weight * (1.0 - discount);
		System.out.println(f.getFruitType() + " price after discount: " + discountPrice);
		return discountPrice;

	}

	@Override
	/**
	 * Snack has 80 percent of discount for those that are expired.
	 */
	public double visit(Snack s) {
		Date expDate = s.getExpireDate();
		Calendar now = GregorianCalendar.getInstance();

		// if the snack is three month before it expires, 80 percent off.
		now.add(Calendar.MONTH, 3);
		double discount;
		if (expDate.after(now.getTime())) {
			System.out.print("This snack doesn't have discount. ");
			discount = 0;
		} else {
			System.out.print("This snack is going bad! It has discount. ");
			discount = 0.8;
		}
		double price = s.getPrice();
		double discountPrice = price * (1.0 - discount);
		System.out.println(s.getSnackType() + " Price after discount: " + discountPrice);
		return discountPrice;
	}

	@Override
	/**
	 * summer clothes are 50 percent of in winter time and vice versa.
	 */
	public double visit(Clothing c) {
		Set<String> summerType = new HashSet<String>();
		summerType.add("Dress");
		summerType.add("Skirt");
		summerType.add("Tank top");
		summerType.add("Shirt");
		summerType.add("Shorts");

		Set<String> winterType = new HashSet<String>();
		winterType.add("Coat");
		winterType.add("Down");
		winterType.add("Jacket");
		winterType.add("Gloves");

		double price = c.getPrice();
		Calendar now = GregorianCalendar.getInstance();
		double discount;
		if (now.get(Calendar.MONTH) == 5 || now.get(Calendar.MONTH) == 6 || now.get(Calendar.MONTH) == 7
				|| now.get(Calendar.MONTH) == 8) {
			System.out.print("summer discount: winter clothes are 50 percent off ");
			if (winterType.contains(c.getClothingType())) {

				discount = 0.5;
			} else {
				discount = 0.1;
			}
		} else {
			System.out.print("winter discount: summer clothes are 50 percent off ");
			if (summerType.contains(c.getClothingType())) {
				discount = 0.5;
			} else {
				discount = 0.1;
			}
		}
		double discountPrice = price * (1.0 - discount);
		System.out.println(c.getClothingType() + " Price after discount: " + discountPrice);
		return discountPrice;

	}

}
