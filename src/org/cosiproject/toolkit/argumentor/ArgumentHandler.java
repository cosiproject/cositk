/* ArgumentHandler.java is part of CosiTK
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

import org.apache.log4j.Logger;
import org.cosiproject.toolkit.exceptions.ArgumentorUnknownArgumentException;

/**
 * The ArgumentHandler parses the argumentline (simply a join of args[]) and
 * triggers pre-defined ArgumentCallbacks by providing an ArgumentEvent.
 * 
 * @see ArgumentCallback
 * @see ArgumentEvent
 */
public class ArgumentHandler {

	/** The root set. */
	private ArgumentSet rootArgumentSet;

	/** The logger. */
	private static Logger logger = Logger.getLogger(ArgumentHandler.class);

	private ArrayList<String> data = new ArrayList<String>();

	private ArgumentSwitchQueue argumentSwitchQueue;

	public ArgumentHandler() {

	}

	public ArgumentHandler(String rootSet) {
		this();
		setRootSet(new ArgumentSet(rootSet));
	}

	/**
	 * Parses args[] into Argument and calls the flush method of the
	 * ArgumentSwitchQueue
	 * 
	 * 
	 * @param args
	 *            the arguments
	 * @throws ArgumentorUnknownArgumentException
	 *             an unknown argument occured
	 * @see ArgumentSwitchQueue
	 */
	public void parse(String[] args) throws ArgumentorUnknownArgumentException {
		parse(args, null);
	}

	/**
	 * Sets the root ArgumentSet.
	 * 
	 * @param set
	 *            the new root set
	 * @see ArgumentSet
	 */
	public void setRootSet(ArgumentSet set) {
		rootArgumentSet = set;

	}

	/**
	 * Gets the root ArgumentSet. If it's null, it creates just a blank one.
	 * 
	 * @return the root set
	 */
	public ArgumentSet getRootSet() {
		if (rootArgumentSet == null)
			rootArgumentSet = new ArgumentSet(null);
		return rootArgumentSet;
	}

	public void addArgumentSet(ArgumentSet set) {
		getRootSet().addChild(set);
	}

	public ArgumentSet getArgumentSetByName(String name) {

		return null;
	}

	/**
	 * Returns the data read by the argument line.
	 * 
	 * @return the argument data
	 */
	public ArrayList<String> getData() {
		return data;
	}

	public static ArgumentHandler newArgumentHandler() {
		// TODO Auto-generated method stub
		return new ArgumentHandler();
	}

	public void parse(String[] args, Object e)
			throws ArgumentorUnknownArgumentException {
		data = new ArrayList<String>();
		clearData();
		if (args == null)
			return; // no arguments to parse.

		Argument param = null;
		ArrayList<Argument> listParams = new ArrayList<Argument>();
		for (int i = 0; i < args.length; i++) {
			String line = args[i];

			ArgumentPrefix prefix = null;
			for (ArgumentPrefix pfix : ArgumentPrefix.values()) {
				if (line.startsWith(pfix.getPrefix())) {
					prefix = pfix;
					break;
				}
			}

			if (prefix != null) {

				line = line.substring(prefix.getPrefix().length(),
						line.length());

				// check if the parameter has a value attached by = or :
				param = new Argument();
				boolean hasValueAttached = false;
				ArgumentSeparator separator = null;
				for (ArgumentSeparator sep : ArgumentSeparator.values()) {
					if (line.contains(String.valueOf(sep.getSeparator()))) {
						hasValueAttached = true;
						separator = sep;
						break;
					}
				}

				if (hasValueAttached) {
					String[] tmp = line.split(
							String.valueOf(separator.getSeparator()), 2);
					param.setParameter(tmp[0]);
					String[] tmp1 = tmp[1].split(",");
					for (String s : tmp1)
						param.addValue(new ArgumentParameter(s));

				} else {
					param.setParameter(line);
				}

				listParams.add(param);

			} else {

				if (param != null) {
					if (param.argumentAllowed()) {
						param.addValue(new ArgumentParameter(line));
					} else if (param.isLastParameter()) {
						data.add(line);
						param = null;
					} else {
						data.add(line);
						param = null;
					}

				} else {
					data.add(line);
				}
			}

		}

		if (listParams.size() > 0) {

			setArgumentSwitchQueue(new ArgumentSwitchQueue(listParams,
					getRootSet(), e));

		}

	}

	public void clearData() {
		data.clear();
		setArgumentSwitchQueue(null);
	}

	public ArgumentSwitchQueue getArgumentSwitchQueue() {
		return argumentSwitchQueue;
	}

	public void setArgumentSwitchQueue(ArgumentSwitchQueue argumentSwitchQueue) {
		this.argumentSwitchQueue = argumentSwitchQueue;
	}

	public void flush(Object encapsulated)
			throws ArgumentorUnknownArgumentException {
		if (getArgumentSwitchQueue() != null) {
			getArgumentSwitchQueue().flush(encapsulated);
		}
		clearData();
	}

}
