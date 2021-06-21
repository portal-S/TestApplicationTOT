package com.totsystems.utils;

import com.totsystems.model.History;
import com.totsystems.model.Security;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Component
public class XmlParseUtil {


    public List<Security> parseSecurity(MultipartFile inputFile) throws IOException { //parse from request file to Securities list
        List<Security> securities = new LinkedList<>();
        parse(inputFile.getInputStream(), (attributes) -> {
            securities.add(new Security(
                    Integer.parseInt(attributes.getNamedItem("id").getNodeValue()),
                    attributes.getNamedItem("secid").getNodeValue(),
                    attributes.getNamedItem("regnumber").getNodeValue(),
                    attributes.getNamedItem("name").getNodeValue(),
                    attributes.getNamedItem("emitent_title").getNodeValue()
            ));
        });
        return securities;
    }

    public List<History> parseHistory(MultipartFile inputFile) throws IOException {  //parse from request file to Histories list
        List<History> histories = new LinkedList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        parse(inputFile.getInputStream(), (attributes) -> {
            String open = attributes.getNamedItem("OPEN").getNodeValue();
            String close = attributes.getNamedItem("CLOSE").getNodeValue();
            try {
                histories.add(new History(
                        attributes.getNamedItem("SECID").getNodeValue(),
                        formatter.parse(attributes.getNamedItem("TRADEDATE").getNodeValue()),
                        Integer.parseInt(attributes.getNamedItem("NUMTRADES").getNodeValue()),
                        Double.parseDouble(attributes.getNamedItem("OPEN").getNodeValue().isEmpty() ? "0" : open),
                        Double.parseDouble(attributes.getNamedItem("CLOSE").getNodeValue().isEmpty() ? "0" : close)
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return histories;
    }

    public void parse(InputStream inputStream, Consumer<NamedNodeMap> t) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList child = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < child.getLength(); i++) {
                if (child.item(i).getNodeName().equals("data")) {
                    NodeList childData = child.item(i).getChildNodes();
                    for (int j = 0; j < childData.getLength(); j++) {
                        if (childData.item(j).getNodeName().equals("rows")) {
                            Node node = childData.item(j).getFirstChild();
                            while (node != null) {
                                if (node.getNodeName().equals("row") && node.getAttributes().getLength() > 10) {
                                    NamedNodeMap attributes = node.getAttributes();
                                    t.accept(attributes);
                                }
                                node = node.getNextSibling();
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public Security parseSecurityByString(String data) {  //parse from response string to Security
        AtomicReference<Security> security = new AtomicReference<>(new Security());
        parse(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)), (attributes) -> {
            security.set(new Security(
                    Integer.parseInt(attributes.getNamedItem("id").getNodeValue()),
                    attributes.getNamedItem("secid").getNodeValue(),
                    attributes.getNamedItem("regnumber").getNodeValue(),
                    attributes.getNamedItem("name").getNodeValue(),
                    attributes.getNamedItem("emitent_title").getNodeValue()
            ));
        });
        return security.get();
    }
}
