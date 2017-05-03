public class ShippingVisitor implements Visitor {

	@Override
	/**
	 * book above 50 percent bucks are free for shipping. otherwise, it is 1
	 * dollar per pound.
	 */
	public double visit(Book b) {
		if (b.getPrice() > 50) {
			System.out.print("Book over 50 is free for shipping. ");
			return 0;
		} else {
			System.out.print("shipping fee for book is 1 dollar per pound ");
			return b.getWeight();
		}

	}

	@Override
	/**
	 * Fruit over 30 dollar is free for shipping otherwise, it is 2 quarters per
	 * pound.
	 */
	public double visit(Fruit f) {

		if (f.getWeight() * f.getPrice() >= 30) {
			System.out.print("Fruit over 30 is free for shipping. ");
			return 0;
		} else {
			System.out.println("shipping fee for fruit is 0.5 dollar per pound ");
			return f.getWeight() * 0.5;
		}
	}

	@Override
	/**
	 * Snack are free for shipping.
	 */
	public double visit(Snack s) {
		System.out.print("snack is free for shipping. ");
		return 0;
	}

	@Override
	/**
	 * Clothings are free for shipping if it is above 10 dollar otherwise, it is
	 * 5 dollar per item. Trick the consumer to buy more.
	 */
	public double visit(Clothing c) {

		if (c.getPrice() >= 18) {
			System.out.print("clothes more than 10 dollar is free for shipping.  ");
			return 0;
		}
		System.out.println("shipping fee for clothes is 5 dollar per clothing item");
		return 5;
	}

}
