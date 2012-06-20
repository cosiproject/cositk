/* ArgumentSwitch.java is part of CosiTK
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

/**
 * The class ArgumentSwitch holds its ArgumentParameters and the
 * ArgumentCallback. It's basically just a container. It also keeps the
 * information how often the ArgumentSwitch may appear and what separator it
 * has.<br />
 * <br />
 * Example:<br />
 * 
 * ./yourprogram --param1 val1 val2 val3<br />
 * <br />
 * param1 (Argument) with its ArgumentValues val1, val2 and val3 would
 * be an ArgumentSwitch.
 * 
 * @see Argument
 * @see ArgumentCallback
 * @see ArgumentSeparator
 * @see ArgumentMultiplicity
 */
public class ArgumentSwitch {

	/** The help text. */
	private String helpText;

	/** The parameter. */
	private List<Argument> arguments = new ArrayList<Argument>();

	/** The separator. */
	private ArgumentSeparator separator;

	/** The multiplicity. */
	private ArgumentMultiplicity multiplicity;

	/** The argumentor. */
	private ArgumentCallback argumentor;
	
	private ArgumentDelimiter delimiter;

	/**
	 * Instantiates a new argument option.
	 * 
	 * @param parameter
	 *            the parameter
	 * @param separator
	 *            the separator
	 * @param multiplicity
	 *            the multiplicity
	 * @param helpText
	 *            the help text
	 * @param argumentor
	 *            the argumentor
	 */
	public ArgumentSwitch(Argument parameter,
			ArgumentSeparator separator, ArgumentMultiplicity multiplicity,
			String helpText, ArgumentCallback argumentor) {
		this.arguments.add(parameter);
		this.helpText = helpText;
		this.argumentor = argumentor;
		this.separator = separator;
		this.multiplicity = multiplicity;
	}

	/**
	 * Instantiates a new argument option.
	 * 
	 * @param parameter
	 *            the parameter
	 * @param separator
	 *            the separator
	 * @param multiplicity
	 *            the multiplicity
	 * @param helpText
	 *            the help text
	 * @param argumentor
	 *            the argumentor
	 */
	public ArgumentSwitch(List<Argument> parameter,
			ArgumentSeparator separator, ArgumentMultiplicity multiplicity,
			String helpText, ArgumentCallback argumentor) {
		this.arguments = parameter;
		this.helpText = helpText;
		this.argumentor = argumentor;
		this.separator = separator;
		this.multiplicity = multiplicity;
	}

	/**
	 * Gets the help text.
	 * 
	 * @return the help text
	 */
	public String getHelpText() {
		return helpText;
	}

	/**
	 * Sets the help text.
	 * 
	 * @param helpText
	 *            the new help text
	 */
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	/**
	 * Gets the parameter.
	 * 
	 * @return the arguments
	 */
	public List<Argument> getArguments() {
		return arguments;
	}

	/**
	 * Sets the parameter.
	 * 
	 * @param arguments
	 *            the new parameter
	 */
	public void setArguments(List<Argument> arguments) {
		this.arguments = arguments;
	}

	/**
	 * Gets the argument.
	 * 
	 * @return the callback
	 */
	public ArgumentCallback getCallback() {
		return argumentor;
	}

	/**
	 * Sets the argumentor.
	 * 
	 * @param argumentor
	 *            the new argumentor
	 */
	public void setCallback(ArgumentCallback argumentor) {
		this.argumentor = argumentor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Argument: " + getArguments().toString() + ", Separator: "
				+ getSeparator().getSeparator() + ", Multiplicity: "
				+ getMultiplicity() + ", Argumentor: "
				+ getCallback().toString();
	}

	/**
	 * Checks for argument.
	 * 
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public boolean hasArgument(String name) {
		// TODO Auto-generated method stub
		for (Argument param : getArguments())
			if (param.getName().equals(name))
				return true;
		return false;
	}

	/**
	 * Gets the separator.
	 * 
	 * @return the separator
	 */
	public ArgumentSeparator getSeparator() {
		return separator;
	}

	/**
	 * Sets the separator.
	 * 
	 * @param separator
	 *            the new separator
	 */
	public void setSeparator(ArgumentSeparator separator) {
		this.separator = separator;
	}

	/**
	 * Gets the multiplicity.
	 * 
	 * @return the multiplicity
	 */
	public ArgumentMultiplicity getMultiplicity() {
		return multiplicity;
	}

	/**
	 * Sets the multiplicity.
	 * 
	 * @param multiplicity
	 *            the new multiplicity
	 */
	public void setMultiplicity(ArgumentMultiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	/**
	 * Gets the parameter.
	 * 
	 * @param param
	 *            the param
	 * @return the parameter
	 */
	public Argument getArgument(Argument param) {
		for (Argument p : getArguments()) {
			if (p.getName().equals(param.getName()))
				return p;
		}
		return null;
	}

	public ArgumentDelimiter getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(ArgumentDelimiter delimiter) {
		this.delimiter = delimiter;
	}
	
	

}
