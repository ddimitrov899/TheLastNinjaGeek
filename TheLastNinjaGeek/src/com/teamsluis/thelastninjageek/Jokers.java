package com.teamsluis.thelastninjageek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Jokers {
	public static void showJokers() {

		if (Data.jokersAllowed == true) {
			JButton[] jokersWindow = new JButton[3];
			for (int i = 0; i < jokersWindow.length; i++) {
				jokersWindow[i] = new JButton();
				jokersWindow[i].setEnabled(false);
				jokersWindow[i].setVisible(true);
				jokersWindow[i].setBounds(720, 60 + ((i + 1) * 70), 70, 60);
				;
				Main.gameWindow.add(jokersWindow[i]);
			}

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

				jokersWindow[1].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Data.scorePlayer[Data.currentPlayer]++;
						jokersWindow[1].setEnabled(false);
						Data.currentPlayerJokers[Data.currentPlayer][1]--;
						Data.playerHasJoker[Data.currentPlayer]--;
						System.out
								.printf("Player %d current Sudden Strike jokers are %d.\nPlayer %d has %d jokers left.",
										Data.currentPlayer,
										Data.currentPlayerJokers[Data.currentPlayer][1],
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
