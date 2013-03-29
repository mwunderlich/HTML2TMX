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

public class Mapping {

	private int headerNumber;
	private String languageCode;
	
	public Mapping(String headerNumber, String languageCode) {
		this.headerNumber = Integer.parseInt(headerNumber);
		this.languageCode = languageCode;
	}
	public void setHeaderNumber(int headerNumber) {
		this.headerNumber = headerNumber;
	}
	public int getHeaderNumber() {
		return this.headerNumber;
	}
	
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getLanguageCode() {
		return this.languageCode;
	}
}
