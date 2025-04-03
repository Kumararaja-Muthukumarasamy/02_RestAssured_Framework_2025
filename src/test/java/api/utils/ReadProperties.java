package api.utils;

import java.util.ResourceBundle;

public class ReadProperties {

	public static ResourceBundle getProperties() {
		
		ResourceBundle prop = ResourceBundle.getBundle("config");
		return prop;
	}
}
