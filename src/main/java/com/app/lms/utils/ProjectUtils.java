package com.app.lms.utils;

import java.util.Random;

public class ProjectUtils {

	public static String generatePassword() {
		Random random = new Random();
		String sixDigitNumber = String.valueOf(100000 + random.nextInt(900000));
		return sixDigitNumber;
	}
}
