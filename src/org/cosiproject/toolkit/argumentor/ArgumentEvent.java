/* ArgumentEvent.java is part of CosiTK
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

import java.util.List;

import org.cosiproject.toolkit.tools.StringTool;


/**
 * An event which indicates that an argument-defined action occured. The event
 * includes the ActionParameter that triggered this event plus its value(s).
 * 
 * @see Argument
 */
public class ArgumentEvent {

	/** The parameter. */
	private Argument parameter;
	
	private Object capsulatedObject;

	/**
	 * Instantiates a new argument event.
	 * 
	 * @param param
	 *            the param
	 */
	public ArgumentEvent(Argument param) {
		parameter = param;
	}

	public ArgumentEvent(Argument argParam, Object e) {
		this(argParam);
		setCapsulatedObject(e);
	}

	/**
	 * Gets the parameter.
	 * 
	 * @return the parameter
	 */
	public Argument getParameter() {
		return parameter;
	}

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public List<ArgumentParameter> getValues() {
		return parameter.getValues();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (parameter == null)
			return null;

		String retVal = null;
		retVal = parameter.getName();
		retVal += "[" + StringTool.join(parameter.getValues(), ",") + "]";

		return retVal;
	}

	public Object getCapsulatedObject() {
		return capsulatedObject;
	}

	public void setCapsulatedObject(Object capsulatedObject) {
		this.capsulatedObject = capsulatedObject;
	}
	
	
}
