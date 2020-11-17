package com.sct.commons.file.location;

import java.util.UUID;

/**
 * @author Pengxuan Men
 */
public class UUIDGenerator {

	public static String get() {
		return random().toString();
	}

	private static UUID random() {
		return UUID.randomUUID();
	}
}
