package utils;

import java.security.SecureRandom;

public class RandomString {

    public static String randomString(int length) throws IllegalArgumentException {
        StringBuilder stringGen;
        SecureRandom random;

        if (length > 0) {
            String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            stringGen = new StringBuilder(length);
            random = new SecureRandom();

            for (int i = 0; i < length; i++) {
                stringGen.append(base.charAt(random.nextInt(base.length())));
            }
        }
        else {
            throw new IllegalArgumentException("Length must be positive greater than 0");
        }

        return stringGen.toString();
    }
}
