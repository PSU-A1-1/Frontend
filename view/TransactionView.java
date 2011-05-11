package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.ImageIcon;

import control.DBConnection;


import model.CardHolder;
import model.Transaction;


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
	 * Create the frame.
	 */
	public TransactionView(CardHolder cardHolder) {
		setBackground(Color.LIGHT_GRAY);
		transaction = new Transaction(cardHolder);
		transaction.addObserver(this);
		createFrame();
		setVisible(true);
	}
	
	public void update(Observable tr, Object obj) {
		int beers = transaction.getBoughtBeers();
		if(beers > 0) {
			boughtBeers.setForeground(Color.red);
			boughtBeers.setText("-" + beers);
		}
		else {
			boughtBeers.setForeground(Color.WHITE);
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
			boughtDrinks.setForeground(Color.WHITE);
			boughtDrinks.setText("" + drinks);
		}
		
		if(drinks + beers == 0) resetButton.setText("Cancel");
		else resetButton.setText("Reset");
	}
	
	
	private void submit() {
		ConfirmDialog confirm = new ConfirmDialog(transaction);
		
		if(confirm.getOkCancel() == 1) {
			confirm.dispose();
			DBConnection DB = new DBConnection(); 
			DB.sendTransaction(transaction);
			setVisible(false);
			dispose();
		} else {
			confirm.dispose();
			transaction.reset();
		}
	}
	
	private void resetCancel() {
		int bought = transaction.getBoughtBeers() + transaction.getBoughtDrinks();
		if(bought != 0) transaction.reset();
		else {
			setVisible(false);
			dispose();
		}
	}
	
	private void styleButton(JButton button) {
	    button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		Border line = new EmptyBorder(0,0,0,0);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		button.setFocusPainted(false);
	}
	
	private int getX(int percentage) {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth()); 
		return (xSize / 100) * percentage;
	}
	
	private int getY(int percentage) {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int ySize = ((int) tk.getScreenSize().getHeight()); 
		return (ySize / 100) * percentage;
	}
	
	private void createFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true); 
		setContentPane(contentPane);
		
		JPanel bottomPane = new JPanel();
		bottomPane.setBackground(Color.LIGHT_GRAY);
		bottomPane.setForeground(Color.LIGHT_GRAY);
		bottomPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(bottomPane, BorderLayout.SOUTH);
		bottomPane.setLayout(new BorderLayout(0, 0));
		
		JButton submitButton = new JButton("Submit");
		styleButton(submitButton);
		submitButton.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
		submitButton.setPreferredSize(new Dimension(getX(30), getY(15)));
		submitButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) { submit(); }
			});
		bottomPane.add(submitButton, BorderLayout.EAST);
		
		resetButton = new JButton("Cancel");
		styleButton(resetButton);
		resetButton.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
		resetButton.setPreferredSize(new Dimension(getX(30), getY(15)));
		resetButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { resetCancel(); }
    	});
		bottomPane.add(resetButton, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setForeground(Color.LIGHT_GRAY);
		bottomPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Navn:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		panel_2.add(lblNewLabel);
		
		JLabel lblFrivillignavn = new JLabel(transaction.getName());
		lblFrivillignavn.setVerticalAlignment(SwingConstants.TOP);
		lblFrivillignavn.setFont(new Font("Arial", Font.PLAIN, 30));
		lblFrivillignavn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrivillignavn.setForeground(Color.BLACK);
		panel_2.add(lblFrivillignavn);
		
		JPanel topPane = new JPanel();
		topPane.setBackground(Color.LIGHT_GRAY);
		topPane.setForeground(Color.LIGHT_GRAY);
		topPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(topPane, BorderLayout.NORTH);
		topPane.setLayout(new GridLayout(0, 4, 30, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.LIGHT_GRAY);
		topPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		newBeers = new JLabel("" + transaction.getNewBeers());
		panel.add(newBeers);
		newBeers.setHorizontalAlignment(SwingConstants.RIGHT);
		newBeers.setVerticalAlignment(SwingConstants.TOP);
		newBeers.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
		newBeers.setForeground(Color.WHITE);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setForeground(Color.LIGHT_GRAY);
		topPane.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		boughtBeers = new JLabel(" " + transaction.getBoughtBeers());
		panel_7.add(boughtBeers);
		boughtBeers.setVerticalAlignment(SwingConstants.TOP);
		boughtBeers.setHorizontalAlignment(SwingConstants.LEFT);
		boughtBeers.setFont(new Font("Stengade font R2", Font.PLAIN, 80));
		boughtBeers.setForeground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setForeground(Color.LIGHT_GRAY);
		topPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		boughtDrinks = new JLabel(" " + transaction.getBoughtDrinks());
		panel_1.add(boughtDrinks);
		boughtDrinks.setVerticalAlignment(SwingConstants.TOP);
		boughtDrinks.setHorizontalAlignment(SwingConstants.RIGHT);
		boughtDrinks.setFont(new Font("Stengade font R2", Font.PLAIN, 80));
		boughtDrinks.setForeground(Color.WHITE);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setForeground(Color.LIGHT_GRAY);
		topPane.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		newDrinks = new JLabel("" + transaction.getNewDrinks());
		panel_8.add(newDrinks);
		newDrinks.setHorizontalAlignment(SwingConstants.LEFT);
		newDrinks.setVerticalAlignment(SwingConstants.TOP);
		newDrinks.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
		newDrinks.setForeground(Color.WHITE);
		
		JPanel middlePane = new JPanel();
		middlePane.setBackground(Color.LIGHT_GRAY);
		middlePane.setForeground(Color.LIGHT_GRAY);
		middlePane.setBorder(new EmptyBorder(30, 30, 30, 30));
		contentPane.add(middlePane, BorderLayout.CENTER);
		middlePane.setLayout(new GridLayout(0, 2, 30, 0));
		
		JButton beerButton = new JButton("");
		beerButton.setIcon(new ImageIcon("C:\\Users\\Niels\\Frontend\\beer.jpg"));
		beerButton.setContentAreaFilled(false);
		styleButton(beerButton);
		beerButton.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
		beerButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) { transaction.addBeer(); }
        	});
		middlePane.add(beerButton);
		
		JButton drinkButton = new JButton("");
		drinkButton.setIcon(new ImageIcon("C:\\Users\\Niels\\Frontend\\drink.jpg"));
		drinkButton.setContentAreaFilled(false);
		styleButton(drinkButton);
		drinkButton.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
		drinkButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) { transaction.addDrink(); }
    		});
		middlePane.add(drinkButton);
		
	}
		
}

