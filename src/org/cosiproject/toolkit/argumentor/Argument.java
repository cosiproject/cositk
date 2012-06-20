/* Argument.java is part of CosiTK
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

/**
 * Defines an Argument with an ArgumentPrefix. You can use as many
 * Argument for an ArgumentSwitch as you want. "Symlinks" like "-s"
 * and "--server" can be created that way. The List ArgumentParameter gets filled by
 * the ArgumentHandler once the Argument is triggered.
 * 
 * @see ArgumentPrefix
 * @see ArgumentSwitch
 */
public class Argument {

	/** The logger. */
	private static Logger logger = Logger.getLogger(Argument.class);

	/** The argument. */
	private String argument;

	/** The prefix. */
	private ArgumentPrefix prefix;

	/** The values. */
	private List<ArgumentParameter> values;

	/**
	 * Instantiates a new argument argument.
	 */
	public Argument() {

	}

	/**
	 * Instantiates a new argument argument.
	 * 
	 * @param argument
	 *            the argument name
	 * @param prefix
	 *            the ArgumentPrefix
	 * @see ArgumentPrefix
	 */
	public Argument(String parameter, ArgumentPrefix prefix) {
		this.argument = parameter;
		this.prefix = prefix;
	}

	/**
	 * Gets the argument.
	 * 
	 * @return the argument
	 */
	public String getName() {
		return argument;
	}

	/**
	 * Sets the argument.
	 * 
	 * @param argument
	 *            the new argument
	 */
	public void setParameter(String parameter) {
		this.argument = parameter;
	}

	/**
	 * Gets the prefix.
	 * 
	 * @return the prefix
	 */
	public ArgumentPrefix getPrefix() {
		return prefix;
	}

	/**
	 * Sets the prefix.
	 * 
	 * @param prefix
	 *            the new prefix
	 */
	public void setPrefix(ArgumentPrefix prefix) {
		this.prefix = prefix;
	}

	/**
	 * Equals.
	 * 
	 * @param argument
	 *            the argument
	 * @return boolean
	 */
	public boolean equals(String parameter) {
		return getName().equals(parameter);

	}

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public List<ArgumentParameter> getValues() {
		if (values == null)
			values = new ArrayList<ArgumentParameter>();
		return values;
	}

	/**
	 * Sets the values.
	 * 
	 * @param values
	 *            the new values
	 */
	public void setValues(List<ArgumentParameter> values) {
		this.values = values;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getPrefix().getPrefix()+getName();
	}

	/**
	 * Adds an ArgumentParameter.
	 * 
	 * @param value
	 *            the value
	 * @see ArgumentParameter
	 */
	public void addValue(ArgumentParameter value) {
		// TODO Auto-generated method stub
		getValues().add(value);
	}

	public boolean argumentAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isLastParameter() {
		// TODO Auto-generated method stub
		return false;
	}

}
