package com.teamsluis.thelastninjageek;

import com.teamsluis.thelastninjageek.data.Answer;
import com.teamsluis.thelastninjageek.data.Question;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CategoriesMenu {

	public static void displayCategories() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(10, 2, 7, 7));
		pan.setBounds(125, 100, 550, 420);
		pan.setBackground(Color.black);
		Main.gameWindow.add(pan);
		JButton[] category = new JButton[20]; // HardCoded
		for (int i = 0; i < 20; i++) {
			// for (int j = 0; j < 10; j++) {
			category[i] = new JButton();
			// category[i].setBounds((i+1)*200,(j+1)*50,150,30);
			pan.add(category[i]);
			// }
		}
	}
}
