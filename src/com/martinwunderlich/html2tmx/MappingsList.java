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

import java.util.ArrayList;
import java.util.List;

public class MappingsList {

	private List<Mapping> mappingList = new ArrayList<Mapping>();
	private int iteratorCounter = 0;
	
	public MappingsList(String str) {
		String mappings[] = null;		  
		mappings = str.split(",");
		
        for(String mapping : mappings) {
     	   String headerNumber = mapping.split(">")[0];
     	   String languageCode = mapping.split(">")[1];            	   
     	   Mapping map = new Mapping(headerNumber, languageCode);
     	   this.addMapping(map);     	   
        }   
	}

	public void addMapping(Mapping map) {
		this.mappingList.add(map);		
	}
	
	public boolean hasNext() {
		boolean endReached = this.iteratorCounter == this.mappingList.size(); 
		if(endReached)
			this.iteratorCounter = 0;
		return !endReached;
	}
	
	public Mapping next() {
		Mapping m = this.mappingList.get(this.iteratorCounter);
		this.iteratorCounter++;
		return m;
	}
}
