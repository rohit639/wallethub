package com.wallethub.web.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.wallethub.web.core.BaseWebdriver;
import com.wallethub.web.core.Configuration;

/**
 * <p>
 * Common helper class for all packages and classes
 * </p>
 * <p>
 * Such as:- TestNG, WebDriver and etc
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class CommonUtil {

	public static String mapToString(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			sb.append(entry.getKey());
			sb.append('=').append('"');
			sb.append(entry.getValue());
			sb.append('"');
			sb.append("\n");
		}
		return sb.toString();

	}

	/**
	 * <p>
	 * Highlight and pause for the seconds provided in configuration
	 * </p>
	 *
	 * @param webElement element which needs to be highlighted
	 */
	public static void slowHighlightElement(WebElement webElement) {
		highLightElmnt(webElement);
		try {
			Thread.sleep(Configuration.getTimetoslow() * 1000);
		} catch (Exception e) {

		}
	}

	/**
	 * <p>
	 * Highlight the webelement
	 * </p>
	 *
	 * @param webElement element which needs to be highlighted
	 */
	public static void highLightElmnt(WebElement webElement) {
		for (int i = 0; i < 5; i++) {

			JavascriptExecutor js = (JavascriptExecutor) BaseWebdriver.getDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement,
					"color: yellow; border: 3px solid red;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement,
					"color: pink; border: 4px solid pink;");
			try {
				Thread.sleep(7);
			} catch (Exception e) {
				// TODO: handle exception
			}
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
		}
	}

	/**
	 * <p>
	 * Convert RGB color to hexadecimal string, which is compute output of CSS style
	 * in Web page. It will be always small. For an example #f18221 or #6930ca
	 * </p>
	 *
	 * @param rgbCode string input for rgb from web page. which is like rgb(241,
	 *                130, 33)
	 * @return hexadecimal, string equivalent to rgb code provided
	 */
	public static String rgbToHexString(String rgbCode) {
		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher(rgbCode);
		List<String> rgbFormat = new LinkedList<String>();
		while (m.find()) {
			rgbFormat.add(m.group());
		}
		return String.format("#%02x%02x%02x", Integer.parseInt(rgbFormat.get(0)), Integer.parseInt(rgbFormat.get(1)),
				Integer.parseInt(rgbFormat.get(2)));
	}
}
