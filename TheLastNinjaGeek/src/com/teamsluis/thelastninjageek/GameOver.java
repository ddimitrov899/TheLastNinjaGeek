package com.teamsluis.thelastninjageek;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

public class GameOver {
	public static JLabel gameOverLab = new JLabel();
	private static final String GAME_OVER = "resources/images/GameOverBACK.jpg";

	public static void gameOver() {

		String[] quotes = {
				"Running shallow  with a year's end sound:  river rapids. ",
				"Flowers of the grass:  scarcely shown, and withered name and all.",
				"Plum petals falling I look up...the sky,  a clear crisp moon. ",
				"Butterflies in flight:  the journey's end...",
				"Island of Eternity: a turtle dries its shell out  in the rays of a new sun.",
				"Farewell...pass as all things do  dew on the grass. ",
				"The running stream  is cool...the pebbles underfoot.",
				"A bright and pleasant autumn day to make death's journey. ",
				"Upon the lotus flower morning dew is  thinning out.   ",
				"Blow if you will, fall wind...the flowers have all faded.",
				"I leap from depths  of debt into the skies:   autumn of the dragon. ",
				"Fields dying off: the underside of grasses frozen hour of my death." };

		ImageIcon gameOverBack = new ImageIcon(GAME_OVER);
		gameOverLab.setIcon(gameOverBack);
		JLabel displayScores = new JLabel();
		displayScores.setText("High score : "
				+ Integer.toString(Data.scorePlayer[Data.currentPlayer]));
		displayScores.setForeground(Color.RED);
		JLabel deadQ = new JLabel(quotes[(int) (Math.random() * 11)]);
		deadQ.setBounds(160, 470, 570, 70);
		displayScores.setBounds(340, 530, 400, 70);
		deadQ.setFont(new java.awt.Font("Times New Roman", 1, 14));
		displayScores.setFont(new java.awt.Font("Times New Roman", 1, 24));
		Main.gameWindow.add(deadQ);
		Main.gameWindow.add(displayScores);
		deadQ.requestFocus(false);

		deadQ.getInputMap()
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "yes");

		deadQ.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "no");
		deadQ.getActionMap().put("yes", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				Main.gameWindow.setContentPane(Main.oldPane);
				Data.scorePlayer[Data.currentPlayer] = 0;
				Game.playerScore.setText(" : 0");
				Game.questions = 0;
				Game.winStrCtr = 0;
				Game.loStrCtr = 0;
				deadQ.setVisible(false);
				displayScores.setVisible(false);
				Data.setNinja();
				Game.nextQuestion();
			}
		});
		deadQ.getActionMap().put("no", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				Main.gameWindow.setContentPane(Main.newPane);
				Game.playerScore.setText(" : 0");
				Data.scorePlayer[Data.currentPlayer] = 0;
				Game.questions = 0;
				Game.winStrCtr = 0;
				Game.loStrCtr = 0;
				deadQ.setVisible(false);
				displayScores.setVisible(false);
				Data.alreadyPlayed = true;

			}
		});
	}
}
