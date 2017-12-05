package com.horstmann.corejava.v2ch03.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MyTest {

    public static void main(String[] args) {
        File file = new File("src\\main\\java\\com\\horstmann\\corejava\\v2ch03\\dom\\server.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        Document doc = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        if (doc != null) {
            Element root = doc.getDocumentElement();
//            NamedNodeMap map = element.getAttributes();
//            for (int i = 0; i < map.getLength(); i++) {
//                Node node = map.item(i);
//                String name = node.getNodeName();
//                String value = node.getNodeValue();
//                System.out.println("name: " + name + "value: " + value);
//            }
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child instanceof Element) {
                    Element childElement = (Element) child;
                    Text textNode = (Text) childElement.getFirstChild();
                    String text = textNode.getData().trim();
                    System.out.println(childElement.getTagName() + ": " + "value: " + text);
                }


            }
        }

    }


}
