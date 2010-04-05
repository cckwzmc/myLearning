package com.myfetch.service.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamUtils {
	/**
	 * Reads an inputstream and converts it to a string. Note that this is rather memory intensive, if you do not need random access in the inputstream you should iterate sequentially over the lines using readLine()
	 * 
	 * @param is
	 *            the inputstream to convert
	 * @param charset 
	 * @return the content of the input stream
	 * @throws IOException
	 *             if reading the inputstream fails
	 */
	public static String readInputStream(InputStream is, String charset) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is, charset));
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		is.close();
		return buffer.toString();
	}
}
