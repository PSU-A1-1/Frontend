package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;


public class TransactionView extends JFrame implements Observer {

	/**
	 * serialVersionUID er noget som eclipse bare syntes var en vildt god idé.
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Transaction transaction;
	private JLabel newBeers;
	private JLabel boughtBeers;
	private JLabel boughtDrinks;
	private JLabel newDrinks;
	private JButton resetButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TransactionView frame = new TransactionView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TransactionView(CardHolder cardHolder) {
		transaction = new Transaction(cardHolder);
		transaction.addObserver(this);
		createFrame();
	}
	
	public void update(Observable tr, Object obj) {
		int beers = transaction.getBoughtBeers();
		if(beers > 0) {
			boughtBeers.setForeground(Color.red);
			boughtBeers.setText("-" + beers);
		}
		else {
			boughtBeers.setForeground(Color.black);
			boughtBeers.setText("" + beers);
		}
		
		newBeers.setText("" + transaction.getNewBeers());
		
		newDrinks.setText("" + transaction.getNewDrinks());
		
		int drinks = transaction.getBoughtDrinks();
		if(drinks > 0) {
			boughtDrinks.setForeground(Color.red);
			boughtDrinks.setText("-" + drinks);
		}
		else {
			boughtDrinks.setForeground(Color.black);
			boughtDrinks.setText("" + drinks);
		}
		
		if(drinks + beers == 0) resetButton.setText("Cancel");
		else resetButton.setText("Reset");
	}
	
	
	private void submit() {
		int okCancel = JOptionPane.showConfirmDialog(
			    this,
			    "" + transaction.getName() + " køber \n" + 
			    "Øl: " + transaction.getBoughtBeers() + 
			    "\n Drinks: " + transaction.getBoughtDrinks(),
			    "Bekræft Køb",
			    JOptionPane.YES_NO_OPTION);
		if(okCancel == 0) {
		DBConnection DB = new DBConnection(); 
		DB.sendTransaction(transaction);
		setVisible(false);
		dispose();
		}
		else transaction.reset();
	}
	
	private void resetCancel() {
		int bought = transaction.getBoughtBeers() + transaction.getBoughtDrinks();
		if(bought != 0) transaction.reset();
		else {
			setVisible(false);
			dispose();
		}
	}
	
	private void createFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel bottomPane = new JPanel();
		bottomPane.setBorder(null);
		contentPane.add(bottomPane, BorderLayout.SOUTH);
		bottomPane.setLayout(new BorderLayout(0, 0));
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) { submit(); }
			});
		bottomPane.add(submitButton, BorderLayout.EAST);
		
		resetButton = new JButton("Cancel");
		resetButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { resetCancel(); }
    	});
		bottomPane.add(resetButton, BorderLayout.WEST);
		
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
		contentPane.add(topPane, BorderLayout.NORTH);
		topPane.setLayout(new GridLayout(0, 4, 30, 0));
		
		JPanel panel = new JPanel();
		topPane.add(panel);
		
		boughtBeers = new JLabel("" + transaction.getBoughtBeers());
		panel.add(boughtBeers);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setHgap(30);
		topPane.add(panel_7);
		
		newBeers = new JLabel("" + transaction.getNewBeers());
		panel_7.add(newBeers);
		
		JPanel panel_1 = new JPanel();
		topPane.add(panel_1);
		
		newDrinks = new JLabel("" + transaction.getNewDrinks());
		panel_1.add(newDrinks);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_8.getLayout();
		flowLayout_1.setHgap(30);
		topPane.add(panel_8);
		
		boughtDrinks = new JLabel("" + transaction.getBoughtDrinks());
		panel_8.add(boughtDrinks);
		
		JPanel middlePane = new JPanel();
		middlePane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(middlePane, BorderLayout.CENTER);
		middlePane.setLayout(new GridLayout(0, 4, 30, 0));
		
		JPanel fillerWest = new JPanel();
		middlePane.add(fillerWest);
		
		JButton beerButton = new JButton("\u00D8l");
		beerButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) { transaction.addBeer(); }
        	});
		middlePane.add(beerButton);
		
		JButton drinkButton = new JButton("Drink");
		drinkButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) { transaction.addDrink(); }
    		});
		middlePane.add(drinkButton);
		
		JPanel fillerEast = new JPanel();
		middlePane.add(fillerEast);
	}
		
}

