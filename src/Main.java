import javax.swing.JFrame;

/**
 * main has a jframe.
 * 
 * @author yuhu
 *
 */
public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Visitor Pattern demo");
		frame.add(new Cart());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}

}
