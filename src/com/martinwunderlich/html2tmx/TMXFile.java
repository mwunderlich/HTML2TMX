/**************************************************************************
 HTML2TMX - A converter to create TMX files from HTML tables
 
 HTML2TMX is based on the JSOUP HTML parser by Jonathan Hedley. 
 See http://jsoup.org/ for details.
 
 Copyright (C) 2013 Martin Wunderlich
               Home page: http://www.martinwunderlich.com/
               Support and questions: martin@wunderlich.com

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License (LGPL) as published by
 the Free Software Foundation. See http://www.gnu.org/copyleft/lesser.html for details.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 **************************************************************************/

package com.martinwunderlich.html2tmx;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TMXFile {

	private Document tmxDoc = null;
	private Element rootElement = null;
	private Element headerElement = null;
	private Element bodyElement = null;
	private int transUnitCount = 0;
	
	public TMXFile() {
	
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
			tmxDoc = docBuilder.newDocument();
	
			rootElement = tmxDoc.createElement("tmx");
			rootElement.setAttribute("version", "1.4");
			headerElement = buildHeader(); 
			bodyElement  = tmxDoc.createElement("body");
	      
			rootElement.appendChild(headerElement);
			rootElement.appendChild(bodyElement);
	      
			tmxDoc.appendChild(rootElement);
	    } catch (Exception e) {
	       e.printStackTrace();
	    }	     
	}

	private Element buildHeader() {
		Element header = this.tmxDoc.createElement("header");
		
		return header;
	}
	
	public void setCreationTool(String tool) {
		this.headerElement.setAttribute("creationtool", tool);
	}
	
	public void setCreationToolVersion(String version) {
		this.headerElement.setAttribute("creationtoolversion", version);
	}
	
	public void setDataType(String type) {
		this.headerElement.setAttribute("datatype", type);
	}
	
	public void setSegType(String type) {
		this.headerElement.setAttribute("segtype", type);
	}
	
	public void setAdminLang(String lang) {
		this.headerElement.setAttribute("adminlang", lang);
	}
	
	public void setSrcLang(String lang) {
		this.headerElement.setAttribute("srclang", lang);
	}
	
	public void setOTmf(String oTmf) {
		this.headerElement.setAttribute("o-tmf", oTmf);
	}
	
	public void saveToFile(String filePath) throws TransformerFactoryConfigurationError {
		try{
		      TransformerFactory transformerFactory = TransformerFactory.newInstance();
		      Transformer transformer = transformerFactory.newTransformer();
		      DOMSource source = new DOMSource(this.tmxDoc);
		
		      StreamResult result =  new StreamResult( new File(filePath) );
		      transformer.transform(source, result);	
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
	}

	public void appendTransUnitElement(String source, String srcLang, String target, String targetLang) {
        Element transUnitElement = createTransUnit(tmxDoc, source, srcLang, target, targetLang);
        this.bodyElement.appendChild(transUnitElement);
        this.transUnitCount++;
	}
	
	private Element createTransUnit(Document tmxDoc, String source, String srcLang, String target, String targetLang) 
	{
		Element transUnitElement = null; 
		
		try {
			transUnitElement = tmxDoc.createElement("tu");
			
		    Element sourceTransUnitVariantElement = tmxDoc.createElement("tuv");
		    sourceTransUnitVariantElement.setAttribute("xml:lang", srcLang);
		    Element segElement = tmxDoc.createElement("seg");
		    segElement.appendChild(tmxDoc.createTextNode(source));
		    sourceTransUnitVariantElement.appendChild(segElement);
		    transUnitElement.appendChild(sourceTransUnitVariantElement);
		    
		    Element targetTransUnitVariantElement = tmxDoc.createElement("tuv");
		    targetTransUnitVariantElement.setAttribute("xml:lang", targetLang);
		    Element targetSegElement = tmxDoc.createElement("seg");
		    targetSegElement.appendChild(tmxDoc.createTextNode(target));
		    targetTransUnitVariantElement.appendChild(targetSegElement);
		    transUnitElement.appendChild(targetTransUnitVariantElement);	    
		}
		catch(Exception ex) {
			System.out.println("Error while trying to create trans unit element: " + ex.getMessage());		
			return null;
		}
		
		return transUnitElement;
	}

	
	public int getTransUnitCount() {
		return this.transUnitCount;
	}
}
