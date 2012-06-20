package org.cosiproject.toolkit.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class XMLTool {

	private static Logger logger = Logger.getLogger(XMLTool.class);

	/**
	 * Load from file.
	 * 
	 * @param file
	 *            the file
	 * @return the conf
	 */
	public static Object loadFromFile(Class clazz, File file) {
		Object retVal = null;
		JAXBContext context;

		try {
			context = JAXBContext.newInstance(clazz);
			Unmarshaller um = context.createUnmarshaller();
			retVal = um.unmarshal(new FileReader(file));
			
			return retVal;
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e, e);
		}
		return retVal;

	}

	/**
	 * Save to file.
	 * 
	 * @param <T>
	 * 
	 * @param object
	 *            the conf
	 * @param file
	 *            the file
	 */
	public static void saveToFile(Class clazz, Object object, File file) {
		logger.debug("Creating xml for " + clazz.getCanonicalName());

		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(object, new FileWriter(file));
			logger.debug("xml written to " + file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			logger.error(e, e);
		} catch (Exception e) {
			logger.error(e, e);

		}
	}

	public static Object loadFromFile(Class clazz, String string) {
		return loadFromFile(clazz, new File(string));
	}

}
