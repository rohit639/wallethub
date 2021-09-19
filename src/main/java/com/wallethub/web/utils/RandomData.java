package com.wallethub.web.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.javafaker.Faker;

/**
 * <p>
 * Helps in providing all random data required, Ex:- Mobile, Name, Password
 * </p>
 * 
 * @author Rohit P Kumar
 *
 */
public class RandomData {

	/**
	 * <p>
	 * Provide random password
	 * </p>
	 * 
	 * @return password
	 */
	public static String randompassword() {
		Faker faker = new Faker();
		return faker.internet().password();
	}

	/**
	 * <p>
	 * Provide random Number
	 * </p>
	 * 
	 * @return number
	 */
	public static int randomNumber() {
		Faker faker = new Faker();
		return faker.number().randomDigit();
	}

	/**
	 * <p>
	 * Provide random number
	 * </p>
	 * 
	 * @param minLength the minimum number of character required
	 * @param maxLength the Maximum number of character required
	 *
	 * @return number
	 */
	public static int randomNumber(int minLength, int maxLength) {
		Faker faker = new Faker();
		return faker.number().numberBetween(minLength, maxLength);
	}

	/**
	 * <p>
	 * Provide random password
	 * </p>
	 * 
	 * @param minLength the minimum number of character required
	 * @param maxLength the Maximum number of character required
	 * 
	 * @return password
	 */
	public static String randompassword(int minLength, int maxLength) {
		Faker faker = new Faker();
		return faker.internet().password(minLength, maxLength);
	}

	/**
	 * <p>
	 * Provide random alphanumeric
	 * </p>
	 * 
	 * @param size the Maximum number of character required
	 * @return alphanumeric string
	 */
	public static String randomString(int size) {
		Faker faker = new Faker();
		return faker.regexify("[A-Za-z0-9]{" + size + '}');
	}

	/**
	 * <p>
	 * Provide random Name
	 * </p>
	 * 
	 * 
	 * @return Name as string
	 */
	public static String randomName() {
		Faker faker = new Faker();
		return faker.name().firstName();
	}

	/**
	 * <p>
	 * Provide random Mobile Number starts with 98
	 * </p>
	 * 
	 * 
	 * @return Mobile number as string
	 */
	public static String randomMobile() {
		Faker faker = new Faker();
		return faker.numerify("98########");
	}

	/**
	 * <p>
	 * Provide random email address
	 * </p>
	 * 
	 * 
	 * @return email address as string
	 */
	public static String randomEmail() {
		Faker faker = new Faker();
		return faker.internet().emailAddress();
	}

	/**
	 * <p>
	 * Provide current date and time based on the format desired.
	 * </p>
	 * 
	 * @param format The format date and time is required.
	 * @return date in a string format
	 */
	public static String getToDayDate(String format) {
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		String todayDate = dtf.format(now);
		return todayDate;
	}

	public static String randomAccount() {
		Faker faker = new Faker();
		return faker.regexify("[0-9]{12}");
	}

	public static String randomCardNumber() {
		Faker faker = new Faker();
		return faker.regexify("4[0-9]{15}");
	}

	public static String randomVPA() {
		return randomName() + "@fakevpa";
	}

}
