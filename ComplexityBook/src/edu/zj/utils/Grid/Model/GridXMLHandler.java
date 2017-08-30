package edu.zj.utils.Grid.Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.zj.utils.Gadgets;

public class GridXMLHandler<V> extends DefaultHandler {
	private final Class<V> cls;

	public GridXMLHandler(Class<V> cls) {
		this.cls = cls;
	}

	private Grid<V> grid;

	public Grid<V> getGrid() {
		return grid;
	}

	// Called at start of an XML document
	@Override
	public void startDocument() throws SAXException {
	}

	// Called at end of an XML document
	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		int rowCount = 0;
		int columnCount = 0;
		V defaultValue = null;
		String name = null;
		if (qName.equalsIgnoreCase("grid")) {
			for (int i = 0; i < atts.getLength(); i++) {
				if (atts.getQName(i).equalsIgnoreCase("name")) {
					name = atts.getValue(i);
				}
				if (atts.getQName(i).equalsIgnoreCase("rowCount")) {
					rowCount = Integer.parseInt(atts.getValue(i));
				}
				if (atts.getQName(i).equalsIgnoreCase("columnCount")) {
					columnCount = Integer.parseInt(atts.getValue(i));
				}
				if (atts.getQName(i).equalsIgnoreCase("default")) {
					defaultValue = Gadgets.valueOf(cls, atts.getValue(i));
				}
			}
				grid = new Grid<V>(rowCount, columnCount);
			grid.setName(name);
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < rowCount; j++) {
					grid.set(i, j, defaultValue);
				}
			}
		}
		int row = 0, column = 0;
		V value = null;
		if (qName.equalsIgnoreCase("cell")) {
			for (int i = 0; i < atts.getLength(); i++) {
				if (atts.getQName(i).equalsIgnoreCase("row")) {
					row = Integer.parseInt(atts.getValue(i));
				}
				if (atts.getQName(i).equalsIgnoreCase("column")) {
					column = Integer.parseInt(atts.getValue(i));
				}
				if (atts.getQName(i).equalsIgnoreCase("value")) {
					value = Gadgets.valueOf(cls, atts.getValue(i));
				}
			}
			grid.set(row, column, value);
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
	}

	@Override
	public void characters(char[] ch, int start, int length) {
	}
}
