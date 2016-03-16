package com.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

public class DefaultPasswordEncoder {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static final int HEX_RIGHT_SHIFT_COEFFICIENT = 4;
	private static final int HEX_HIGH_BITS_BITWISE_FLAG = 0x0f;


	private final static String encodingAlgorithm="MD5";

	private static String characterEncoding="UTF-8";


	public static String encode(final String password) {
		if (password == null) {
			return null;
		}

		try {
			final MessageDigest messageDigest = MessageDigest
					.getInstance(encodingAlgorithm);

			if (StringUtils.hasText(characterEncoding)) {
				messageDigest.update(password.getBytes(characterEncoding));
			} else {
				messageDigest
						.update(password.getBytes(Charset.defaultCharset()));
			}

			final byte[] digest = messageDigest.digest();

			return getFormattedText(digest);
		} catch (final NoSuchAlgorithmException e) {
			throw new SecurityException(e);
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 *
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(final byte[] bytes) {
		final StringBuilder buf = new StringBuilder(bytes.length * 2);

		for (int j = 0; j < bytes.length; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> HEX_RIGHT_SHIFT_COEFFICIENT)
					& HEX_HIGH_BITS_BITWISE_FLAG]);
			buf.append(HEX_DIGITS[bytes[j] & HEX_HIGH_BITS_BITWISE_FLAG]);
		}
		return buf.toString();
	}

	public void setCharacterEncoding(final String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}
}
