package com.ginger.study.utils.config;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 */

public class XmlParser2Map {

    protected static Logger logger = LoggerFactory.getLogger(XmlParser2List.class);

    private static XMLInputFactory FACTORY;

    private StringBuilder currentText;
    private Map<Object, Object> parserMap;
    private Map<Integer, Object> stackMap = new HashMap<Integer, Object>();
    private List<Map<Object, Object>> subMapList = Lists.newArrayList();

    private Stack levelStack = new Stack();
    private Stack levelNameStack = new Stack();

    private int count = -2;

    static {
        FACTORY = XMLInputFactory.newInstance();
        logger.info("XMLInputFactory implementation: {}", FACTORY);
    }

    public XmlParser2Map() {
    }

    public Map<Object, Object> parse(InputStream stream, String startElement) {
        XMLStreamReader streamReader = null;
        try {
            streamReader = FACTORY.createXMLStreamReader(stream);
            return parse(streamReader, startElement);
        } catch (XMLStreamException e) {
            throw new RuntimeException("Failed to parse xml stream", e);//TODO add exception type
        } finally {
            if (streamReader != null)
                try {
                    streamReader.close();
                } catch (XMLStreamException e) {
                    logger.trace("Failed to close XMLStreamException");
                }
        }
    }

    private Map<Object, Object> parse(XMLStreamReader reader, String startElement) throws XMLStreamException {
        assert (reader.getEventType() == XMLEvent.START_DOCUMENT);

        do {
            if (reader.nextTag() == XMLStreamConstants.START_ELEMENT) {
                if (null != startElement && !reader.getLocalName().equals(startElement))
                    throw new RuntimeException("xml document root must be " + startElement);
                break;
            }
        } while (reader.hasNext());

        return parseComponent(reader);
    }

    private Map<Object, Object> parseComponent(XMLStreamReader reader) throws XMLStreamException {

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLEvent.CDATA:
                case XMLEvent.SPACE:
                case XMLEvent.CHARACTERS:
                    processText(reader.getText());
//                    System.out.println(count + " :CHARACTERS: " + reader.getText());
                    break;
                case XMLEvent.END_ELEMENT:
                    ended(reader.getLocalName());
//                    System.out.println(count + " :END_ELEMENT: " + reader.getLocalName());
                    break;
                case XMLEvent.START_ELEMENT:
                    startElement(reader.getLocalName());
//                    System.out.println(count + " :START_ELEMENT: " + reader.getLocalName());
                    break;
            }
        }

        return (Map<Object, Object>) this.stackMap.get(1);
    }

    private void startElement(String localName) {
        currentText = new StringBuilder(256);

        count++;

        if (levelStack.isEmpty()) {
            levelStack.push(count);
        } else {
            int lastLevel = (int) levelStack.peek();
            if (count > lastLevel) {
                levelStack.push(count);
            }
        }
    }

    private void ended(String localName) {
        if ((null != currentText) && !"".equals(currentText) && (currentText.length() != 0)) {
            String text = currentText.toString();
            parserMap = (Map<Object, Object>) stackMap.get(count);
            parserMap.put(localName, text);
        } else {
            parserMap = (Map<Object, Object>) stackMap.get(count+1);
            if (count > 1 && null != parserMap && !parserMap.isEmpty()) {
                subMapList.add(parserMap);
                ((Map<Object, Object>) stackMap.get(1)).put(localName, subMapList);
                stackMap.remove(count+1);
            } else if (1 == count) {
                subMapList = Lists.newArrayList();
            }
        }
        currentText = null;

        count--;
    }

    private void processText(String text) {
        text = text.replaceAll("\t|\n", "").trim();
        if (null != text && !"".equals(text)) {
            currentText.append(text);

            if (stackMap.isEmpty()) {
                parserMap = new HashMap<Object, Object>();
                stackMap.put(count, parserMap);
            } else {
                if (null == stackMap.get(count)) {
                    parserMap = new HashMap<Object, Object>();
                    stackMap.put(count, parserMap);
                }
            }
        }
    }
}
