package com.goku.breeze.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXContentHandler;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class GokuSAXReader extends SAXReader {
	DocumentFactory docFactory;
	Locator locator;

	public GokuSAXReader(DocumentFactory docFactory) {
		// TODO Auto-generated constructor stub
		super(docFactory);
		this.docFactory = docFactory;
	}

	public GokuSAXReader(DocumentFactory docFactory, Locator locator) {
		// TODO Auto-generated constructor stub
		super(docFactory);
		this.locator = locator;
		this.docFactory = docFactory;
	}

	@Override
	protected SAXContentHandler createContentHandler(XMLReader reader) {
		return new GokuSAXContentHandler(this.getDocumentFactory(), super.getDispatchHandler());
	}

	@Override
	public Document read(InputSource in) throws DocumentException {
		try {
			XMLReader reader = this.getXMLReader();

			reader = this.installXMLFilter(reader);

			EntityResolver thatEntityResolver = super.getEntityResolver();

			if (thatEntityResolver == null) {
				thatEntityResolver = this.createDefaultEntityResolver(in.getSystemId());
				super.setEntityResolver(thatEntityResolver);
			}

			reader.setEntityResolver(thatEntityResolver);

			SAXContentHandler contentHandler = this.createContentHandler(reader);
			contentHandler.setEntityResolver(thatEntityResolver);
			contentHandler.setInputSource(in);

			boolean internal = this.isIncludeInternalDTDDeclarations();
			boolean external = this.isIncludeExternalDTDDeclarations();

			contentHandler.setIncludeInternalDTDDeclarations(internal);
			contentHandler.setIncludeExternalDTDDeclarations(external);
			contentHandler.setMergeAdjacentText(this.isMergeAdjacentText());
			contentHandler.setStripWhitespaceText(this.isStripWhitespaceText());
			contentHandler.setIgnoreComments(this.isIgnoreComments());
			reader.setContentHandler(contentHandler);

			this.configureReader(reader, contentHandler);
			((GokuSAXContentHandler) contentHandler).setDocFactory((DocumentFactoryWithLocator) this.docFactory);
			contentHandler.setDocumentLocator(this.locator);
			reader.parse(in);

			return contentHandler.getDocument();
		} catch (Exception e) {
			if (e instanceof SAXParseException) {
				// e.printStackTrace();
				SAXParseException parseException = (SAXParseException) e;
				String systemId = parseException.getSystemId();

				if (systemId == null) {
					systemId = "";
				}

				String message = "Error on line " + parseException.getLineNumber() + " of document " + systemId + " : "
						+ parseException.getMessage();

				throw new DocumentException(message, e);
			} else {
				throw new DocumentException(e.getMessage(), e);
			}
		}
	}
}
