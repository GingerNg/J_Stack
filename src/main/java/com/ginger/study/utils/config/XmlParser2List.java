package com.ginger.study.utils.config;

/**
 */

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

public class XmlParser2List {

    protected static Logger logger = LoggerFactory.getLogger(XmlParser2List.class);

    private static XMLInputFactory FACTORY;

    private StringBuilder currentText;
    private int count = -1;
    private Map<Object, Object> parserMap = new HashMap<Object, Object>();
    private List<Map<Object, Object>> parserMapList = Lists.newArrayList();

    private Map<Object, Object> subMap = null;
    private List<Map<Object, Object>> subMapList = null;
    private Map<Object, Object> subDetlMap = null;

    static {
        FACTORY = XMLInputFactory.newInstance();
        logger.info("XMLInputFactory implementation: {}", FACTORY);
    }

    public XmlParser2List() {
    }

    /**
     * @param stream
     * @param startElement XML第一个标签，用于校验
     * @return
     */
    public List<Map<Object, Object>> parse(InputStream stream, String startElement) {
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

    private List<Map<Object, Object>> parse(XMLStreamReader reader, String startElement) throws XMLStreamException {
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

    private List<Map<Object, Object>> parseComponent(XMLStreamReader reader) throws XMLStreamException {

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLEvent.CDATA:
                case XMLEvent.SPACE:
                case XMLEvent.CHARACTERS:
                    processText(reader.getText());
                    System.out.println(count + " :CHARACTERS: " + reader.getText());
                    break;
                case XMLEvent.END_ELEMENT:
                    ended(reader.getLocalName());
                    System.out.println(count + " :END_ELEMENT: " + reader.getLocalName());
                    break;
                case XMLEvent.START_ELEMENT:
                    startElement(reader.getLocalName());
                    System.out.println(count + " :START_ELEMENT: " + reader.getLocalName());
                    break;
            }
        }

        return this.parserMapList;
    }

    private void startElement(String localName) {
        currentText = new StringBuilder(256);
        count++;

        if (count == 3) {
            subMap = new HashMap<>();
            subMapList = Lists.newArrayList();
            subMap.put(localName, subMapList);
        }
    }

    private void ended(String localName) {
        count--;
        if (currentText != null) {
            if (count == 3) {
                subDetlMap = new HashMap<>();
                subDetlMap.put(localName, currentText.toString());
                subMapList.add(subDetlMap);
            } else {
                String text = currentText.toString();
                parserMap.put(localName, text);
            }
        }
        currentText = null;

        if (count == 0) {
            parserMapList.add(parserMap);
            parserMap = new HashMap<Object, Object>();
        }
    }

    private void processText(String text) {
        text = text.replaceAll("\t|\n", "").trim();
        if (null != text && !"".equals(text)) {
            currentText.append(text);
        }
    }
}
