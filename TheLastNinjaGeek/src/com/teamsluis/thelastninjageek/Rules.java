package com.teamsluis.thelastninjageek;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Rules {
	private static final String RULES_PATH = "resources/data/rules.html";
	private static JTextPane rulesPane;
	private static String rules;
	private static List<JComponent> jComponentsOnView;

	public static void show() {
		jComponentsOnView = new ArrayList<JComponent>();
		
		Main.gameWindow.setBackground(Color.BLACK);
		
		try {
			rules = getRules(RULES_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rulesPane = new JTextPane();
		rulesPane.setBounds(0, 0, Main.gameWindow.getWidth() - 20,
				Main.gameWindow.getHeight());
		rulesPane.setFont(new Font("Serif", Font.ITALIC, 13));
		rulesPane.setEditable(false);
		rulesPane.setBackground(Color.BLACK);
		rulesPane.setOpaque(true);
		rulesPane.setHighlighter(null);

		if (rules == null) {
			rulesPane.setText("Rules Cannot Be loadead");
			Main.gameWindow.add(rulesPane);
		} else {
			rulesPane.setContentType("text/html");
			rulesPane.setText(rules);
			JScrollPane paneScrollPane = new JScrollPane(rulesPane);
			paneScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			paneScrollPane.setBounds(0, 0, Main.gameWindow.getWidth() - 4,
					Main.gameWindow.getHeight() - 200);
			Main.gameWindow.add(paneScrollPane);
			jComponentsOnView.add(paneScrollPane);
		}

		JButton backButton = new JButton("Back");
		Main.gameWindow.add(backButton);
		backButton.setBounds(330, Main.gameWindow.getHeight() - 140, 145, 30);
		backButton.setFont(new java.awt.Font("Tahoma", 0, 18));
		backButton.setOpaque(true);
		backButton.setFocusPainted(false);
		backButton.setBackground(Color.BLACK);
		
		jComponentsOnView.add(backButton);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (JComponent jComomentOnView : jComponentsOnView) {
					Main.gameWindow.remove(jComomentOnView);
				}
				
				jComponentsOnView.clear();
				Main.gameWindow.setContentPane(Main.newPane);
				Main.displayMenu();
				System.out.println("Going to Main");
			}
		});
	}

	private static String getRules(String rulesPath) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(rulesPath)));
		return content;
	}
}