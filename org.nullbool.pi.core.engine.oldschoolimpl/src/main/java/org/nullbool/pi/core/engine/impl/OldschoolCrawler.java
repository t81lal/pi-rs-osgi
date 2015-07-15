package org.nullbool.pi.core.engine.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nullbool.pi.core.engine.api.IPageCrawler;

public class OldschoolCrawler implements IPageCrawler {
	private static final String USER_AGENT = getHttpUserAgent();
	private static final String PARAM_PATTERN = "<param name=\"?([^\\s]+)\"?\\s+value=\"?([^>]*)\"?>";
	private static final String HAS_IE6 = "haveie6";
	private static final char[] DOCUMENT_PUT = "document.write('<app".toCharArray();

	private final Map<String, String> parameterMap;
	private final Map<String, String> appletMap;
	
	public OldschoolCrawler(URL pageURL) throws IOException {
		parameterMap = new HashMap<String, String>();
		appletMap = new HashMap<String, String>();
		
		String data = downloadPage(pageURL, null);
		parseGenericApplet(data);
		parseDocument(data);
	}

	private void parseGenericApplet(String data) throws IOException {
		Pattern pattern = Pattern.compile(PARAM_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			String key = removeTrailingChar(matcher.group(1), '"');
			String value = removeTrailingChar(matcher.group(2), '"');
			if (!parameterMap.containsKey(key)) {
				parameterMap.put(key, value);
			}
		}

		if (parameterMap.containsKey(HAS_IE6)) {
			parameterMap.remove(HAS_IE6);
		}
		parameterMap.put(HAS_IE6, "0");
	}

	private void parseDocument(String data) {
		char[] chars = data.toCharArray();
		boolean reading = false;
		for(int i=0; i < chars.length; i++) {
			if(reading) {
				char c = chars[i];
				if(c == '=') {
					int s = dist(chars, i, false, '\'');
					int e = dist(chars, i, true, '\'');
					int s2 = i + s;
					int e2 = i + e;
					String str = data.substring(s2 + 1, e2).trim();
					String[] parts = str.split("=");
					if(parts.length != 2) {
						System.err.println(String.format("Malformed document data (i=%d, s=%d, e=%d, s2=%d, e2=%d, str=%s).", i, s, e, s2, e2, str));
					} else {
						appletMap.put(parts[0], parts[1]);
					}
					i = e2 + 1;
				} else if(c == '>') {
					reading = false;
					return;
				}
			} else {
				reading = next(chars, i, DOCUMENT_PUT);
			}
		}
	}

	private static boolean next(char[] chars, int offset, char[] pattern) {
		if((offset + pattern.length) >= chars.length)
			return false;
		for(int i=offset; i < (offset + pattern.length); i++) {
			char c = chars[i];
			char p = pattern[i - offset];
			if(c != p)
				return false;
		}
		return true;
	}

	private static int dist(char[] chars, int offset, boolean forward, char t) {
		if(forward) {
			for(int i=offset; i < chars.length; i++) {
				char c = chars[i];
				if(c == t)
					return i - offset;
			}
		} else {
			for(int i=offset; i >= 0; i--) {
				char c = chars[i];
				if(c == t)
					return i - offset;
			}
		}
		return -1;
	}

	private static String downloadPage(URL pageURL, String referer) throws IOException {
		HttpURLConnection con = getHttpConnection(pageURL);
		if (referer != null && !referer.isEmpty())
			con.addRequestProperty("Referer", referer);
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder buf = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			buf.append(line);
		}
		reader.close();
		return buf.toString();
	}

	private static String getHttpUserAgent() {
		if (USER_AGENT != null)
			return USER_AGENT;
		String os = "Windows NT 6.1";
		String osn = System.getProperty("os.name");
		if (osn.contains("Mac")) {
			os = "Macintosh; Intel Mac OS X 10_6_6";
		} else if (os.contains("Linux")) {
			os = "X11; Linux x86_64";
		}
		StringBuilder buf = new StringBuilder(125);
		buf.append("Mozilla/5.0 (").append(os).append(")");
		buf.append(" AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.60 Safari/534.24");
		return buf.toString();
	}

	private static HttpURLConnection getHttpConnection(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.addRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		con.addRequestProperty("Accept-Encoding", "gzip,deflate");
		con.addRequestProperty("Accept-Language", "en-us,en;q=0.5");
		con.addRequestProperty("Host", url.getHost());
		con.addRequestProperty("User-Agent", getHttpUserAgent());
		con.setConnectTimeout(10000);
		return con;
	}

	private static String removeTrailingChar(String str, char ch) {
		if ((str == null) || str.isEmpty()) {
			return str;
		} else if (str.length() == 1) {
			return str.charAt(0) == ch ? "" : str;
		}
		try {
			int l = str.length() - 1;
			if (str.charAt(l) == ch) {
				return str.substring(0, l);
			}
			return str;
		} catch (Exception e) {
			return str;
		}
	}

	@Override
	public Map<String, String> getGameParameters() {
		return parameterMap;
	}

	@Override
	public Map<String, String> getAppletParameters() {
		return appletMap;
	}
}