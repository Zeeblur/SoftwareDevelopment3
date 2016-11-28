package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import observer.*;
import skywars.GameState;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PopUp extends JDialog implements Observer {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public PopUp() {
		// initialise window design
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton okButton = new JButton("FIRE!");
			okButton.setBounds(111, 128, 222, 90);
			contentPanel.add(okButton);
			okButton.setFont(new Font("Rockwell", Font.PLAIN, 36));
			okButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
				
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JLabel lblNewLabel = new JLabel("<html>Enemy Space Ships in<br>YOUR SKY!</html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 36));
		lblNewLabel.setBounds(12, 42, 408, 72);
		contentPanel.add(lblNewLabel);
	}

	// observer to game manager
	@Override
	public void update(GameState state)
	{
		// if the state has a collision within it. POP UP!
		if (state.getCollision())
			this.setVisible(true);		
	}
}
