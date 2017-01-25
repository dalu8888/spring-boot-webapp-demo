package com.digitalchina.common.utils;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

import java.math.BigDecimal;
import java.net.IDN;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	public static boolean decimalRangeValidator(String value, BigDecimal minValue, BigDecimal maxValue,
			boolean inclusive) {
		return decimalMinValidator(value, minValue, inclusive) && decimalMaxValidator(value, maxValue, inclusive);
	}

	public static boolean decimalMaxValidator(String value, BigDecimal maxValue, boolean inclusive) {
		if (value == null) {
			return true;
		}
		try {
			int comparisonResult = new BigDecimal(value).compareTo(maxValue);
			return inclusive ? comparisonResult <= 0 : comparisonResult < 0;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean decimalMinValidator(String value, BigDecimal minValue, boolean inclusive) {
		if (value == null) {
			return true;
		}
		try {
			int comparisonResult = new BigDecimal(value.toString()).compareTo(minValue);
			return inclusive ? comparisonResult >= 0 : comparisonResult > 0;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean digitValidator(String value, int maxIntegerLength, int maxFractionLength) {
		if (value == null) {
			return true;
		}

		BigDecimal bigNum = getBigDecimalValue(value);
		if (bigNum == null) {
			return false;
		}

		int integerPartLength = bigNum.precision() - bigNum.scale();
		int fractionPartLength = bigNum.scale() < 0 ? 0 : bigNum.scale();

		return (maxIntegerLength <= integerPartLength && maxFractionLength <= fractionPartLength);
	}

	public static boolean nullValidator(String value) {
		return value != null;
	}

	/*
	 * 
	 * @param regex The expression to be compiled
	 *
	 * @param flags Match flags, a bit mask that may include {@link
	 * #Pattern.CASE_INSENSITIVE}, {@link #Pattern.MULTILINE}, {@link
	 * #Pattern.DOTALL}, {@link #Pattern.UNICODE_CASE}, {@link
	 * #Pattern.CANON_EQ}, {@link #Pattern.UNIX_LINES}, {@link
	 * #Pattern.LITERAL}, {@link #Pattern.UNICODE_CHARACTER_CLASS} and {@link
	 * #Pattern.COMMENTS}
	 */
	public static boolean patternValidator(String value, String regex, int flags) {
		if (value == null) {
			return true;
		}
		Pattern pattern = Pattern.compile(regex, flags);
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

	public static boolean datePastValidator(Date date) {
		if (date == null) {
			return true;
		}

		return date.getTime() < System.currentTimeMillis();
	}

	public static boolean calendarPastValidator(Calendar cal) {
		if (cal == null) {
			return true;
		}

		return cal.getTimeInMillis() < System.currentTimeMillis();
	}

	public static boolean datePastValidator(String dateString, String format) {
		if (dateString == null) {
			throw new IllegalArgumentException("Date is not provided.");
		}
		String defaultDateFormat = "yyyy-MM-dd";
		if (format == null) {
			format = defaultDateFormat;
		}
		try {
			Date date = new SimpleDateFormat(format).parse(dateString);
			Date now = new SimpleDateFormat(defaultDateFormat)
					.parse(new SimpleDateFormat(defaultDateFormat).format(new Date()));
			return now.compareTo(date) > 0;
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date format is not correct.");
		}
	}

	public static boolean emailValidator(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}

		// split email at '@' and consider local and domain part separately;
		// note a split limit of 3 is used as it causes all characters following
		// to an (illegal) second @ character to
		// be put into a separate array element, avoiding the regex application
		// in this case since the resulting array
		// has more than 2 elements
		String[] emailParts = value.toString().split("@", 3);
		if (emailParts.length != 2) {
			return false;
		}

		// if we have a trailing dot in local or domain part we have an invalid
		// email address.
		// the regular expression match would take care of this, but IDN.toASCII
		// drops the trailing '.'
		// (imo a bug in the implementation)
		if (emailParts[0].endsWith(".") || emailParts[1].endsWith(".")) {
			return false;
		}

		String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
		String DOMAIN = ATOM + "+(\\." + ATOM + "+)*";
		String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
		int MAX_LOCAL_PART_LENGTH = 64;
		int MAX_DOMAIN_PART_LENGTH = 255;
		Pattern localPattern = Pattern.compile(ATOM + "+(\\." + ATOM + "+)*", CASE_INSENSITIVE);
		Pattern domainPattern = Pattern.compile(DOMAIN + "|" + IP_DOMAIN, CASE_INSENSITIVE);

		if (!matchPart(emailParts[0], localPattern, MAX_LOCAL_PART_LENGTH)) {
			return false;
		}

		return matchPart(emailParts[1], domainPattern, MAX_DOMAIN_PART_LENGTH);
	}

	public static boolean lengthValidator(String value, int min, int max) {
		if (value == null) {
			return true;
		}
		int length = value.length();
		return length >= min && length <= max;
	}

	public static boolean notBlankValidator(String value) {
		if (value == null) {
			return true;
		}

		return value.trim().length() > 0;
	}

	/*
	 * Protocol, host和port的精确验证
	 */
	public static boolean urlValidator(String value, String protocol, String host, int port) {
		if (value == null || value.length() == 0) {
			return true;
		}

		java.net.URL url;
		try {
			url = new java.net.URL(value);
		} catch (MalformedURLException e) {
			return false;
		}

		if (protocol != null && protocol.length() > 0 && !url.getProtocol().equals(protocol)) {
			return false;
		}

		if (host != null && host.length() > 0 && !url.getHost().equals(host)) {
			return false;
		}

		if (port != -1 && url.getPort() != port) {
			return false;
		}

		return true;
	}

	private static boolean matchPart(String part, Pattern pattern, int maxLength) {
		String asciiString;
		try {
			asciiString = toAscii(part);
		} catch (IllegalArgumentException e) {
			return false;
		}

		if (asciiString.length() > maxLength) {
			return false;
		}

		Matcher matcher = pattern.matcher(asciiString);
		return matcher.matches();
	}

	private static String toAscii(String unicodeString) throws IllegalArgumentException {
		String asciiString = "";
		int start = 0;
		int end = unicodeString.length() <= 63 ? unicodeString.length() : 63;
		while (true) {
			// IDN.toASCII only supports a max "label" length of 63 characters.
			// Need to chunk the input in these sizes
			asciiString += IDN.toASCII(unicodeString.substring(start, end));
			if (end == unicodeString.length()) {
				break;
			}
			start = end;
			end = start + 63 > unicodeString.length() ? unicodeString.length() : start + 63;
		}

		return asciiString;
	}

	private static BigDecimal getBigDecimalValue(String value) {
		BigDecimal bd;
		try {
			bd = new BigDecimal(value);
		} catch (NumberFormatException nfe) {
			return null;
		}
		return bd;
	}
}
