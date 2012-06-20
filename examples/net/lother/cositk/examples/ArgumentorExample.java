/* ArgumentExample.java is part of CosiTK
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
package net.lother.cositk.examples;

import org.apache.log4j.Logger;
import org.cosiproject.toolkit.argumentor.Argument;
import org.cosiproject.toolkit.argumentor.ArgumentCallback;
import org.cosiproject.toolkit.argumentor.ArgumentEvent;
import org.cosiproject.toolkit.argumentor.ArgumentHandler;
import org.cosiproject.toolkit.argumentor.ArgumentMultiplicity;
import org.cosiproject.toolkit.argumentor.ArgumentPrefix;
import org.cosiproject.toolkit.argumentor.ArgumentSeparator;
import org.cosiproject.toolkit.argumentor.ArgumentSet;
import org.cosiproject.toolkit.argumentor.ArgumentSwitch;
import org.cosiproject.toolkit.exceptions.ArgumentorUnknownArgumentException;
import org.cosiproject.toolkit.tools.StringTool;


/**
 * The Class ArgumentExample.
 * 
 * Just shows an example of the Argumentor system.
 */
public class ArgumentorExample {

	/** The logger. */
	private static Logger logger = Logger.getLogger(ArgumentorExample.class);
	private static ArgumentHandler argumentHandler = new ArgumentHandler();
	static {
		ArgumentSet argumentSet = new ArgumentSet("test");
		argumentSet.addArgumentSwitch(new ArgumentSwitch(new Argument(
				"test1", ArgumentPrefix.DOUBLEDASH), ArgumentSeparator.BLANK,
				ArgumentMultiplicity.ZERO_OR_ONE, "ein test",
				new ArgumentCallback() {

					@Override
					public void process(ArgumentEvent e) {
						// TODO Auto-generated method stub
						logger.debug(e);
					}

				}));
		argumentSet.addArgumentSwitch(new ArgumentSwitch(new Argument(
				"test2", ArgumentPrefix.DOUBLEDASH), ArgumentSeparator.EQUALS,
				ArgumentMultiplicity.ZERO_OR_ONE, "ein test",
				new ArgumentCallback() {
			
					@Override
					public void process(ArgumentEvent e) {
						// TODO Auto-generated method stub
						logger.debug(e);
					}

				}));
		argumentHandler.setRootSet(argumentSet);
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		//  If no arguments are passed, use the default 
		if (args.length == 0) {
			String line = "--test1 This is a test! --test2=This is also a test! --test3:This too! -test4 Also this is a test and -test5:even this.";
			String[] argv = line.split(" ");
			args = argv;
		}
		try {
			argumentHandler.parse(args);
		} catch (ArgumentorUnknownArgumentException e) {
			logger.warn("Unknown Argument: " + e.getArgument() + " ["
					+ StringTool.join(e.getArgument().getValues(), ", ") + "]");
		}
	}
}
