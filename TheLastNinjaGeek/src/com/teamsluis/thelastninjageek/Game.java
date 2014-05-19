package com.teamsluis.thelastninjageek;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Map;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.teamsluis.thelastninjageek.data.Answer;
import com.teamsluis.thelastninjageek.data.DataPersister;
import com.teamsluis.thelastninjageek.data.Question;

public class Game {
	static int winStrCtr = 0;
	static int loStrCtr = 0;
	public static int questions = 0;
	private final static String xmlLocation = "resources/data/questions.xml";

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

					Map<String, java.util.List<Question>> someVar;
					try {
						someVar = DataPersister
								.loadCategoriesFromXmlFile(xmlLocation);
						for (Map.Entry<String, java.util.List<Question>> entry : someVar
								.entrySet()) {
							System.out.println(entry.getKey());
						}
					} catch (ParserConfigurationException | SAXException
							| IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (questions < 5 && Data.ninjas > 1) {
						questions++;
						// Correct answer logic
						// if (Question.getCorrectAnswer() == Answer.) {
						// winStrCtr++; // counting correct answers and starting
						// streak;
						// loStrCtr = 0; // nullifying lose streak;
						// playerScore.setText(" : "+
						// Integer.toString(Data.scorePlayer[Data.currentPlayer]
						// += 1));
						// }
						// Wrong Answer logic
						// else if (questions == 2) {
						// winStrCtr = 0; // nullifying win streak
						// loStrCtr--; // counting wrong answers and starting
						// streak;
						//
						// }

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
