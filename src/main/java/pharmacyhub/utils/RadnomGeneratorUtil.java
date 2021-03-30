package pharmacyhub.utils;

import java.util.Random;

public class RadnomGeneratorUtil {

	public static String generateActivationCode() {
		return generateRandomString(60);
	}
	
	public static String generateEmployeePassword() {
		return generateRandomString(20);
	}
	
	private static String generateRandomString(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
