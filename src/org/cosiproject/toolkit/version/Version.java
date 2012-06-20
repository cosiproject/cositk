/* Version.java is part of CosiTK
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
package org.cosiproject.toolkit.version;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Version.
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Version {

	/** The logger. */
	private static Logger logger = Logger.getLogger(Version.class);
	
	/** The micro. */
	@XmlAttribute
	private int micro;
	
	/** The minor. */
	@XmlAttribute
	private int minor;
	
	/** The major. */
	@XmlAttribute
	private int major;
	
	/** The append text. */
	@XmlAttribute
	private String appendText;

	@XmlValue
	private String version;
	
	/**
	 * Gets the append text.
	 *
	 * @return the append text
	 */
	public String getAppendText() {
		return appendText;
	}

	/**
	 * Sets the append text.
	 *
	 * @param appendText the new append text
	 */
	public void setAppendText(String appendText) {
		this.appendText = appendText;
	}

	/**
	 * Set the Version as String.
	 * For Example:
	 * new Version("1.2") or ("1.2.4") or ("1.4.2-final")
	 * The string behind "-" will be appended to the toString() output.
	 *
	 * @param version the version
	 */
	public Version(String version) {
		if(!version.contains("."))
			throw new IllegalArgumentException("Version has to include at least a minor number.\n" +
					"Examples:\n" +
					"\t\t1.0\n" + 
					"\t\t1.0.1\n" + 
					"\t\t1.0-final\n" + 
					"\t\t1.0.1-betaFoo\n");
		
		this.version = version;
		parse(version);
		
		
	}
	
	private void parse(String line) {
		// Check, if version string has appendText.
				if(version.contains("-")) {
					String[] spl1 = version.split("-");
					setAppendText(spl1[1]);
					line = spl1[0];
				} 
				
				String[] spl = line.split("\\.");
				try {
					if(spl.length == 3) {
						this.major = Integer.parseInt(spl[0]);
						this.minor = Integer.parseInt(spl[1]);
						this.micro = Integer.parseInt(spl[2]);	
					}
					else if(spl.length == 2) {
						this.major = Integer.parseInt(spl[0]);
						this.minor = Integer.parseInt(spl[1]);	
					}
					else {
						this.major = Integer.parseInt(spl[0]);
					}	
				} catch(Exception e) {
					logger.error(e);
				}
	}
	/**
	 * Instantiates a new version.
	 */
	public Version() {
		
	}
	
	/**
	 * Instantiates a new version.
	 *
	 * @param version the version
	 * @param appendText the append text
	 */
	public Version(String version, String appendText) {
		this(version);
		setAppendText(appendText);
	}
	
	/**
	 * Instantiates a new version.
	 *
	 * @param major the major
	 * @param minor the minor
	 * @param micro the micro
	 */
	public Version(int major, int minor, int micro) {
		this.major = major;
		this.minor = minor;
		this.micro = micro;
	}
	
	/**
	 * Gets the micro.
	 *
	 * @return the micro
	 */
	public int getMicro() {
		return micro;
	}

	/**
	 * Sets the micro.
	 *
	 * @param micro the new micro
	 */
	public void setMicro(int micro) {
		this.micro = micro;
	}

	/**
	 * Gets the minor.
	 *
	 * @return the minor
	 */
	public int getMinor() {
		return minor;
	}

	/**
	 * Sets the minor.
	 *
	 * @param minor the new minor
	 */
	public void setMinor(int minor) {
		this.minor = minor;
	}

	/**
	 * Gets the major.
	 *
	 * @return the major
	 */
	public int getMajor() {
		return major;
	}

	/**
	 * Sets the major.
	 *
	 * @param major the new major
	 */
	public void setMajor(int major) {
		this.major = major;
	}

	/**
	 * Sets the version.
	 *
	 * @param major the major
	 * @param minor the minor
	 * @param micro the micro
	 */
	public void setVersion(int major, int minor, int micro) {
		this.major = major;
		this.minor = minor;
		this.micro = micro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retVal;
		if(micro != 0)
			retVal = major + "." + minor + "." + micro;
		else
			retVal = major + "." + minor;
		
		if(getAppendText() != null) 
			retVal += "-" + getAppendText();
		return retVal;
	}

	/**
	 * Returns true if this version is lower than the given one.
	 *
	 * @param version the version
	 * @return true, if is lower
	 */
	public boolean isLower(Version version) {

		if (getMajor() < version.getMajor())
			return true;

		if (getMajor() == version.getMajor() && getMinor() < version.getMinor())
			return true;

		if (getMajor() == version.getMajor()
				&& getMinor() == version.getMinor()
				&& getMicro() < version.getMicro())
			return true;

		return false;
	}

	
	
	/**
	 * Returns true if this version is higher than the given one.
	 *
	 * @param version the version
	 * @return true, if is higher
	 */
	public boolean isHigher(Version version) {
		
		if (getMajor() > version.getMajor())
			return true;
		
		if (getMajor() == version.getMajor() && getMinor() > version.getMinor())
			return true;

		if (getMajor() == version.getMajor()
				&& getMinor() == version.getMinor()
				&& getMicro() > version.getMicro())
			return true;

		return false;
	}
	
	/**
	 * Equals.
	 *
	 * @param version the version
	 * @return true, if successful
	 */
	public boolean equals(Version version) {
		if (getMajor() == version.getMajor()
				&& getMinor() == version.getMinor()
				&& getMicro() == version.getMicro())
			return true;
		return false;
	}

	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
	
}
