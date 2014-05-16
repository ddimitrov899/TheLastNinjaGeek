package com.teamsluis.thelastninjageek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.color.*;
import java.awt.Toolkit;

public class Main {

	private static void createGameWindow() {
		
		// Create and display window frames.		
		JFrame gameWindow = new JFrame("The Last Ninja Geek™");
		gameWindow.getContentPane().setLayout(null);
		gameWindow.setPreferredSize(new Dimension(800, 600));
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false); // just for now
		gameWindow.pack();
		gameWindow.setVisible(true);
		
		// Questions window		
		JTextArea questionsWindow = new JTextArea();
		questionsWindow.setBounds(70, 100, 655, 260);
		questionsWindow.setFont(new Font("Serif", Font.ITALIC, 26));
		questionsWindow.setLineWrap(true);
		questionsWindow.setWrapStyleWord(true);
		questionsWindow.setText("Insert  XML questions  here");
		questionsWindow.setEditable(false);
		gameWindow.getContentPane().add(questionsWindow);
		
		// Labels
		JLabel playerName = new JLabel("<html><font color=red>"
				+ Data.namePlayer[0] + "</font></html>");
		JLabel playerScore = new JLabel(" : "
				+ Integer.toString(Data.scorePlayer[0]));
		playerName.setBounds(670, 3, 100, 60);
		playerScore.setBounds(700, 3, 100, 60);
		gameWindow.getContentPane().add(playerName);
		gameWindow.getContentPane().add(playerScore);

		// Create and set BUTTONS
		JButton[] buttons = new JButton[4];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setText("Insert XML answers here");
			gameWindow.getContentPane().add(buttons[i]);
			buttons[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String pName = playerName.getText();
		
					playerScore.setText(" : "
							+ Integer.toString(Data.scorePlayer[0] += 1));
				}
			});
		}
		buttons[0].setBounds(70, 410, 300, 60); // X, Y, Width,Height format;
		buttons[1].setBounds(420, 410, 300, 60);
		buttons[2].setBounds(70, 490, 300, 60);
		buttons[3].setBounds(420, 490, 300, 60);
		
		  try
	        {
			 buttons[1] .setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("18762.png").getImage(),new Point(0,0),"custom cursor"));
	        }catch(Exception e){}
		  buttons[1].setVisible(true);
        
	    }

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			 
				createGameWindow();
			}
		});
	}
}
