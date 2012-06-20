/* StringTool.java is part of CosiTK
 * created 18.11.2011 
 *
 *
 * Copyright (c) 2011, The Cosi Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the <organization> nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  
 * 
 * @author lotherk
 *
 * 
 */
package org.cosiproject.toolkit.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class StringTool.
 */
public abstract class StringTool {
	private static Logger logger = Logger.getLogger(StringTool.class);

	private static final char delimiterChar = '\350';

	/**
	 * Joins a collection with the given delimiter. If the collection is empty
	 * or null, null is returned.
	 * 
	 * @param c
	 *            the Collection
	 * @param delimiter
	 *            the delimiter
	 * @return the joined string
	 */
	public static String join(Collection c, String delimiter) {
		String retVal = "";

		Iterator iter = c.iterator();
		while (iter.hasNext()) {
			retVal += iter.next();
			if (iter.hasNext()) {
				retVal += delimiter;
			}
		}
		if (retVal.length() == 0)
			retVal = null;
		return retVal;

	}

	public static String join(String[] data, String delimiter) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		for (String s : data)
			list.add(s);
		return join(list, delimiter);
	}

	public static String enquote(String string, char delimiter) {
		char[] charArray = string.toCharArray();
		boolean rpl = false;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == delimiter) {
				
				if (i == 0) {
					// it's the first char, so just replace until the next
					// quote appears (if it's not escaped..)
					rpl = true;
				} else
				if (charArray[i-1] == '\\') {
					rpl = true;
				} else {
					rpl = !rpl;
				}

			}
			else if (charArray[i] == ' ' && rpl) {
				charArray[i] = delimiterChar;
			} 
		}
		return new String(charArray);
	}

	public static String dequote(String string, char delimiter) {
		
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == delimiterChar)
				charArray[i] = delimiter;
		}
		String retVal = new String(charArray);
		return retVal;
	}
	
	public static String descape(String string) {
		return string.replaceAll("\\\"", "");
	}

	public static void main(String[] args) {
		String line = "Das ist ein 'String test zwo drei vier' foo und bar und bla";
		logger.debug(enquote(line, '\''));
	}

}
