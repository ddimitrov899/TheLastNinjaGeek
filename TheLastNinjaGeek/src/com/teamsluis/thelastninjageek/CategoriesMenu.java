package com.teamsluis.thelastninjageek;

import com.teamsluis.thelastninjageek.data.Answer;
import com.teamsluis.thelastninjageek.data.DataPersister;
import com.teamsluis.thelastninjageek.data.Question;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;



public class CategoriesMenu {
	
	private final static String xmlLocation = "resources/data/questions.xml";
	
	public static  void displayCategories() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(10, 2, 7, 7));
		pan.setBounds(20, 100, 750, 420);
		pan.setBackground(Color.black);
		Main.gameWindow.add(pan);
		
		Map<String, java.util.List<Question>> someVar;
		try {
			someVar = DataPersister.loadCategoriesFromXmlFile(xmlLocation);
			for (Map.Entry<String, java.util.List<Question>> entry : someVar
					.entrySet()) {
				
				 String[] r = entry.getKey().split("(?=\\p{Upper})");
				 JButton[] cat = new JButton[r.length];				 
				for (int i = 0; i < cat.length; i++) {
					cat[i] = new JButton();
					cat[i].setText(r[i]);
					pan.add(cat[i]);	
					cat[i].setForeground(Color.WHITE);
					cat[i].setBackground(Color.BLACK);
					cat[i].setFont(new java.awt.Font("Times New Roman", 1, 14));
						
					cat[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Game.gameComencing();
							pan.setVisible(false);
						}
					});
					
				}
			}
		} catch (ParserConfigurationException | SAXException
				| IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	
	}

