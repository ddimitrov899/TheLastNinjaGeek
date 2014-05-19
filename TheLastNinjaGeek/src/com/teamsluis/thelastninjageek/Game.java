package com.teamsluis.thelastninjageek;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private static Map<String, List<Question>> questionsByCategory;
	private final static String xmlLocation = "resources/data/questions.xml";

	public static void gameComencing() {

		JTextArea questionsWindow = setWindowDimensions();

		try {
			questionsByCategory = DataPersister
					.loadCategoriesFromXmlFile(xmlLocation);
		} catch (ParserConfigurationException | SAXException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String category = getRandomCategory(questionsByCategory);
		Question question = getRandomQuestionForCategory(category);

		questionsWindow.setText(question.getValue());

		setPlayer();

		// Create and set BUTTONS
		JButton[] buttons = new JButton[4];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			question.shuffleAnswers();
			List<Answer> answers = question.getAnswers();
			Answer currentAnswer = answers.get(i);
			
			buttons[i].setText(currentAnswer.getValue());
			Main.gameWindow.add(buttons[i]); // here

			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton) e.getSource();
				    Answer correctAnswer = question.getCorrectAnswer();
				    System.out.println(button.getText());
				    
				    if (button.getText().equals(correctAnswer.getValue())) {
						//TO DO	
					} else {
						//TO DO
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

	//There could be a bug when all question are exhausted
	//The method could fall in endless loop
	private static Question getRandomQuestionForCategory(String category) {
		List<Question> questions = questionsByCategory.get(category);
		Question question;
		
		do {
			int randomIndex = Randomizer.getRandomNumberInRange(0, questions.size() - 1);
			question = questions.get(randomIndex);
		} while (question.isAsked());
		
		return question;
	}

	private static String getRandomCategory(Map<String, List<Question>> questionsByCategory) {
		Set<String> categories = questionsByCategory.keySet();
		int i = 0, randommCategoryIndex = Randomizer.getRandomNumberInRange(0, categories.size() - 1);
		String category = null;
		
		for (String currentCategory : categories) {
			if (i == randommCategoryIndex) {
				category = currentCategory;
				break;
			}
			
			i++;
		}
		
		return category;
	}

	private static void setPlayer() {
		// Labels
		JLabel playerName = new JLabel("<html><font color=red>"
				+ Data.namePlayer[0] + "</font></html>");
		JLabel playerScore = new JLabel(" : "
				+ Integer.toString(Data.scorePlayer[0]));
		playerName.setBounds(670, 3, 100, 60);
		playerScore.setBounds(700, 3, 100, 60);
		Main.gameWindow.add(playerName);
		Main.gameWindow.add(playerScore);
	}

	private static JTextArea setWindowDimensions() {
		JTextArea questionsWindow = new JTextArea();
		questionsWindow.setBounds(70, 100, 655, 260);
		questionsWindow.setFont(new Font("Serif", Font.ITALIC, 26));
		questionsWindow.setLineWrap(true);
		questionsWindow.setWrapStyleWord(true);
		questionsWindow.setEditable(false);
		Main.gameWindow.add(questionsWindow);
		return questionsWindow;
	}

}
