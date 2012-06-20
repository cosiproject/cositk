/* ArgumentSet.java is part of CosiTK
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
 * An ArgumentSet has a list of ArgumentSwitches. For a single application only
 * one ArgumentSet is needed, the root ArgumentSet.
 * 
 * @see ArgumentSwitch
 */
public class ArgumentSet {

	/** The name. */
	private String name;

	/** The switch list. */
	private List<ArgumentSwitch> argumentSwitches = new ArrayList<ArgumentSwitch>();

	/** The logger. */
	private static Logger logger;

	/** The childs. */
	private ArrayList<ArgumentSet> childs = new ArrayList<ArgumentSet>();

	/** The parent. */
	private ArgumentSet parent;

	/**
	 * Gets the ArgumentCallback by Argument.
	 * 
	 * @param name
	 *            the name
	 * @return the argument by parameter
	 */
	public ArgumentCallback getArgumentByParameter(String name) {
		ArgumentSwitch retVal = getArgumentSwitchByParameter(name);
		if (retVal != null)
			return retVal.getCallback();

		for (ArgumentSet child : getChilds()) {
			ArgumentCallback retValArgumentor = child
					.getArgumentByParameter(name);
			if (retVal != null)
				return retValArgumentor;
		}
		return null;

	}

	/**
	 * Gets the ArgumentSwitch by Argument.
	 * 
	 * @param param
	 *            the param
	 * @return the option by parameter
	 */
	private ArgumentSwitch getArgumentSwitchByParameter(String param) {

		for (ArgumentSwitch swtch : argumentSwitches) {
			if (swtch.hasArgument(param))
				return swtch;
		}
		return null;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public ArgumentSet getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 * 
	 * @param parent
	 *            the new parent
	 */
	public void setParent(ArgumentSet parent) {
		this.parent = parent;
	}

	/**
	 * Adds a child.
	 * 
	 * @param child
	 *            the child
	 */
	public void addChild(ArgumentSet child) {
		logger.debug("Adding " + child.getName());
		childs.add(child);
	}

	/**
	 * Gets the childs.
	 * 
	 * @return the childs
	 */
	public ArrayList<ArgumentSet> getChilds() {
		return childs;
	}

	/**
	 * Sets the childs.
	 * 
	 * @param childs
	 *            the new childs
	 */
	public void setChilds(ArrayList<ArgumentSet> childs) {
		this.childs = childs;
	}

	/**
	 * Checks for childs.
	 * 
	 * @return true, if successful
	 */
	public boolean hasChilds() {
		return childs.size() > 0;
	}

	/**
	 * Adds the ArgumentSwitch.
	 * 
	 * @param argumentSwitch
	 *            the option
	 * @return the argument set
	 */
	public ArgumentSet addArgumentSwitch(ArgumentSwitch argumentSwitch) {
		argumentSwitches.add(argumentSwitch);
		return this;
	}

	/**
	 * Instantiates a new argument set.
	 * 
	 * @param name
	 *            the name
	 */
	public ArgumentSet(String name) {
		logger = Logger.getLogger("[ArgSet]" + name);
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Gets the argument set.
	 * 
	 * @param name
	 *            the name
	 * @return the argument set
	 */
	public ArgumentSet getArgumentSet(String name) {
		if (getName().equals(name))
			return this;

		for (ArgumentSet tmp : getChilds()) {
			ArgumentSet tmp2 = tmp.getArgumentSet(name);
			if (tmp2 != null)
				return tmp2;
		}
		return null;
	}

	/**
	 * Gets the ArgumentSwitch list.
	 * 
	 * @return the switch list
	 */
	public List<ArgumentSwitch> getArgumentSwitches() {
		return argumentSwitches;
	}

	/**
	 * Sets the ArgumentSwitch list.
	 * 
	 * @param argumentSwitches
	 *            the new ArgumentSwitch list
	 */
	public void setArgumentSwitches(List<ArgumentSwitch> argumentSwitches) {
		this.argumentSwitches = argumentSwitches;
	}

	/**
	 * Gets the ArgumentSwitch by Argument.
	 * 
	 * @param param
	 *            the param
	 * @return the ArgumentSwitch by Argument
	 */
	public ArgumentSwitch getArgumentSwitchByParameter(Argument param) {

		for (ArgumentSwitch option : getArgumentSwitches()) {
			if (option.hasArgument(param.getName()))
				return option;
		}
		for (ArgumentSet child : getChilds()) {
			ArgumentSwitch retVal = child.getArgumentSwitchByParameter(param);
			if (retVal != null)
				return retVal;
		}

		return null;
	}

	public boolean hasArgument(Argument argument) {
		// TODO Auto-generated method stub
		if(getArgumentSwitchByParameter(argument) != null)
			return true;
		return false;
	}

}
