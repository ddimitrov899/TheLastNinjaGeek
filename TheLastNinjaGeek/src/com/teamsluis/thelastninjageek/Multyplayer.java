package com.teamsluis.thelastninjageek;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Multyplayer {

	public static void displayMulty() {

		SpringLayout layout = new SpringLayout();
		Container contentPane =  Main.gameWindow.getContentPane();
		contentPane.setLayout(layout);
		contentPane.setBackground(Color.black);
		JTextField[] inName = new JTextField[Data.ninjas];
		JLabel[] playersT = new JLabel[Data.ninjas];
		JButton applyBut = new JButton("Apply");

		for (int i = 0; i < inName.length; i++) {
			inName[i] = new JTextField();
			playersT[i] = new JLabel("Player " + i + " name :");
			playersT[i].setForeground(Color.white);
			contentPane.add(inName[i]);
			contentPane.add(playersT[i]);
			inName[i].setPreferredSize(new Dimension(120, 20));

			layout.putConstraint(SpringLayout.WEST, playersT[i], 200,
					SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.NORTH, playersT[i],
					200 + (i * 50), SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, inName[i], 150,
					SpringLayout.EAST, playersT[i]);
			layout.putConstraint(SpringLayout.NORTH, inName[i], 200 + (i * 50),
					SpringLayout.NORTH, contentPane);

			if (i == inName.length - 1) {
				contentPane.add(applyBut);
				layout.putConstraint(SpringLayout.WEST, applyBut, 50,
						SpringLayout.EAST, playersT[i]);
				layout.putConstraint(SpringLayout.NORTH, applyBut,
						300 + (i * 50), SpringLayout.NORTH, contentPane);
			}
		}

		applyBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < playersT.length; i++) {
					Data.namePlayer[i] = inName[i].getText();
					inName[i].setVisible(false);
					playersT[i].setVisible(false);							
				}
				applyBut.setVisible(false);		
			Main.gameWindow.setContentPane(Main.oldPane);
			Main.gameWindow.setLayout(null);
				CategoriesMenu.displayCategories();
			}
		});

		Main.gameWindow.pack();
		Main.gameWindow.setVisible(true);
	}
}
