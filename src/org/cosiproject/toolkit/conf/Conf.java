/* Conf.java is part of CosiTK
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
package org.cosiproject.toolkit.conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Conf.
 */
@XmlRootElement(namespace = "http://cosi.lother.net", name="conf")
@XmlAccessorType(XmlAccessType.NONE)
public class Conf extends SuperConf {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(Conf.class);
	
	/** The whitespace. */
	private static String whitespace = "";
	
	/** The space. */
	private static String space = "     ";
	
	/** The name. */
	private String name;
	
	/** The list key. */
	private ArrayList<Key> listKey;
	
	/** The list conf. */
	private ArrayList<Conf> listConf;
	
	/**
	 * Instantiates a new conf.
	 *
	 * @param string the string
	 */
	public Conf(String string) {
		// TODO Auto-generated constructor stub
		this.name = string;
	}
	
	/**
	 * Instantiates a new conf.
	 */
	public Conf() {
		
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@XmlAttribute(name="name", required=true)
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the list keys.
	 *
	 * @return the list keys
	 */
	@XmlElement(name="key")
	public ArrayList<Key> getListKeys() {
		if(listKey == null)
			listKey = new ArrayList<Key>();
		return listKey;
	}
	
	/**
	 * Sets the list keys.
	 *
	 * @param listKey the new list keys
	 */
	public void setListKeys(ArrayList<Key> listKey) {
		this.listKey = listKey;
	}
	
	/**
	 * Gets the list conf.
	 *
	 * @return the list conf
	 */
	@XmlElement(name="conf")
	public List<Conf> getListConf() {
		if(listConf == null)
			listConf = new ArrayList<Conf>();
		return listConf;
	}
	
	/**
	 * Sets the list conf.
	 *
	 * @param listConf the new list conf
	 */
	public void setListConf(ArrayList<Conf> listConf) {
		this.listConf = listConf;
	}
	
	/**
	 * Gets the key.
	 *
	 * @param key the key
	 * @return the key
	 */
	public Key getKey(String key) {
		for(Key sKey : listKey) {
			if(sKey.getName().equals(key)) {
				return sKey;
			}
		}
		return null;
	}
	
	/**
	 * Load from file.
	 *
	 * @param file the file
	 * @return the conf
	 */
	public static Conf loadFromFile(File file)  {
		Conf retVal = null;
		JAXBContext context;
		
		
				try {
					context = JAXBContext.newInstance(Conf.class);
					Unmarshaller um = context.createUnmarshaller();
					retVal = (Conf) um.unmarshal(new FileReader(file));
					return retVal;
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					logger.error(e, e);
				}
				return retVal;
			
	}
	
	/**
	 * Save to file.
	 *
	 * @param conf the conf
	 * @param file the file
	 */
	public static void saveToFile(Conf conf, File file) {
		logger.debug("Creating xml for " + conf);
		
		try {
			JAXBContext context = JAXBContext.newInstance(Conf.class);
			Marshaller m = context.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
			m.marshal(conf, new FileWriter(file));
			logger.debug("xml written to " + file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);

		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Conf conf = new Conf("kloud");
		Key key = new Key("syncDriver");
		key.getListValues().add(new Value("org.hiddenbox.kloud.core.sync.svn.SVNSyncDriver"));
		conf.getListKeys().add(key);
		key = new Key("settings");
		conf.getListKeys().add(key);
		Key folderSettings = new Key("f&older");
		conf.setCfg(new Cfg("foobfsdsüar=foo"));
		key.getListKeys().add(folderSettings);
		Value value = new Value("path", System.getProperty("user.home"));
		value.getOptions().add("foobar 1 ");
		value.getOptions().add("foobar 2");
		value.getOptions().add("foobar 2fsdfsd dfs dfsdaf sdfss");
		folderSettings.getListValues().add(value);
		folderSettings.getListValues().add(value);
		folderSettings.getListValues().add(value);
		folderSettings.setCfg(new Cfg("foo=bar;blubb=b00b00"));
		
//		Conf foof = (Conf) ObjectTool.clone(conf);
//		try {
//		conf.getListConf().add(loadFromFile(new File("../Kloud/kloudConf.xml")));
//		Conf foof = loadFromFile(new File("../Kloud/kloudConf.xml"));
//		Conf fooof = loadFromFile(new File("../Kloud/kloudConf.xml"));
//		Conf fo = loadFromFile(new File("../Kloud/kloudConf.xml"));
//		Conf f = loadFromFile(new File("../Kloud/kloudConf.xml"));
//		foof.getListConf().add(fooof);
//		fooof.getListConf().add(fo);
//		fo.getListConf().add(f);
//		conf.getListConf().add(foof);
//		} catch (Exception e) {
//			// foo
//		}
		
		//saveToFile(conf, new File("../Kloud/kloudConf.xml"));
		conf.debug();
//		Conf cfg = loadFromFile(new File("../Kloud/kloudConf.xml"));
		
		
		
	}
	
	/**
	 * Debug.
	 */
	public void debug() {
		// TODO Auto-generated method stub
		
		String ws = getWS();
		logger.debug(ws +"@"+ this.getName() +" &(" +this.getCfg()+") {");
		for(Key key : getListKeys()) {
			debugKey(getListKeys());
		}
		for(Conf cfg : getListConf()) {
			cfg.debug();
		}
		logger.debug(ws+"}");
		clearWS();
	}
	
	/**
	 * Debug key.
	 *
	 * @param key the key
	 */
	private void debugKey(List<Key> key) {
		String ws = getWS();
		for(Key k : key) {
			logger.debug(ws+"%"+k.getName()+" &("+k.getCfg()+") {");
			if(k.getListValues().size() > 0) {
				debugValue(k.getListValues());
			}
			if(k.getListKeys().size() > 0) {
				debugKey(k.getListKeys());
			}
			logger.debug(ws+"}");
			
		}
		clearWS();
	}
	
	/**
	 * Debug value.
	 *
	 * @param value the value
	 */
	private void debugValue(List<Value> value) {
		String ws = getWS();
		for(Value v : value) {
			logger.debug(ws+"*"+v.getName()+" &("+v.getCfg()+") {");
			for(String s : v.getOptions())
				logger.debug(ws+"\t+\""+s+"\"");
			logger.debug(ws+"\t value = " + v.getValue());
			
					
			logger.debug(ws+"}");
		}
		
		clearWS();
	}
	
	/**
	 * Clear ws.
	 */
	private static void clearWS() {
		// TODO Auto-generated method stub
		
		if(whitespace.length() > space.length());
		whitespace = whitespace.substring(0, whitespace.length() - space.length());
		
	}
	
	/**
	 * Gets the wS.
	 *
	 * @return the wS
	 */
	private static String getWS() {
		
		String retVal = new String(whitespace);
		whitespace += space;
		
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.cosiproject.toolkit.conf.SuperConf#toString()
	 */
	public String toString() {
		return getName()+ "("+super.toString()+")";
	}

	public void addKey(Key key) {
		getListKeys().add(key);
	}

	public void addConf(Conf conf) {
		getListConf().add(conf);
	}

	
}
