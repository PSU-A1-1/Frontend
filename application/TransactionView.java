package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import java.util.Observer;

import javax.swing.border.SoftBevelBorder;

public class TransactionView implements Observer {

	private JFrame frame;
	private Transaction transaction;
	

	/**
	 * Launch the application.
	 */
	
		
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardHolder cardholder = new CardHolder(29, "Anna", "Didriksen", 10, 10, 1);
					TransactionView window = new TransactionView(cardholder);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	TransactionView(CardHolder cardHolder) {	
		transaction = new Transaction(cardHolder);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPane = new JPanel();
		bottomPane.setBorder(null);
		frame.getContentPane().add(bottomPane, BorderLayout.SOUTH);
		bottomPane.setLayout(new BorderLayout(0, 0));
		
		JButton Submit = new JButton("Submit");
		bottomPane.add(Submit, BorderLayout.EAST);
		
		JButton Reset = new JButton("Reset");
		bottomPane.add(Reset, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		bottomPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Frivillig:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);
		
		JLabel lblFrivillignavn = new JLabel(transaction.getName());
		lblFrivillignavn.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblFrivillignavn);
		
		JPanel topPane = new JPanel();
		topPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(topPane, BorderLayout.NORTH);
		topPane.setLayout(new GridLayout(0, 4, 30, 0));
		
		JPanel panel = new JPanel();
		topPane.add(panel);
		
		JLabel boughtBeers = new JLabel("" + transaction.getBoughtBeers());
		panel.add(boughtBeers);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setHgap(30);
		topPane.add(panel_7);
		
		JLabel newBeers = new JLabel("" + transaction.getNewBeers());
		panel_7.add(newBeers);
		
		JPanel panel_1 = new JPanel();
		topPane.add(panel_1);
		
		JLabel newDrinks = new JLabel("" + transaction.getNewDrinks());
		panel_1.add(newDrinks);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_8.getLayout();
		flowLayout_1.setHgap(30);
		topPane.add(panel_8);
		
		JLabel boughtDrinks = new JLabel("" + transaction.getBoughtDrinks());
		panel_8.add(boughtDrinks);
		
		JPanel middlePane = new JPanel();
		middlePane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(middlePane, BorderLayout.CENTER);
		middlePane.setLayout(new GridLayout(0, 4, 30, 0));
		
		JPanel fillerWest = new JPanel();
		middlePane.add(fillerWest);
		
		JButton beerButton = new JButton("\u00D8l");
		middlePane.add(beerButton);
		
		JButton drinkButton = new JButton("Drink");
		middlePane.add(drinkButton);
		
		JPanel fillerEast = new JPanel();
		middlePane.add(fillerEast);
	}

}
