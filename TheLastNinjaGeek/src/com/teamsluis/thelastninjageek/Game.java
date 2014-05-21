package com.teamsluis.thelastninjageek;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
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
	static JTextPane questionsWindow = new JTextPane();
	static JButton[] buttons = new JButton[4];
	static JLabel playerScore = new JLabel(" : "
			+ Integer.toString(Data.scorePlayer[Data.currentPlayer]));
	static JButton button = new JButton();
	static boolean isClicked = false;
	static String CorA;
	private static ImageIcon bg;

	public static void nextQuestion() {
		questions++;		
		String category = getRandomCategory(questionsByCategory);
		Question question = getRandomQuestionForCategory(category);
		if(question.isAsked() == true) {
			System.out.println("ALREADY ASKED GETTING NEW!");
			return;
		}
		else {
		questionsWindow.setText(question.getValue());
		}
		
		for (int i = 0; i < buttons.length; i++) {
			//question.shuffleAnswers();
			List<Answer> answers = question.getAnswers();
			Answer currentAnswer = answers.get(i);
			Answer correctAnswer = question.getCorrectAnswer();
			CorA = ("<html>" + correctAnswer.getValue() + "</html>");
			questionsWindow.setText(question.getValue());
			buttons[i].setText("<html>" + currentAnswer.getValue() + "</html>");
			buttons[i].setToolTipText(currentAnswer.getValue());
			
		}
		System.out.println(CorA );
	}

	public static void gameComencing() {
		JLabel jl = new JLabel();
		bg = new ImageIcon("resources/images/GamePanel.jpg");
		jl.setIcon(bg);
		jl.setIconTextGap(-800);
		jl.setOpaque(true);
		jl.setSize(800, 600);
		Jokers.showJokers();
		questionsWindow.setBounds(70, 150, 655, 260);
		questionsWindow.setFont(new Font("Serif", Font.BOLD, 26));
		questionsWindow.setEditable(false);
		questionsWindow.setBackground(Color.BLACK);
	    questionsWindow.setForeground(Color.BLACK);
		questionsWindow.setOpaque(false);
		StyledDocument doc = questionsWindow.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		

		Main.gameWindow.add(questionsWindow);
		questions++;
		try {
			questionsByCategory = DataPersister
					.loadCategoriesFromXmlFile(xmlLocation);
		} catch (ParserConfigurationException | SAXException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String category = getRandomCategory(questionsByCategory);
		Question question = getRandomQuestionForCategory(category);
		if(question.isAsked() == true) {
			return;
		}
		else {
		questionsWindow.setText(question.getValue());
		}
		
		playerScore = new JLabel(" : "
				+ Integer.toString(Data.scorePlayer[Data.currentPlayer]));
		playerScore.setFont(new Font("Serif", Font.BOLD, 20));
		playerScore.setBounds(700, 30, 100, 40);
		Main.gameWindow.add(playerScore);
		playerScore.setForeground(Color.RED);

		// Create and set BUTTONS
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setBackground(Color.BLACK);
			buttons[i].setFont(new java.awt.Font("Times New Roman", 1, 12));
			List<Answer> answers = question.getAnswers();
			Answer currentAnswer = answers.get(i);
			buttons[i].setText("<html>" + currentAnswer.getValue() + "</html>");
			buttons[i].setToolTipText(currentAnswer.getValue());
			Main.gameWindow.add(buttons[i]); 
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Data.currentPlayerJokers[Data.currentPlayer][0] == 0) {
						for (int j = 0; j < buttons.length; j++) {
							buttons[j].setVisible(true);
						}
					}					
					JButton button = (JButton) e.getSource();
					Answer correctAnswer = question.getCorrectAnswer();
				//	System.out.printf(" CORA = %s%n BUTT = %s",CorA,button.getText());
					if (button.getText().equals("<html>" + correctAnswer.getValue() + "</html>")
							||button.getText().equals(CorA)) {
						if (Data.playerUsedShuriken == true) {
							Data.playerUsedShuriken = false;
							Data.scorePlayer[Data.currentPlayer] += Jokers.bitsReward;
						}
						int score = ++Data.scorePlayer[Data.currentPlayer];
						playerScore.setText(" : " + score);
						winStrCtr++;                      // adding to win streak;
						loStrCtr = 0;                       // nullifying lose streak;
						if (winStrCtr == 5) {
							Data.playerHasJoker[Data.currentPlayer]++;
						}
						else if (winStrCtr == 10){
							Data.scorePlayer[Data.currentPlayer] *= 2;
					    	Data.playerHasJoker[Data.currentPlayer]++;
						}
						else if (winStrCtr == 20){
							Data.scorePlayer[Data.currentPlayer] *= 4;
					    	Data.playerHasJoker[Data.currentPlayer]++;
					    	System.out.println("\u001B[31m"+"OUTSTANDING!!!!!!!");
						}
						System.out.printf("%nCorrect Answer down");
						nextQuestion();				
					} else {
						winStrCtr = 0;
						loStrCtr++;
						System.out.printf("%nWrong Answer Down!");
						if (loStrCtr > 4) {
							System.out.println("GAME OVER!!!!!!!!!!!!");
						}				
						nextQuestion();
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
		   Main.gameWindow.add(jl);
		// Show JokerWindow keyBinding
	}

	// There could be a bug when all question are exhausted
	// The method could fall in endless loop
	private static Question getRandomQuestionForCategory(String category) {
		List<Question> questions = questionsByCategory.get(category);
		Question question;

		do {
			int randomIndex = Randomizer.getRandomNumberInRange(0,
					questions.size() - 1);
			question = questions.get(randomIndex);
		} while (question.isAsked());

		return question;
	}

	private static String getRandomCategory(
			Map<String, List<Question>> questionsByCategory) {
		Set<String> categories = questionsByCategory.keySet();
		int i = 0, randommCategoryIndex = Randomizer.getRandomNumberInRange(0,
				categories.size() - 1);
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

}