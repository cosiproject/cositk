/* Localizer.java is part of CosiTK
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
package org.cosiproject.toolkit.localizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Localizer.
 */
@XmlRootElement(name = "localizer")
@XmlAccessorType(XmlAccessType.NONE)
public class Localizer {
	
	/** The keys. */
	@XmlElement(name = "key", required = true)
	private List<LocalizerKey> keys;
	
	/** The lang dirs. */
	private ArrayList<File> langDirs;
	
	/** The instance. */
	private Localizer instance;
	
	/** The logger. */
	private static Logger logger;
	
	/** The clazz. */
	private Class clazz;
	
	/** The file url. */
	private URL fileURL;
	
	/** The file. */
	private File file;

	
	
	/**
	 * Instantiates a new localizer.
	 */
	protected Localizer() {

	}

	// No one is allowed to create an instance!
	/**
	 * Instantiates a new localizer.
	 *
	 * @param clazz the clazz
	 */
	protected Localizer(Class clazz) {
		logger = Logger.getLogger("L[" + clazz.getSimpleName() + "]");
		this.clazz = clazz;
		
		// Uhm.. what was planned here?
		if(langDirs == null) {
			
		} 
		// search file
		String resourceArg = "lang" + File.separator + clazz.getCanonicalName().replaceAll("\\.",
				File.separator)
				+ ".xml";
		fileURL = ClassLoader.getSystemClassLoader().getResource(resourceArg);
		if(fileURL == null) {
			resourceArg = "lang" + File.separator + clazz.getCanonicalName().replaceAll("\\.",
					File.separator)
					+ "_default.xml";
			fileURL = ClassLoader.getSystemClassLoader().getResource(resourceArg);
		}
		if(fileURL == null) {
			resourceArg = "lang" + File.separator + clazz.getCanonicalName().replaceAll("\\.",
					File.separator)
					+ Locale.getDefault().getDisplayLanguage().toLowerCase() + ".xml";
			fileURL = ClassLoader.getSystemClassLoader().getResource(resourceArg);
		}
		
		JAXBContext context;
		try {
			
			
			context = JAXBContext.newInstance(Localizer.class);
			Unmarshaller um = context.createUnmarshaller();
			
			file = new File(fileURL.getFile());
			instance = (Localizer) um.unmarshal(new FileReader(file));
			
		} catch (JAXBException e) {
				// TODO Auto-generated catch block
			logger.debug(clazz, e);
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			logger.debug(clazz, e);
		} catch (StringIndexOutOfBoundsException e){
			logger.debug(clazz, e);
		} catch (NullPointerException e) {
			logger.debug(clazz, e);
		}
	
			
		 
	}

	/**
	 * Instantiates a new localizer.
	 *
	 * @param file the file
	 */
	protected Localizer(File file) {
		// TODO Auto-generated constructor stub
		logger = Logger.getLogger("L[" + clazz.getSimpleName() + "]");
		JAXBContext context;
		try {
			
			
			context = JAXBContext.newInstance(Localizer.class);
			Unmarshaller um = context.createUnmarshaller();
			
			File langFile = new File(fileURL.getFile());
			instance = (Localizer) um.unmarshal(new FileReader(langFile));
			setFile(langFile);
			
		} catch (JAXBException e) {
				// TODO Auto-generated catch block
			logger.error(e);
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			logger.error(e);
		} catch (StringIndexOutOfBoundsException e){
			logger.error(e);
		}
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Returns the value of a key.
	 *
	 * @param key the key
	 * @return String
	 */
	public String getKey(String key) {
		if (instance != null)
			for (LocalizerKey lKey : instance.getKeys()) {
				if (lKey.getKeyString().equals(key)) {
					return lKey.getValueString();

				}
			}
		return "N/A";
	}

	/**
	 * Returns the value of a key and replaces arguments like %1, %2 by
	 * the given Object[]. Basically, it just calles the .toString() method
	 * of the objects in Object[].
	 *
	 * @param key the key
	 * @param vars the vars
	 * @return String
	 */
	public String getKey(String key, Object[] vars) {
		String string = getKey(key);
		for (int i = 0; i < vars.length; i++) {
			// logger.debug("replacing %" + i + "% with " + vars[i].toString());
			string = string.replaceAll("%" + i, vars[i].toString());
		}
		return string;
	}

	/**
	 * Returns a Localizer for the given class.
	 *
	 * @param clazz the clazz
	 * @return Localizer
	 */
	public static Localizer getLocalizer(Class clazz) {
		return new Localizer(clazz);
	}
	
	/**
	 * Gets the localizer.
	 *
	 * @param file the file
	 * @return the localizer
	 */
	public static Localizer getLocalizer(File file) {
		return new Localizer(file);
	}

	/**
	 * Gets the keys.
	 *
	 * @return the keys
	 */
	public List<LocalizerKey> getKeys() {
		return keys;
	}

	/**
	 * Sets the keys.
	 *
	 * @param keys the new keys
	 */
	public void setKeys(List<LocalizerKey> keys) {
		this.keys = keys;
	}

	/**
	 * Gets the clazz.
	 *
	 * @return the clazz
	 */
	public Class getClazz() {
		return this.clazz;
	}

	/**
	 * Save a Localizer instance into a xml file.
	 *
	 * @param localizer the localizer
	 * @param file the file
	 */
	public static void writeLocalizedFile(Localizer localizer, File file) {
		logger.debug("Writing localized file to " + file.getAbsolutePath());
		try {
			JAXBContext context = JAXBContext.newInstance(Localizer.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(localizer, new FileWriter(file));
			logger.debug("Localized file has been saved.");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (Exception e) {
			logger.error("Uncorrectly caught exception: ", e);

		}
	}
}
