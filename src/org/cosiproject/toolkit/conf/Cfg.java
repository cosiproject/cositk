/* Cfg.java is part of CosiTK
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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



// TODO: Auto-generated Javadoc
/**
 * The Class Cfg.
 */
public class Cfg {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(Cfg.class);
	
	/** The child cfg. */
	private Cfg childCfg;
	
	/** The key. */
	private CfgKey key;
	
	/** The value. */
	private CfgValue value;
	
	/**
	 * Instantiates a new cfg.
	 *
	 * @param cfgString the cfg string
	 */
	public Cfg(String cfgString) {
		if(cfgString.length() == 0)
			return;
		String[] s = cfgString.split(";", 2);
		if(s.length >= 1) {
			// create key and value
	 	 	String[] tmp = s[0].split("=", 2);
			key = new CfgKey(tmp[0]);
			value = new CfgValue(tmp[1]);
			
			// do we need a child?
			if(s.length == 2 && s[1].contains("="))
				setChildCfg(new Cfg(s[1]));
			
		}
	}
	
	/**
	 * Gets the child cfg.
	 *
	 * @return the child cfg
	 */
	public Cfg getChildCfg() {
		return childCfg;
	}
	
	/**
	 * Sets the child cfg.
	 *
	 * @param childCfg the new child cfg
	 */
	public void setChildCfg(Cfg childCfg) {
		this.childCfg = childCfg;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public CfgKey getKey() {
		return key;
	}
	
	/**
	 * Gets the value.
	 *
	 * @param keyName the key name
	 * @return the value
	 */
	public CfgValue getValue(String keyName) {
		if(getKey() != null && getKey().getText().equals(keyName)) 
			return getValue();
		if(getChildCfg()!=null)
			return getChildCfg().getValue(keyName);
		return null;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(CfgKey key) {
		this.key = key;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public CfgValue getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(CfgValue value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String retVal = "";
		if(getKey()!=null)
			retVal += getKey()+"="+getValue()+";";
		
		if(getChildCfg() != null)
			retVal += getChildCfg().toString();
		
		return retVal;
	}
	
	/**
	 * Instantiates a new cfg.
	 */
	public Cfg() {
		
	}
	
	/**
	 * Instantiates a new cfg.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public Cfg(String key, String value) {
		this.key = new CfgKey(key);
		this.value = new CfgValue(value);
	}
	
	/**
	 * Instantiates a new cfg.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public Cfg(CfgKey key, CfgValue value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Adds the.
	 *
	 * @param cfg the cfg
	 */
	public void add(Cfg cfg) {
		if(getChildCfg() != null)
			getChildCfg().add(cfg);
		else
			setChildCfg(cfg);
	}
	
	/**
	 * Removes the.
	 *
	 * @param cfg the cfg
	 */
	public void remove(Cfg cfg) {
		// TODO
	}
	
	/**
	 * Adds the.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void add(String key, Object value) {
		// TODO Auto-generated method stub
		add(new Cfg(key, value.toString()));
	}
	
	/**
	 * Adds the.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void add(CfgKey key, CfgValue value) {
		// TODO Auto-generated method stub
		add(new Cfg(key, value));
	}
	
	
	
}
