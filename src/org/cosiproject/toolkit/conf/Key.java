/* Key.java is part of CosiTK
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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.BindingType;


// TODO: Auto-generated Javadoc
/**
 * The Class Key.
 */
public class Key extends SuperConf {
	
	/** The name. */
	private String name;
	
	/** just a single value */
	private String value;
	
	/** The list value. */
	private List<Value> listValue;
	
	/** The list keys. */
	private List<Key> listKeys;
	
	/**
	 * Instantiates a new key.
	 *
	 * @param string the string
	 */
	public Key(String string) {
		this.name = string;
	}
	
	/**
	 * Instantiates a new key.
	 */
	public Key() {
		
	}
	
	public Key(String key, String value) {
		this.name = key;
		this.value = value;
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
	 * Gets the list values.
	 *
	 * @return the list values
	 */
	@XmlElement(name="value")
	public List<Value> getListValues() {
		return listValue;
	}
	
	/**
	 * Sets the list value.
	 *
	 * @param listValue the new list value
	 */
	public void setListValue(List<Value> listValue) {
		this.listValue = listValue;
	}
	
	/**
	 * Gets the list keys.
	 *
	 * @return the list keys
	 */
	@XmlElement(name="key")
	public List<Key> getListKeys() {
		if(listKeys == null)
			listKeys = new ArrayList<Key>();
		return listKeys;
	}
	
	/**
	 * Sets the list keys.
	 *
	 * @param listKey the new list keys
	 */
	public void setListKeys(List<Key> listKey) {
		this.listKeys = listKey;
	}
	
	/**
	 * Gets the key.
	 *
	 * @param key the key
	 * @return the key
	 */
	public Key getKey(String key) {
		for(Key sKey : listKeys) {
			if(sKey.getName().equals(key)) {
				return sKey;
			}
		}
		return null;
	}
	
	/**
	 * Gets the values.
	 *
	 * @param string the string
	 * @return the values
	 */
	public List<Value> getValues(String string) {
		// TODO Auto-generated method stub
		ArrayList<Value> retVal = new ArrayList<Value>();
		for(Value value : listValue) {
			if(string != null && string.equals(value.getName())) {
				retVal.add(value);
			}
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.cosiproject.toolkit.conf.SuperConf#toString()
	 */
	public String toString() {
		return getName()+ "("+super.toString()+")";
	}

	public void setKey(String key, String value) {
		Key k = new Key(key, value);
		getListKeys().add(k);
	}

	@XmlAttribute(name="value")
	public String getValue() {
		if(getListValues() == null)
			return value;
		return getListValues().get(0).getName();
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
	
}
