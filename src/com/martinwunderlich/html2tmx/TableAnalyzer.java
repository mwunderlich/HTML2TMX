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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableAnalyzer {

	public static TMXFile buildTMXfile(HTMLtableDoc htmlDoc, String tmxFile,
			MappingsList mappingList, String srcLang) {

		TMXFile tmxDoc = new TMXFile();
		setTmxHeaderAttributes(tmxDoc, htmlDoc.getFilePath(), srcLang);
		
		Elements tableElements = htmlDoc.getTables();
        Elements tableRowElements = tableElements.select(":not(thead) tr");

        // Loop over all tables and extract source and target texts
        for (int i = 1; i < tableRowElements.size(); i++) {
           Element row = tableRowElements.get(i);
           Elements rowItems = row.select("td");
           if( rowItems.size() < 2) 
        	   continue;
           
    	   String source = "";
    	   String target = "";
    	   String targetLang = "";
           
           while( mappingList.hasNext() ) {
        	   Mapping map = mappingList.next();
        	   int headerNumber = map.getHeaderNumber();
        	   String languageCode = map.getLanguageCode();            	   
	   
        	   String segment = rowItems.get(headerNumber).text();
        	   if( languageCode.equals(srcLang) )
        		   source = segment;
        	   else {
        		   target = segment;
        		   targetLang = languageCode;
        	   }
           }   

           tmxDoc.appendTransUnitElement(source, srcLang, target, targetLang);          
        }        
        return tmxDoc;
	}

	private static void setTmxHeaderAttributes(TMXFile tmxDoc, String filePath, String srcLang) {
		tmxDoc.setAdminLang("EN");
		tmxDoc.setCreationTool("HTML2TMX by Martin Wunderlich");
		tmxDoc.setCreationToolVersion("1.0");
		tmxDoc.setDataType("plaintext");
		tmxDoc.setOTmf(filePath);
		tmxDoc.setSegType("sentence");
		tmxDoc.setSrcLang(srcLang);
	}
}
