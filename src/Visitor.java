/**
 * visitor interface.
 * 
 * @author yuhu
 *
 */
public interface Visitor {
	public double visit(Book b);

	public double visit(Fruit f);

	public double visit(Snack s);

	public double visit(Clothing c);
}
