package com.teamsluis.thelastninjageek.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataPersister {
	public static Map<String, List<Question>> loadCategoriesFromXmlFile(String xmlLocation)
			throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File(xmlLocation);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		Element rootElement = doc.getDocumentElement();
		rootElement.normalize();
		Map<String, List<Question>> questionsByCategory = getQuestionsByCategories(rootElement);

		return questionsByCategory;
	}

	private static Map<String, List<Question>> getQuestionsByCategories(
			Element rootElement) {
		NodeList children = rootElement.getChildNodes();
		Map<String, List<Question>> questionsByCategory = new HashMap<String, List<Question>>();

		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE
					&& node.getNodeName().equals("question")) {
				Question question = getQuestionFromNode(node);
				String questionCategory = question.getCategory();

				if (questionsByCategory.containsKey(questionCategory)) {
					List<Question> questions = questionsByCategory
							.get(questionCategory);
					questions.add(question);
				} else {
					List<Question> questions = new ArrayList<Question>();
					questions.add(question);
					questionsByCategory.put(questionCategory, questions);
				}
			}
		}

		return questionsByCategory;
	}

	private static Question getQuestionFromNode(Node questionNode) {
		NamedNodeMap attributes = questionNode.getAttributes();
		String category = getAttributeValue("category", attributes);
		int correctAnswerId = Integer.parseInt(getAttributeValue(
				"correct-answer-id", attributes));
		String value = getAttributeValue("value", attributes);
		List<Answer> answers = getAnswers(questionNode);
		Question question = new Question(category, correctAnswerId, value,
				answers);

		return question;
	}

	private static List<Answer> getAnswers(Node questionNode) {
		if (!questionNode.hasChildNodes()) {
			throw new NullPointerException(
					"Question node should have question nodes"
							+ questionNode.getNodeValue());
		}

		List<Answer> answers = new ArrayList<Answer>(Question.TOTAL_ANSWERS);

		NodeList answerNodes = questionNode.getChildNodes();

		for (int i = 0; i < answerNodes.getLength(); i++) {
			Node answerNode = answerNodes.item(i);

			if (answerNode.getNodeType() == Node.ELEMENT_NODE
					&& answerNode.getNodeName().equals("answer")) {
				NamedNodeMap answerNodeAttributes = answerNode.getAttributes();
				int id = Integer.parseInt(getAttributeValue("id",
						answerNodeAttributes));
				String value = answerNode.getTextContent();
				answers.add(new Answer(id, value));
			}
		}

		return answers;
	}

	private static String getAttributeValue(String attributeName,
			NamedNodeMap attributes) {
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute = attributes.item(i);
			String currentAttributeName = attribute.getNodeName();

			if (currentAttributeName.equals(attributeName)) {
				return attribute.getNodeValue();
			}
		}

		throw new IllegalArgumentException("Non existing attribute: "
				+ attributeName);
	}
}
