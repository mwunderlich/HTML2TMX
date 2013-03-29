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

import java.util.List;

public class HTML2TMX {
   public static void main(String[] args) {
	  
	  String mode = "";
	  String tmxFilePath = "";
	  String htmlFile = "";
	  MappingsList mappingList = null;
	  
	  // Validate and read in command line parameters
	  if( args.length != 1 && args.length != 3) {
		  printHelpMessage();		  
		  System.exit(1);
	  }
	  
	  // Determine the mode;
	  // "analysis" retrieves table information for the user
	  // "extraction" build the TMX file based on the mappings provided;
	  if ( args.length == 1)
		  mode = "analysis";
	  else if ( args.length == 3) {
		  tmxFilePath = args[1];
		  mappingList = new MappingsList(args[2]);
		  mode = "extraction";
	  }
	    
	  // Analyse HTML table or build TMX file
	  htmlFile = args[0];
	  HTMLtableDoc htmlDoc = new HTMLtableDoc(htmlFile);
	  
	  if(mode == "analysis")
		  printTableHeaders(htmlDoc);
	  else {
		  String srcLang = getLangCodeOfFirstMapping(args[2]);
		  TMXFile tmxDoc = TableAnalyzer.buildTMXfile(htmlDoc, tmxFilePath, mappingList, srcLang);
		  tmxDoc.saveToFile(tmxFilePath);
		  System.out.println("TMX file created succesfully: " + tmxFilePath);
		  System.out.println("Number of trans-units added: " + tmxDoc.getTransUnitCount());
	  }
   }
	
	private static String getLangCodeOfFirstMapping(String str) {
		return str.split(",")[0].split(">")[1];
}

	/**
	 * @param htmlDoc
	 */
	private static void printTableHeaders(HTMLtableDoc htmlDoc) {
		List<String> tableHeaderEles = htmlDoc.getHeaders();	
		System.out.println("Table headers:");
		
		for (int i = 0; i < tableHeaderEles.size(); i++) {
		     System.out.println(i + " " + tableHeaderEles.get(i));
		}
		
		if(tableHeaderEles.size() == 0)
			System.out.println("No header row found. There are " + htmlDoc.getColumnCount() + " columns in the table.");
	}
	
	/**
	 * 
	 */
	private static void printHelpMessage() {
		System.out.println("ERROR: Missing parameter(s)!");
		System.out.println("Please provide either ");
		  System.out.println("- one parameter, the URL to the HTML file, to get the headers of the table");
		  System.out.println("OR");
		  System.out.println("- three parameters to build the TMX file, specifically: ");
		  System.out.println("	1) the URL to the HTML file,");
		  System.out.println("	2) the file path for the TMX outputfile, and");
		  System.out.println("	3) a mapping of header numbers to locale code in comma-separated form (e.g. 1>en-UK,2>de-DE...); NOTE: The first entry will be used as the source language.");
		  System.out.println("Please note that the parameters might need to be surrouned by quotes.");
	      System.out.println("Thank you.");
	}
}