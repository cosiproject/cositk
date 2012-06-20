/* ObjectTool.java is part of CosiTK
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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectTool.
 */
public class ObjectTool {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ObjectTool.class);
	
	/** The whitespace. */
	private static String whitespace = "";
	
	/**
	 * Clone.
	 *
	 * @param object the object
	 * @return the object
	 */
	public static Object clone(Object object) {
		String ws = new String(whitespace);
		whitespace += "    ";
		logger.debug(ws + "Trying to clone " + object.getClass().getCanonicalName());
		Object retVal = null;
		
		try {
			retVal = Class.forName(object.getClass().getCanonicalName()).newInstance();
			
		
			ArrayList<Method> getterList = new ArrayList<Method>();
			
			// get all getters
			for(Method m : object.getClass().getMethods()) {
				if(m.getName().startsWith("get")) {
					getterList.add(m);
				}
			}
			logger.debug(ws + "- converting... ");
			for(Method getterMethod : getterList) {
				
				if(getterMethod.getParameterTypes().length == 0) {
					// only use get methods WITHOUT parameters!
					logger.debug(ws + "`-- " + getterMethod.getName());
					Object getVal;
					try {
						getVal = getterMethod.invoke(object);
						if(getVal == null)
							continue;
						Object cloneVal = getVal;
//						Object cloneVal = ObjectTool.clone(getVal);
//						if(cloneVal == null)
//							continue;
						//logger.debug("\t\t" + getterMethod.getReturnType().getCanonicalName() +": "+ object + "." + getterMethod.getName()+"() = " + getVal);
						//logger.debug("  `-- Searching for: " + getterMethod.getName());
						String s = getterMethod.getName();
						s = s.replaceFirst("g", "s");
						logger.debug(ws + "  `-- trying "+s+"("+cloneVal.getClass()+")....");
						Method m = retVal.getClass().getMethod(s, cloneVal.getClass());
						m.invoke(retVal, cloneVal);
						logger.debug(ws + "    `-- information set: " + cloneVal.toString());
						
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						logger.debug(ws + e);
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						logger.debug(ws + e);
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						logger.debug(ws + e);
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						logger.debug(ws + e);
					}
					
				} else {
					
				}
				
				
			}
			
			
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.debug(ws + e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.debug(ws + e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.debug(ws + e);
		}
			
		if(retVal != null)
		logger.debug(ws + "returning: " + retVal);
		if(whitespace.length() > 4)
			whitespace = whitespace.substring(0, whitespace.length()-4);
		else
			whitespace = "";
		
		return retVal;
	}
}
