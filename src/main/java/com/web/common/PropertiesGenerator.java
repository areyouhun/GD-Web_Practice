package com.web.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesGenerator {

	public static Properties by(String path) {
		Properties prop = new Properties();
		try (FileReader fileReader = new FileReader(path)) {
			prop.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
