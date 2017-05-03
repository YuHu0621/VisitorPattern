import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Cart is the client class that perform calculate discount and shipping cost
 * operation to all the items in the shopping class. The app uses visitor
 * pattern.
 * 
 * @author yuhu
 *
 */
@SuppressWarnings("serial")
public class Cart extends JPanel {

	// shopping items
	private Set<Visitable> items = new HashSet<Visitable>();

	private JComboBox itemMenu = new JComboBox();
	private String[] addItemStrings = { "Add item to cart", "Add Book to cart", "add clothing to cart",
			"add fruit to cart", "add snack to cart" };
	private JButton addItemBtn;

	// fruit panel and input textfield
	private JPanel textPanel;
	private JLabel typeLabel = new JLabel("Enter Fruit Type");
	private JTextField fruitType = new JTextField();
	private JLabel priceLabel = new JLabel("Enter Fruit Price");
	private JTextField fruitPrice = new JTextField();
	private JLabel weightLabel = new JLabel("Enter Fruit Weight");
	private JTextField fruitWeight = new JTextField();
	private JLabel providerLabel = new JLabel("Enter fruit provider");
	private JTextField fruitProvider = new JTextField();
	private JLabel dateLabel = new JLabel("Enter fruit expire date dd/mm/yyyy");
	private JTextField fruitDate = new JTextField();

	// book panel and textfield
	private JLabel titleLabel = new JLabel("Enter book Title");
	private JTextField bookTitle = new JTextField();
	private JLabel priceLbl = new JLabel("Enter book Price");
	private JTextField bookPrice = new JTextField();
	private JLabel bWeightLabel = new JLabel("Enter book Weight");
	private JTextField bookWeight = new JTextField();
	private JLabel isbnLabel = new JLabel("Enter ISBN");
	private JTextField bookIsbn = new JTextField();

	// Snack panel and textfield
	private JLabel sTypeLabel = new JLabel("Enter snack");
	private JTextField snackType = new JTextField();
	private JLabel sPriceLabel = new JLabel("Enter price");
	private JTextField snackPrice = new JTextField();
	private JLabel sBrandLabel = new JLabel("Enter snack brand");
	private JTextField snackBrand = new JTextField();
	private JLabel sDate = new JLabel("Enter snack expire date dd/mm/yyyy");
	private JTextField snackDate = new JTextField();

	// clothing panel
	private JLabel cTypeLabel = new JLabel("Enter clothes");
	private JTextField clothingType = new JTextField();
	private JLabel cPriceLabel = new JLabel("Enter clothes price");
	private JTextField clothingPrice = new JTextField();
	private JLabel cBrandLabel = new JLabel("Enter brand");
	private JTextField clothingBrand = new JTextField();

	// central panel
	private JPanel centerPanel;

