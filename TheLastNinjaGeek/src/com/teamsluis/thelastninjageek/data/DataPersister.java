package com.teamsluis.thelastninjageek.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DataPersister {
	private final static String xmlLocation = "resources/data/questions.xml";

	private static List<Question> loadQuestionsFromXmlFile() throws ParserConfigurationException,
			SAXException, IOException {
		File fXmlFile = new File(xmlLocation);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		return null;
	}
}