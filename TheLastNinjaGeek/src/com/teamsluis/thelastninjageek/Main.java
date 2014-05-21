package com.teamsluis.thelastninjageek;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	private static final String NINJA_IMAGE_LOCATION = "resources/images/ninja.jpg";

	public static int choice = 0;

	public static JLabel opPic = new JLabel();
	public static JFrame gameWindow;
	public static Container oldPane = new Container();
	public static JLabel newPane = new JLabel();

	private static final String[] MENU_OPTIONS = { "SINGLE PLAYER",
			"MULTI PLAYER", "OPTIONS", "RULES", "EXIT" };

	public static void createGameWindow() {

		// Create and display window frames.
		gameWindow = new JFrame("The Last Ninja Geek™");
		gameWindow.getContentPane().setLayout(null);
		gameWindow.setPreferredSize(new Dimension(800, 600));
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false); // just for now
		gameWindow.pack();
		gameWindow.setVisible(true);
		oldPane = gameWindow.getContentPane();

		ImageIcon picLabel = new ImageIcon(NINJA_IMAGE_LOCATION);

		newPane.setIcon(picLabel);
		gameWindow.setContentPane(newPane);
	}

	public static void displayMenu() {
		JButton[] menuList = new JButton[MENU_OPTIONS.length];
		int gap = 340;

		for (int i = 0; i < menuList.length; i++) {
			menuList[i] = new JButton();
			menuList[i].setFont(new java.awt.Font("Tahoma", 0, 18));
			menuList[i].setText(MENU_OPTIONS[i]);
			menuList[i].setBounds(330, gap, 200, 30);
			menuList[i].setOpaque(true);
			menuList[i].setFocusPainted(false);
			menuList[i].setHorizontalTextPosition(JLabel.CENTER);
			menuList[i].setBackground(Color.BLACK);
			gameWindow.add(menuList[i]);
			gap += 40;
		}

		menuList[MenuOption.SINGLEPLAYER.getValue()]
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Data.setNinja();
						gameWindow.setContentPane(oldPane);
						Game.gameComencing();
					}
				});

		menuList[MenuOption.MULTIPLAYER.getValue()]
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gameWindow.setContentPane(oldPane);
						if (Data.ninjas > 1) {
							Multyplayer.displayMulty();
						}
					}
				});

		menuList[MenuOption.OPTIONS.getValue()]
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gameWindow.setContentPane(oldPane);
						Options.optionsPanel();
					}
				});

		menuList[MenuOption.RULES.getValue()]
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gameWindow.setContentPane(oldPane);
						Rules.show();
					}
				});
		
		menuList[MenuOption.EXIT.getValue()].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowEvent windowEvent = new WindowEvent(gameWindow,
						WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue()
						.postEvent(windowEvent);
				
			}
		});
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGameWindow();
				displayMenu();
			}
		});
	}
}