	/**
	 * constructor
	 */
	public Cart() {
		setLayout(new BorderLayout());
		// NORTH PANEL
		// enter new shopping items into the carts

		// selection menu
		createItemMenu(addItemStrings);
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.add(itemMenu, BorderLayout.WEST);
		add(textPanel, BorderLayout.NORTH);
		// add button
		addItemBtn = createAddBtn();

		// SOUTH PANEL
		JButton shippingBtn = createShippingBtn();
		JButton priceBtn = createPriceBtn();
		JButton resetBtn = createResetBtn();
		JPanel southPanel = new JPanel();
		southPanel.add(shippingBtn);
		southPanel.add(priceBtn);
		southPanel.add(resetBtn);
		add(southPanel, BorderLayout.SOUTH);

		// central panel
		// display all the shopping items and the costs
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(10, 1));
		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * create a reset button
	 * 
	 * @return return a button
	 */
	private JButton createResetBtn() {
		JButton btn = new JButton("New Cart");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove(textPanel);
				remove(centerPanel);
				textPanel = new JPanel(new BorderLayout());
				textPanel.add(itemMenu, BorderLayout.WEST);
				add(textPanel, BorderLayout.NORTH);
				items = new HashSet<Visitable>();
				centerPanel = new JPanel(new GridLayout(10, 1));
				add(centerPanel, BorderLayout.CENTER);
				validate();
				repaint();

			}

		});
		return btn;
	}

	/**
	 * create an add button that add item to the shopping cart when pressed,
	 * information are obtained from the text field and an object is created and
	 * stored in the cart.
	 * 
	 * @return return a button
	 */
	private JButton createAddBtn() {
		JButton btn = new JButton("add");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedItemIndex = itemMenu.getSelectedIndex();
				Visitable item = null;
				if (selectedItemIndex == 0) {
					return;
				} else {
					JLabel itemLabel = new JLabel();
					if (selectedItemIndex == 1) {
						item = new Book(bookIsbn.getText(), Double.parseDouble(bookWeight.getText()),
								Double.parseDouble(bookPrice.getText()), bookTitle.getText());
						itemLabel = new JLabel(item.toString());
					} else if (selectedItemIndex == 2) {
						item = new Clothing(clothingType.getText(), clothingBrand.getText(),
								Double.parseDouble(clothingPrice.getText()));
						itemLabel = new JLabel(item.toString());
					} else if (selectedItemIndex == 3) {
						item = new Fruit(fruitType.getText(), fruitProvider.getText(),
								Double.parseDouble(fruitWeight.getText()), Double.parseDouble(fruitPrice.getText()),
								fruitDate.getText());
						itemLabel = new JLabel(item.toString());
					} else if (selectedItemIndex == 4) {
						item = new Snack(snackType.getText(), snackBrand.getText(),
								Double.parseDouble(snackPrice.getText()), snackDate.getText());
						itemLabel = new JLabel(item.toString());
					}
					items.add(item);
					centerPanel.add(itemLabel);
					validate();
					repaint();
				}
			}

		});
		return btn;
	}

	/**
	 * create a price button that calculate the total cost, taking into
	 * consideration the discount.
	 * 
	 * @return return total cost.
	 */
	private JButton createPriceBtn() {
		JButton btn = new JButton("get price");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				double totalPrice = calculateTotalPrice();
				centerPanel.add(new JLabel("Total prices: " + totalPrice));
				validate();
				repaint();
			}

		});
		return btn;
	}

	/**
	 * helper method that calculate total cost.
	 * 
	 * @return return the total cost.
	 */
	private double calculateTotalPrice() {
		Iterator<Visitable> itr = items.iterator();
		double totalPrice = 0;
		while (itr.hasNext()) {
			totalPrice += itr.next().accept(new DiscountVisitor());
		}
		return totalPrice;
	}

	/**
	 * create shipping botton
	 * 
	 * @return return a button
	 */
	private JButton createShippingBtn() {
		JButton btn = new JButton("get Shipping cost");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double shippingCost = getShippingCost(items);
				centerPanel.add(new JLabel("Shipping prices: " + shippingCost));
				validate();
				repaint();
			}

		});
		return btn;
	}

	/**
	 * helper method to calculate shipping cost.
	 * 
	 * @param shoppingItems
	 *            items in the cart.
	 * @return return the shipping cost.
	 */
	private double getShippingCost(Set<Visitable> shoppingItems) {
		double totalPrice = calculateTotalPrice();
		double totalShippingCost = 0;

		if (totalPrice >= 35) {
			return totalShippingCost;
		}
		Iterator<Visitable> itr = shoppingItems.iterator();
		while (itr.hasNext()) {
			Visitable currItem = itr.next();
			if (currItem instanceof Book) {
				totalShippingCost += ((Book) currItem).accept(new ShippingVisitor());
			} else if (currItem instanceof Fruit) {
				totalShippingCost += ((Fruit) currItem).accept(new ShippingVisitor());
			} else if (currItem instanceof Snack) {
				totalShippingCost += ((Snack) currItem).accept(new ShippingVisitor());
			} else if (currItem instanceof Clothing) {
				totalShippingCost += ((Clothing) currItem).accept(new ShippingVisitor());
			}

		}
		return totalShippingCost;
	}

	/**
	 * create the itemMenu bar.
	 * 
	 * @param addItemStrings
	 *            string of add item.
	 */
	private void createItemMenu(String[] addItemStrings) {
		itemMenu = new JComboBox(addItemStrings);

		itemMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox comboMenu = (JComboBox) e.getSource();
				int selected = comboMenu.getSelectedIndex();
				if (selected == 1) {
					createBookTextField();
				} else if (selected == 2) {
					createClothingTextField();
				} else if (selected == 3) {
					createFruitTextField();
				} else if (selected == 4) {
					createSnackTextField();
				} else {
					resetTextFieldPanel();
					add(itemMenu, BorderLayout.CENTER);
				}
			}

		});
	}

	/**
	 * reset textfield. remove the previous entry.
	 */
	private void resetTextFieldPanel() {
		if (textPanel != null) {
			remove(textPanel);
			textPanel = null;
		}
		validate();
		repaint();
	}

	/**
	 * create fruit textfield.
	 */
	private void createFruitTextField() {
		resetTextFieldPanel();
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.add(itemMenu, BorderLayout.WEST);
		JPanel fruitPanel = new JPanel(new GridLayout(5, 2));
		fruitPanel.add(typeLabel);
		fruitPanel.add(fruitType);
		fruitPanel.add(priceLabel);
		fruitPanel.add(fruitPrice);
		fruitPanel.add(weightLabel);
		fruitPanel.add(fruitWeight);
		fruitPanel.add(dateLabel);
		fruitPanel.add(fruitDate);
		fruitPanel.add(providerLabel);
		fruitPanel.add(fruitProvider);
		textPanel.add(fruitPanel, BorderLayout.CENTER);
		textPanel.add(addItemBtn, BorderLayout.EAST);
		add(textPanel, BorderLayout.NORTH);
		validate();
		repaint();
	}

	/**
	 * create clothing textfield.
	 */
	private void createClothingTextField() {
		resetTextFieldPanel();
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.add(itemMenu, BorderLayout.WEST);
		JPanel clothPanel = new JPanel(new GridLayout(5, 2));
		clothPanel.add(cTypeLabel);
		clothPanel.add(clothingType);
		clothPanel.add(cPriceLabel);
		clothPanel.add(clothingPrice);
		clothPanel.add(cBrandLabel);
		clothPanel.add(clothingBrand);
		textPanel.add(addItemBtn, BorderLayout.EAST);
		textPanel.add(clothPanel, BorderLayout.CENTER);
		add(textPanel, BorderLayout.NORTH);
		validate();
		repaint();
	}

	/**
	 * create snack textfield
	 */
	private void createSnackTextField() {
		resetTextFieldPanel();
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.add(itemMenu, BorderLayout.WEST);
		JPanel snackPanel = new JPanel(new GridLayout(5, 2));
		snackPanel.add(sTypeLabel);
		snackPanel.add(snackType);
		snackPanel.add(sPriceLabel);
		snackPanel.add(snackPrice);
		snackPanel.add(sBrandLabel);
		snackPanel.add(snackBrand);
		snackPanel.add(sDate);
		snackPanel.add(snackDate);
		textPanel.add(addItemBtn, BorderLayout.EAST);
		textPanel.add(snackPanel, BorderLayout.CENTER);
		add(textPanel, BorderLayout.NORTH);
		validate();
		repaint();
	}

	/**
	 * create textfiled for book
	 */
	private void createBookTextField() {
		resetTextFieldPanel();
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.add(itemMenu, BorderLayout.WEST);
		JPanel bookPanel = new JPanel(new GridLayout(5, 2));
		bookPanel.add(titleLabel);
		bookPanel.add(bookTitle);
		bookPanel.add(isbnLabel);
		bookPanel.add(bookIsbn);
		bookPanel.add(priceLbl);
		bookPanel.add(bookPrice);
		bookPanel.add(bWeightLabel);
		bookPanel.add(bookWeight);
		textPanel.add(addItemBtn, BorderLayout.EAST);
		textPanel.add(bookPanel, BorderLayout.CENTER);
		add(textPanel, BorderLayout.NORTH);
		validate();
		repaint();
	}

}
