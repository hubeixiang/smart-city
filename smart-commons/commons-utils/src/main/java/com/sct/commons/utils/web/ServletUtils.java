package com.sct.commons.utils.web;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServletUtils {

	public static StringBuffer getBaseURL(HttpServletRequest request) {

		StringBuffer url = new StringBuffer();
		String scheme = request.getScheme();
		int port = request.getServerPort();
		if (port < 0)
			port = 80; // Work around java.net.URL bug

		url.append(scheme);
		url.append("://");
		url.append(request.getServerName());
		if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
			url.append(':');
			url.append(port);
		}
		url.append(request.getContextPath());

		return (url);
	}

	public static String buildURL(HttpServletRequest request, String requestPath) {
		StringBuffer url = getBaseURL(request);
		url.append(requestPath);
		return url.toString();
	}

	public static String getRealIp(HttpServletRequest request) {
		String address = StringUtils.trimToNull(request.getHeader("X-Real-IP"));
		if (address == null) {
			address = StringUtils.trimToNull(request.getHeader("X-Forwarded-For"));
			if (address != null && address.contains(",")) {
				address = StringUtils.trimToNull(StringUtils.split(address, ",")[0]);
			}
			if (address != null && address.contains(":")) {
				address = address.replaceAll(":\\d+$", StringUtils.EMPTY);
			}
		}
		if (address == null) {
			address = StringUtils.trimToNull(request.getRemoteHost());
		}
		return address;
	}

	/**
	 * Check client is likely using a proxy.
	 */
	public static boolean isProxied(HttpServletRequest request) {
		String header = request.getHeader("X-Forwarded-For");
		return StringUtils.isNotBlank(header);
	}

	public static String replaceHost(String url, HttpServletRequest request) {
		String host = request.getHeader("host");
		return url.replaceAll("(https?://[^/]+/)", "http://" + host + "/");
	}

	public static String parameterMapToString(HttpServletRequest request) {
		Map<String, String> stringMap = new HashMap<>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (String key : parameterMap.keySet()) {
			String[] value = parameterMap.get(key);
			stringMap.put(key, value != null && value.length == 1 ? value[0] : Arrays.toString(value));
		}
		return stringMap.toString();
	}
}
