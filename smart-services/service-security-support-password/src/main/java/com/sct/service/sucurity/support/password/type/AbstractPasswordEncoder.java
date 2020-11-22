/*
 * 
 */

package com.sct.service.sucurity.support.password.type;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Abstract base class for password encoders.
 *
 * @author 
 * @since 1.0.0
 */
public abstract class AbstractPasswordEncoder implements PasswordEncoder {

	private final BytesKeyGenerator saltGenerator;

	protected AbstractPasswordEncoder(int saltLength) {
		this.saltGenerator = KeyGenerators.secureRandom(saltLength); // bytes
	}

	protected int getSaltLength() {
		return this.saltGenerator.getKeyLength();
	}

	protected byte[] generateSalt() {
		return this.saltGenerator.generateKey();
	}

	public static byte[] concatenate(byte[]... arrays) {
		int length = 0;
		for (byte[] array : arrays) {
			length += array.length;
		}
		byte[] newArray = new byte[length];
		int destPos = 0;
		for (byte[] array : arrays) {
			System.arraycopy(array, 0, newArray, destPos, array.length);
			destPos += array.length;
		}
		return newArray;
	}

	public static boolean matches(byte[] expected, byte[] actual) {
		if (expected.length != actual.length) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < expected.length; i++) {
			result |= expected[i] ^ actual[i];
		}
		return result == 0;
	}
}
