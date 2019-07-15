package com.pub.sapient.reader.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pub.sapient.model.Transaction;
import com.pub.sapient.reader.ITransactionReader;
import com.pub.sapient.utils.Utility;

public class XMLTransactionReader implements ITransactionReader {

	@Override
	public List<Transaction> read(File file) throws IOException {
		List<Transaction> list = new ArrayList<>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilders = dbFactory.newDocumentBuilder();
			Document document = dBuilders.parse(file);
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("transaction");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Transaction transaction = Utility.getTransaction(
							eElement.getElementsByTagName("transactionId").item(0).getTextContent(),
							eElement.getElementsByTagName("clientId").item(0).getTextContent(),
							eElement.getElementsByTagName("securityId").item(0).getTextContent(),
							eElement.getElementsByTagName("transactionType").item(0).getTextContent(),
							eElement.getElementsByTagName("transactionDate").item(0).getTextContent(),
							eElement.getElementsByTagName("marketValue").item(0).getTextContent(),
							eElement.getElementsByTagName("priority").item(0).getTextContent());
					list.add(transaction);
				}
			}

		} catch (Exception e) {

		}

		return list;
	}

}
