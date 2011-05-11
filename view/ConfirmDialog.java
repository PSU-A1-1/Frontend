package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import model.Transaction;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int okCancel;
	
	/**
	 * Create the dialog.
	 */
	public ConfirmDialog(Transaction tr) {
		getContentPane().setForeground(Color.WHITE);
		setForeground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		
		setBounds(getX(30), getY(30), getX(40), getY(40));
		setUndecorated(true); 
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel mainPanel = new JPanel();
			mainPanel.setForeground(Color.WHITE);
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
			getContentPane().add(mainPanel);
			mainPanel.setLayout(new BorderLayout(0, 0));
			mainPanel.add(contentPanel);
			contentPanel.setForeground(Color.WHITE);
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
			{
				JLabel name = new JLabel("" + tr.getName() + " køber");
				name.setFont(new Font("Arial", Font.PLAIN, 30));
				contentPanel.add(name);
			}
			{
				JLabel beers = new JLabel( "Øl: " + tr.getBoughtBeers());
				beers.setFont(new Font("Arial", Font.PLAIN, 30));
				contentPanel.add(beers);
			}
			{
				JLabel drinks = new JLabel("Drinks: " + tr.getBoughtDrinks());
				drinks.setFont(new Font("Arial", Font.PLAIN, 30));
				contentPanel.add(drinks);
			}
			{
				JPanel buttonPane = new JPanel();
				mainPanel.add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setForeground(Color.WHITE);
				buttonPane.setBackground(Color.WHITE);
				buttonPane.setLayout(new GridLayout(0, 2, 30, 0));
				{
					JButton okButton = new JButton("OK");
					styleButton(okButton);
					okButton.setPreferredSize(new Dimension(47, 100));
					okButton.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) { 
	    				okCancel = 1;
	    				setVisible(false);
	    				}
					});
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("CANCEL");
					styleButton(cancelButton);
					cancelButton.setPreferredSize(new Dimension(47, 100));
					cancelButton.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) { 
	    				okCancel = 0;
	    				setVisible(false);
	    				}
					});
					buttonPane.add(cancelButton);
				}
			}
		}
		setVisible(true);
	}
	
	public int getOkCancel(){
		return okCancel;
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
	
	private void styleButton(JButton button) {
		button.setFont(new Font("Stengade font R2", Font.PLAIN, 60));
	    button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		Border line = new LineBorder(Color.GRAY);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		button.setFocusPainted(false);
	}

}
