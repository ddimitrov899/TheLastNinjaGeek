package com.teamsluis.thelastninjageek;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options {
public static int playersNum = 1;
	  public static void OptionsPanel () {
		  // Labels
		  JLabel opLabel = new JLabel("Options");
		  JLabel players = new JLabel("Set number of players");
		  JLabel jockers = new JLabel("Allow jockers");
		  JLabel showPl = new JLabel("1");
		  
		  opLabel.setFont(new java.awt.Font("Times New Roman", 2, 48));
		  players.setFont(new java.awt.Font("Times New Roman", 1, 16));
		  jockers.setFont(new java.awt.Font("Times New Roman", 1, 16));
		  
		  Main.gameWindow.add(opLabel);
		  Main.gameWindow.add(players);
		  Main.gameWindow.add(jockers);
		  Main.gameWindow.add(showPl);
		  
		  opLabel.setBounds(300,40, 200,70);
     	  players.setBounds(200,150, 240,80);
		  jockers.setBounds(240,220,140,80);
		  showPl.setBounds(550,115,100,70);
		  
		  // Sliders and Buttons
		  JSlider plNumbSl = new JSlider();
		  plNumbSl.setValue(1);
		  plNumbSl.setMinimum(1);
		  plNumbSl.setMaximum(4);
		  plNumbSl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				showPl.setText(Integer.toString(plNumbSl.getValue()));
				playersNum  = plNumbSl.getValue();
			}
		});

		  JToggleButton allowJkr = new JToggleButton("Yes");
		  Main.gameWindow.add(plNumbSl);
		  Main.gameWindow.add(allowJkr);
		  plNumbSl.setBounds(490,170,130,30);
		  allowJkr.setBounds(500,240,100,50);
		  
	  }
	}
