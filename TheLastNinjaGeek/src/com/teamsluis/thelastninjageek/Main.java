package com.teamsluis.thelastninjageek;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Main {
	int choice = 0;
	public static JFrame gameWindow;

	public void createGameWindow() {

		// Create and display window frames.
		gameWindow = new JFrame("The Last Ninja Geek™");
		gameWindow.getContentPane().setLayout(null);
		gameWindow.setPreferredSize(new Dimension(800, 600));
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false); // just for now
		gameWindow.pack();
		gameWindow.setVisible(true);
		// List
		JLabel[] menuList = new JLabel[3];
		String[] bName = { "   START", " OPTIONS", "    EXIT" };
		int gap = 370;
		for (int i = 0; i < menuList.length; i++) {
			menuList[i] = new JLabel();
			menuList[i].setFont(new java.awt.Font("Tahoma", 2, 18));
			menuList[i].setText(bName[i]);
			menuList[i].setBounds(340, gap, 100, 20);
			menuList[i].setOpaque(true);
			gameWindow.add(menuList[i]);
			gap += 40;
		}
		// Key EVENTS
		final Color bkgr = menuList[1].getBackground();
		menuList[0].setBackground(Color.RED);
		menuList[1].getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		menuList[1].getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		menuList[1].getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

		menuList[1].getActionMap().put("up", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				menuList[choice].setBackground(bkgr);
				choice--;
				if (choice < 0) {
					choice = 2;
				}
				menuList[choice].setBackground(Color.red);
			}
		});
		menuList[1].getActionMap().put("down", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				menuList[choice].setBackground(bkgr);
				choice++;
				if (choice > 2) {
					choice = 0;
				}
				menuList[choice].setBackground(Color.red);
			}
		});

		menuList[1].getActionMap().put("enter", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (choice == 0) {
					Game.gameComencing();
				}
				for (int i = 0; i < menuList.length; i++) {
					menuList[i].setVisible(false);
				}
				System.out.println("Entering new game state..");
			}
		});
	}

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main main = new Main();
				main.createGameWindow();
			}
		});
	}
}