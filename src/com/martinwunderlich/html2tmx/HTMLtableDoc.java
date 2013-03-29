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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLtableDoc {
	
  	private Document htmlDoc = null; 
	private String filePath = "";
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public HTMLtableDoc(String filePath) {
		this.filePath = filePath;
		
		try { 	 
			File f = new File(filePath);
			
			if(filePath.startsWith("file://")) {
				File in = new File(filePath.replace("file://", ""));
				htmlDoc = Jsoup.parse(in, null);
	   	 	}
	   	 	else if ( f.exists() && f.canRead() )
	   	 		htmlDoc = Jsoup.parse(f, null);
	   	 	else if( filePath.startsWith("http://") || filePath.startsWith("https://") )
	   	 		htmlDoc = Jsoup.connect(filePath).get();
	   	 	else 
	   	 		throw new IOException("Invalid file name provided: " + filePath);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getHeaders() {   	 
        List<String> headers = new ArrayList<String>();;
        Elements tableElements = htmlDoc.select("table");
        Elements tableHeaderEles = tableElements.select("thead tr th");
        
		for (int i = 0; i < tableHeaderEles.size(); i++) {
			headers.add(tableHeaderEles.get(i).text());
		}
		  
        return headers;
	}

	public Elements getTables() {
		return this.htmlDoc.select("table");
	}

	public int getColumnCount() {
		Elements tableElements = this.htmlDoc.select("table");
        Elements tableRowElements = tableElements.select(":not(thead) tr");

        int count = tableRowElements.get(0).select("td").size();
        
        return count;
	}
}
