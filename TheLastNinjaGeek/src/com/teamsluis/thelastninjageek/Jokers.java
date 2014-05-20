package com.teamsluis.thelastninjageek;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Jokers {
	public static void showJokers() {

		if (Data.jokersAllowed == true) {
			JButton[] jokersWindow = new JButton[3];
			for (int i = 0; i < jokersWindow.length; i++) {
				jokersWindow[i] = new JButton();
				jokersWindow[i].setEnabled(false);
				jokersWindow[i].setVisible(true);
				jokersWindow[i].setForeground(Color.WHITE);
				jokersWindow[i].setBackground(Color.BLACK);
				jokersWindow[i].setBounds(730, 60 + ((i + 1) * 70), 70, 60);			
				Main.gameWindow.add(jokersWindow[i]);
			}
			ImageIcon jokerHackico =  new ImageIcon("resources/images/icones/50f50.jpg");
			ImageIcon jokerShuriken =  new ImageIcon("resources/images/icones/shuriken.jpg");
			ImageIcon jokerSwitch =  new ImageIcon("resources/images/icones/switch.jpg");

			jokersWindow[0].setIcon(jokerHackico);
			jokersWindow[1].setIcon(jokerShuriken);
			jokersWindow[2].setIcon(jokerSwitch);
			
			
			jokersWindow[0].setToolTipText("<html><font color=red><p align = center>"
					+ "<b><u> [Ninja Hack]</b></u></p></font><br>"
					+ "You hack through the game,<br> "
					+ "removing "+ "2 of the possible<br>"
					+ " answers..so mean..</html>");
			jokersWindow[1].setToolTipText("<html><font color=red><p align = center>"
					+ "<b><u> [Shuriken Bits]</b></u></p></font><br>"
					+ "You throw a shuriken hitting,<br> "
					+ "few of the bits changing your<br>"
					+ "scores value, you better aim<br>"
					+ "carefully.</html>");
			jokersWindow[2].setToolTipText("<html><font color=red><p align = center>"
					+ "<b><u> [Nerds Rage]</b></u></p></font><br>"
					+ "Please change this hard question<br> "
					+ "for me, or<font color=red> I KEEL YOU!</font></html>");
			
			
			
			
			// Round 1 jokers = 50/50,DoublePoints,Change Question
			if (Data.currentRound == 1) {
				if (Data.playerHasJoker[Data.currentPlayer] == 3) {
					for (int i = 0; i < 3; i++) {
						Data.currentPlayerJokers[Data.currentPlayer][i] = 1;
					}
				}
				for (int i = 0; i < 3; i++) {
					if (Data.currentPlayerJokers[Data.currentPlayer][i] == 1) {
						jokersWindow[i].setEnabled(true);

					}
				}
				// Shuriken  bits ! Player points multiplied x N < 5;;
				jokersWindow[1].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int bitsReward = 1 + (int) Math.floor(Math.random() * 5); // from 1 to 5 points
						System.out.println("Random points are : "+bitsReward);					
						jokersWindow[1].setEnabled(false);
						Data.currentPlayerJokers[Data.currentPlayer][1]--;
						Data.playerHasJoker[Data.currentPlayer]--;
						System.out
								.printf("%nPlayer %d current Sudden Strike jokers are %d.%nPlayer %d has %d jokers left.",
										Data.currentPlayer,
										Data.currentPlayerJokers[Data.currentPlayer][1],
										Data.currentPlayer,
										Data.playerHasJoker[Data.currentPlayer]);
					}
				});
				// 50/50 (Ninja HACK) removes 2 of the answers;
				
				jokersWindow[0].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int counter = 0;
						for (int i = 0; i <  Game.buttons.length; i++) {							
		                 if (Game.buttons[i].getText().equals(Game.CorA)) {
		                
							continue;	
							
						}
		                 else if (counter < 2){
		                	 counter++;
		                	 Game.buttons[i].setVisible(false);
		                 }
					}
						
						System.out.println("50/50 used");
						System.out.println(Game.CorA);
						
						jokersWindow[0].setEnabled(false);
						Data.currentPlayerJokers[Data.currentPlayer][0]--;
						Data.playerHasJoker[Data.currentPlayer]--;
						System.out
								.printf("%nPlayer %d current 50/50 jokers are %d.%nPlayer %d has %d jokers left.",
										Data.currentPlayer,
										Data.currentPlayerJokers[Data.currentPlayer][0],
										Data.currentPlayer,
										Data.playerHasJoker[Data.currentPlayer]);
					}
				});
				
	           // Nerds Rage  change the question.				
				jokersWindow[2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {			
						Game.nextQuestion();		
						Game.questions--;
						jokersWindow[2].setEnabled(false);
						Data.currentPlayerJokers[Data.currentPlayer][2]--;
						Data.playerHasJoker[Data.currentPlayer]--;
						System.out
								.printf("%nPlayer %d current NERD RAGE  jokers are %d.%nPlayer %d has %d jokers left.",
										Data.currentPlayer,
										Data.currentPlayerJokers[Data.currentPlayer][2],
										Data.currentPlayer,
										Data.playerHasJoker[Data.currentPlayer]);
					}
				});
				
			}
		}
	}

	public static void initJokers() {
	Data.playerHasJoker[Data.currentPlayer] = 3; // fix me
	}
}