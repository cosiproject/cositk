/* ArgumentSwitchQueue.java is part of CosiTK
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
package org.cosiproject.toolkit.argumentor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cosiproject.toolkit.exceptions.ArgumentorUnknownArgumentException;

// TODO: Auto-generated Javadoc
/**
 * The ArgumentSwitchQueue basically just holds a list of ArgumentSwitches and
 * its main method flush().
 * 
 * 
 */
public class ArgumentSwitchQueue {

	/** The data. */
	private static ArrayList<ArgumentSwitch> argumentSwitches = new ArrayList<ArgumentSwitch>();

	/** The logger. */
	private static Logger logger = Logger.getLogger(ArgumentSwitchQueue.class);

	/** The param not found list. */
	private ArrayList<String> paramNotFoundList = new ArrayList<String>();

	private List<Argument> params;

	private ArgumentSet argumentSet;

	private Object encapsulated;

	public ArgumentSwitchQueue(List<Argument> params, ArgumentSet argumentSet,
			Object e) {
		this.params = params;
		this.argumentSet = argumentSet;
		this.encapsulated = e;
	}

	public ArgumentSwitchQueue() {
		
	}

	/**
	 * Adds an ArgumentSwitch
	 * 
	 * @param argumentSwitch
	 *            the option
	 */
	public void add(ArgumentSwitch argumentSwitch) {
		argumentSwitches.add(argumentSwitch);
	}

	

	/**
	 * Gets the param not found list.
	 * 
	 * @return the param not found list
	 */
	public ArrayList<String> getParamNotFoundList() {
		return paramNotFoundList;
	}

	/**
	 * Sets the param not found list.
	 * 
	 * @param paramNotFoundList
	 *            the new param not found list
	 */
	public void setParamNotFoundList(ArrayList<String> paramNotFoundList) {
		this.paramNotFoundList = paramNotFoundList;
	}

	public void flush() throws ArgumentorUnknownArgumentException {
		flush(encapsulated);
		
		
	}
	
	public void flush(Object encapsulated) throws ArgumentorUnknownArgumentException {
		for (Argument param : params) {
			ArgumentSwitch argSwitch = argumentSet
					.getArgumentSwitchByParameter(param);
			if (argSwitch != null) {
				Argument argParam = argSwitch.getArgument(param);
				argParam.setValues(param.getValues());
				argSwitch.getCallback().process(new ArgumentEvent(argParam, encapsulated));
			} else {
				throw new ArgumentorUnknownArgumentException(param);
			}
		}
	}

	public static ArrayList<ArgumentSwitch> getArgumentSwitches() {
		return argumentSwitches;
	}

	public static void setArgumentSwitches(
			ArrayList<ArgumentSwitch> argumentSwitches) {
		ArgumentSwitchQueue.argumentSwitches = argumentSwitches;
	}

	public List<Argument> getParams() {
		return params;
	}

	public void setParams(List<Argument> params) {
		this.params = params;
	}

	public ArgumentSet getArgumentSet() {
		return argumentSet;
	}

	public void setArgumentSet(ArgumentSet argumentSet) {
		this.argumentSet = argumentSet;
	}
	
	

}
