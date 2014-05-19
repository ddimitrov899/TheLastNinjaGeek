package com.teamsluis.thelastninjageek;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game {
	public static int questions = 0;
	public static void gameComencing() {

		
		JTextArea questionsWindow = new JTextArea();
		questionsWindow.setBounds(70, 100, 655, 260);
		questionsWindow.setFont(new Font("Serif", Font.ITALIC, 26));
		questionsWindow.setLineWrap(true);
		questionsWindow.setWrapStyleWord(true);
		questionsWindow.setText("Insert  XML questions  here");
		questionsWindow.setEditable(false);
		Main.gameWindow.add(questionsWindow);
        
		// Labels
		JLabel playerName = new JLabel("<html><font color=red>"
				+ Data.namePlayer[0] + "</font></html>");
		JLabel playerScore = new JLabel(" : "
				+ Integer.toString(Data.scorePlayer[0]));
		playerName.setBounds(670, 3, 100, 60);
		playerScore.setBounds(700, 3, 100, 60);
		Main.gameWindow.add(playerName);
		Main.gameWindow.add(playerScore);

		// Create and set BUTTONS
		JButton[] buttons = new JButton[4];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setText("Insert XML answers here");
			Main.gameWindow.add(buttons[i]); // here

			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (questions < 5){
						playerScore.setText(" : "+ Integer.toString(Data.scorePlayer[0] += 1));			
					      questions++;
					}
					else {
						questions = 0;
						if (Data.ninjas > 1) {
							// SHOW MENY QUESTIONS THEMES
						}
						
					}
        
					}				
					
			});
		}
		buttons[0].setBounds(70, 410, 300, 60); // X, Y, Width,Height format;
		buttons[1].setBounds(420, 410, 300, 60);
		buttons[2].setBounds(70, 490, 300, 60);
		buttons[3].setBounds(420, 490, 300, 60);
		questionsWindow.setVisible(true);
		Jokers.showJokers();
		// Show JokerWindow keyBinding

	}
}
