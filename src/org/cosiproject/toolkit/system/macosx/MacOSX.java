/* MacOSX.java is part of CosiTK
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
package org.cosiproject.toolkit.system.macosx;



import java.awt.event.ActionEvent;


import org.apache.log4j.Logger;
import org.cosiproject.toolkit.signal.SignalHandler;
import org.cosiproject.toolkit.system.DefaultPlattformHandler;



// TODO: Auto-generated Javadoc
/**
 * The Class MacOSX.
 */
public class MacOSX extends DefaultPlattformHandler {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(MacOSX.class);
	
	/**
	 * Instantiates a new mac osx.
	 */
	public MacOSX() {
		// Setting MenuBar on top.
				logger.debug("Setting MenuBar Property");
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				 try {
		             // Generate and register the OSXAdapter, passing it a hash of all the methods we wish to
		             // use as delegates for various com.apple.eawt.ApplicationListener methods
		             OSXAdapter.setQuitHandler(this, getClass().getDeclaredMethod("handleQuit", (Class[])null));
		             OSXAdapter.setAboutHandler(this, getClass().getDeclaredMethod("handleAbout", (Class[])null));
		             OSXAdapter.setPreferencesHandler(this, getClass().getDeclaredMethod("handlePreferences", (Class[])null));
		            
		             //OSXAdapter.setFileHandler(this, getClass().getDeclaredMethod("loadImageFile", new Class[] { String.class }));
		         } catch (Exception e) {
		             System.err.println("Error while loading the OSXAdapter:");
		             e.printStackTrace();
		         }
	}

	/* (non-Javadoc)
	 * @see org.cosiproject.toolkit.system.DefaultPlattformHandler#initialize()
	 */
	@Override
	public void initialize() {
		
		
	}

	
	/* (non-Javadoc)
	 * @see org.cosiproject.toolkit.system.DefaultPlattformHandler#requestUserAttention(boolean)
	 */
	public void requestUserAttention(boolean bool) {
		// TODO Auto-generated method stub
		//Application.getApplication().requestUserAttention(bool);
	}

	
	/**
	 * Handle about.
	 */
	public void handleAbout() {
		// TODO Auto-generated method stub
		logger.debug("handleAbout");
	}

	
	/**
	 * Handle open application.
	 */
	public void handleOpenApplication() {
		// TODO Auto-generated method stub
		logger.debug("handleOpenApplication");
	}

	
	/**
	 * Handle open file.
	 */
	public void handleOpenFile() {
		// TODO Auto-generated method stub
		logger.debug("handleOpenFile");
	}

	
	/**
	 * Handle preferences.
	 */
	public void handlePreferences() {
		// TODO Auto-generated method stub
		logger.debug("handlePreferences");
	}

	
	/**
	 * Handle print file.
	 */
	public void handlePrintFile() {
		// TODO Auto-generated method stub
		logger.debug("handlePrintFile");
	}

	
	/**
	 * Handle quit.
	 */
	public void handleQuit() {
		// TODO Auto-generated method stub
		logger.debug("handleQuit");
		SignalHandler.handleQuit();
	}

	
	/**
	 * Handle re open application.
	 */
	public void handleReOpenApplication() {
		// TODO Auto-generated method stub
		logger.debug("handleReOpenApplication");
		
	}

	
}
