package com.hieunguyen.lab;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static Collection<Integer> getIdsByMessage(String xmlStr, String message) {
        Collection<Integer> result = new HashSet<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlStr));
            Document doc = builder.parse(is);

            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("entry");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    NodeList messages = element.getElementsByTagName("message");
                    if (messages == null || messages.getLength() == 0) {
                        continue;
                    }
                    for (int j = 0; j < messages.getLength(); j++) {
                        Node msgNode = messages.item(j);
                        String msg = msgNode.getTextContent();
                        if (!message.equals(msg)) {
                            continue;
                        }
                        String id = ((Element) node).getAttribute("id");
                        if (id == null || "".equals(id)) {
                            continue;
                        }
                        try {
                            result.add(Integer.parseInt(id));
                        } catch(NumberFormatException e) {
                            // ignore
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\"?>\n" +
                "<log>\n" +
                "    <entry id = \"1\">\n" +
                "        <message>Application started</message>\n" +
                "    </entry>\n" +
                "    <entry id = \"a\">\n" +
                "        <message>Application ended</message>\n" +
                "    </entry>\n"+
                "    <entry id = \"b\">\n" +
                "        <message>Application ended</message>\n" +
                "    </entry>\n"+
                "    <entry>\n" +
                "        <message>Application ended</message>\n" +
                "    </entry>\n"+
                "    <entry id=\"3\">\n" +
                "    </entry>\n"+
                "</log>";

        Collection<Integer> results = getIdsByMessage(xml, "Application ended");

        for(int i : results) {
            System.out.print(i + " ");
        }
    }
}
