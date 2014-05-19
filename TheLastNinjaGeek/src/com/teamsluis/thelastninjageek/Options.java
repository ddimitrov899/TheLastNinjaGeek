package com.teamsluis.thelastninjageek;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options {

public static ImageIcon bg;
	public static void optionsPanel() {
		
        JLabel jl = new JLabel();
		bg = new ImageIcon("resources/images/backgrpOP.png");
	    jl.setIcon(bg);
	    jl.setIconTextGap(-800);
	    jl.setOpaque(true);
	    jl.setSize(800,600);
	   
	   

		// Labels init
		JLabel players = new JLabel("Set number of players");
		JLabel jockers = new JLabel("Allow jockers");
		JLabel showPl = new JLabel("1");


		players.setFont(new java.awt.Font("Times New Roman", 1, 16));
		jockers.setFont(new java.awt.Font("Times New Roman", 1, 16));

		Main.gameWindow.add(players);
		Main.gameWindow.add(jockers);
		Main.gameWindow.add(showPl);

		players.setBounds(200, 150, 240, 80);
		jockers.setBounds(240, 220, 140, 80);
		showPl.setBounds(550, 115, 100, 70);

		// Sliders and Buttons init
		JButton cancel = new JButton("Cancel");
		JButton apply = new JButton("Apply");
		JToggleButton allowJkr = new JToggleButton("Yes");

		JSlider plNumbSl = new JSlider();
		allowJkr.setSelected(Data.jokersAllowed);
		cancel.setFont(new java.awt.Font("Times New Roman", 1, 16));
		apply.setFont(new java.awt.Font("Times New Roman", 1, 16));

		Main.gameWindow.add(cancel);
		Main.gameWindow.add(apply);
		Main.gameWindow.add(plNumbSl);
		Main.gameWindow.add(allowJkr);
		plNumbSl.setBounds(490, 170, 130, 30);
		allowJkr.setBounds(500, 240, 100, 50);
		apply.setBounds(200, 480, 180, 50);
		cancel.setBounds(450, 480, 180, 50);
		Container[] all = { jl, players, jockers, showPl, cancel, apply,
				allowJkr, plNumbSl };
		for (int i = 0; i < all.length; i++) {
			all[i].setForeground(Color.WHITE);
			all[i].setBackground(Color.BLACK);
		}

		// Slider Actions
		plNumbSl.setValue(Data.ninjas);
		plNumbSl.setMinimum(1);
		plNumbSl.setMaximum(4);
		 int oldVal = Data.ninjas;
		showPl.setText(Integer.toString(Data.ninjas));
		
		plNumbSl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				Data.ninjas = plNumbSl.getValue();
				showPl.setText(Integer.toString(plNumbSl.getValue()));
				
			}
		});
		// Button Actions
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < all.length; i++) {
					all[i].setVisible(false);
				}
				Data.ninjas =  oldVal;
				plNumbSl.setValue(Data.ninjas);
				Main.gameWindow.setContentPane(Main.newPane);
				Main.displayMenu();
				System.out.println("Going to Main");
			}
		});
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < all.length; i++) {
					all[i].setVisible(false);
				}
				Data.setNinja();
				Main.gameWindow.setContentPane(Main.newPane);
				Main.displayMenu();
				if(allowJkr.isSelected() == true) {
					Data.jokersAllowed = true;
					System.out.printf("Players set to %d!\nJokers are allowed!\nGoing to Main..",Data.ninjas);
				}
				else {
					Data.jokersAllowed = false;
					System.out.printf("Players set to %d!\nJokers are NOT allowed!\nGoing to Main..",Data.ninjas);
				}			
			}
		});
		 Main.gameWindow.add(jl);
	}
}
