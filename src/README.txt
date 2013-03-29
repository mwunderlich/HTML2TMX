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

 INSTRUCTIONS: 
 
 Prerequisites: 
 In order to run this file, you need to have a Java Runtime Environment (JRE) installed on your machine. 
 You can download Java here: 
 If you are running Mac OS, Java should already be installed. 
 
 How to run: 
 HTML2TMX is an executable jar file. This means you can run it from the command line (DOS Window or Shell)
 by entering the following command: 
 
 java -jar HTML2TMX.jar
 
 followed by parameters. 
 
 The extraction to TMX is a two-step process. As parameters please provide either 
	- one parameter, the URL to the HTML file, to get the headers of the table
	OR
	- three parameters to build the TMX file, specifically: 
		1) the URL to the HTML file,
		2) the file path for the TMX outputfile, and
		3) a mapping of header numbers to locale code in comma-separated form (e.g. 1>en-UK,2>de-DE...); NOTE: The first entry will be used as the source language.
 Please note that the parameters might need to be surrouned by quotes.

 For example: 
  First, you can analyze the HTML file (provided as a URL or file path) to see what headers there are in the table. 
  To do this, just provide one parameter, e.g.: 

  java -jar HTML2TMX.jar "http://www.linguee.com/english-german/search?source=auto&query=weekend"

  In order to extract the text to TMX, you need to tell the tool what the TMX output file should be 
  called and to which languages the headers or columns should be mapped. The mapping should be provided 
  in a format like this: "0>en-UK,1>de-DE" (comma-separated list where the digit is the number 
  of the header). 

  For instance: 
  java -jar HTML2TMX.jar "http://www.linguee.com/english-german/search?source=auto&query=weekend" "./tmxOut.tmx" "0>en-UK,1>de-DE"
 
 
 