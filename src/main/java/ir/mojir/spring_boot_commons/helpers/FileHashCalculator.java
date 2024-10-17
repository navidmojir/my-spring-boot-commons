package ir.mojir.spring_boot_commons.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ir.mojir.spring_boot_commons.exceptions.InternalErrorException;

public class FileHashCalculator {
	public static String calculate(byte[] file) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(file);
		} catch (NoSuchAlgorithmException e) {
			throw new InternalErrorException("NoSuchAlgorithmException occured for MD5", e);
		}
		BigInteger bi = new BigInteger(1, hash);
		return String.format("%0" + (hash.length << 1) + "X", bi);
	}
}
