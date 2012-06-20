/* SignalHandler.java is part of CosiTK
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
package org.cosiproject.toolkit.signal;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class SignalHandler.
 */
public class SignalHandler {

	/** The list callback. */
	private static ArrayList<SignalCallback> listCallback;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(SignalHandler.class);
	
	/**
	 * Handle quit.
	 */
	public static void handleQuit() {
		logger.debug("Quit!");
		for(SignalCallback callback : getListCallback()) {
			if(callback.getSignal().equals(Signal.QUIT))
				callback.process();
		}
			
	
		
	}
	
	/**
	 * Adds the callback.
	 *
	 * @param callback the callback
	 */
	public static void addCallback(SignalCallback callback) {
		getListCallback().add(callback);
	}
	
	/**
	 * Removes the callback.
	 *
	 * @param callback the callback
	 */
	public static void removeCallback(SignalCallback callback) {
		getListCallback().remove(callback);
	}

	/**
	 * Gets the list callback.
	 *
	 * @return the list callback
	 */
	private static ArrayList<SignalCallback> getListCallback() {
		if(listCallback == null)
			listCallback = new ArrayList<SignalCallback>();
		return listCallback;
	}

	/**
	 * Sets the list callback.
	 *
	 * @param listCallback the new list callback
	 */
	private static void setListCallback(ArrayList<SignalCallback> listCallback) {
		SignalHandler.listCallback = listCallback;
	}
	
	

}
