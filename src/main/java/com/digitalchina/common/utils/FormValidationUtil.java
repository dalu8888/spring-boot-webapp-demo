package com.digitalchina.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FormValidationUtil {

	public static void main(String[] args) {
		List<ErrorMessage> errors = createEmptyErrors();
		decimalRangeValidator("12", BigDecimal.valueOf(1.0), BigDecimal.valueOf(100.0), true, "AAAA", errors);
		decimalRangeValidator("100", BigDecimal.valueOf(1.0), BigDecimal.valueOf(100.0), true, "BBBB", errors);
		decimalRangeValidator("100", BigDecimal.valueOf(1.0), BigDecimal.valueOf(100.0), false, "CCCC", errors);
		decimalRangeValidator("-1", BigDecimal.valueOf(1.0), BigDecimal.valueOf(100.0), true, "DDDD", errors);
		System.out.println(errors);
	}

	public static List<ErrorMessage> createEmptyErrors() {
		return new ArrayList<ErrorMessage>();
	}

	public static void decimalRangeValidator(String value, BigDecimal minValue, BigDecimal maxValue, boolean inclusive,
			String fieldName, List<ErrorMessage> errors) {
		if (!ValidationUtil.decimalRangeValidator(value, minValue, maxValue, inclusive)) {
			errors.add(new ErrorMessage(fieldName,
					String.format("最小值为%s, 最大值为%s （%s边界值）", minValue, maxValue, inclusive ? "包含" : "不包含")));
		}
	}

	public static void decimalMaxValidator(String value, BigDecimal maxValue, boolean inclusive, String fieldName,
			List<ErrorMessage> errors) {
		if (!ValidationUtil.decimalMaxValidator(value, maxValue, inclusive)) {
			errors.add(new ErrorMessage(fieldName, String.format("最大值为%s （%s边界值）", maxValue, inclusive ? "包含" : "不包含")));
		}
	}

	public static void decimalMinValidator(String value, BigDecimal minValue, boolean inclusive, String fieldName,
			List<ErrorMessage> errors) {
		if (!ValidationUtil.decimalMinValidator(value, minValue, inclusive)) {
			errors.add(new ErrorMessage(fieldName, String.format("最小值为%s （%s边界值）", minValue, inclusive ? "包含" : "不包含")));
		}
	}

	public static void digitValidator(String value, int maxIntegerLength, int maxFractionLength, String fieldName,
			List<ErrorMessage> errors) {
		if (!ValidationUtil.digitValidator(value, maxIntegerLength, maxFractionLength)) {
			errors.add(new ErrorMessage(fieldName, String.format("最大整数位为%s，最大小数位为%s", maxIntegerLength, maxFractionLength)));
		}
	}

	public static void nullValidator(String value, String fieldName, List<ErrorMessage> errors) {
		if (!ValidationUtil.nullValidator(value)) {
			errors.add(new ErrorMessage(fieldName, String.format("不可为空")));
		}
	}

	public static void patternValidator(String value, String regex, int flags, String fieldName,
			List<ErrorMessage> errors) {
		if (!ValidationUtil.patternValidator(value, regex, flags)) {
			errors.add(new ErrorMessage(fieldName, String.format("格式不正确")));
		}
	}

	public static void datePastValidator(Date date, String fieldName, List<ErrorMessage> errors) {
		if (!ValidationUtil.datePastValidator(date)) {
			errors.add(new ErrorMessage(fieldName, String.format("日期已超过今天")));
		}
	}

	public static void calendarPastValidator(Calendar cal, String fieldName, List<ErrorMessage> errors) {
		if (!ValidationUtil.calendarPastValidator(cal)) {
			errors.add(new ErrorMessage(fieldName, String.format("日期已超过今天")));
		}
	}

	public static void datePastValidator(String dateString, String format, String fieldName,
			List<ErrorMessage> errors) {
		if (!ValidationUtil.datePastValidator(dateString, format)) {
			errors.add(new ErrorMessage(fieldName, String.format("日期已超过今天")));
		}
	}

	public static void emailValidator(String value, String fieldName, List<ErrorMessage> errors) {
		if (!ValidationUtil.emailValidator(value)) {
			errors.add(new ErrorMessage(fieldName, String.format("邮件地址格式不正确")));
		}
	}

	public static void lengthValidator(String value, int min, int max, String fieldName, List<ErrorMessage> errors) {
		if (!ValidationUtil.lengthValidator(value, min, max)) {
			errors.add(new ErrorMessage(fieldName, String.format("最小长度为%s，最大长度为%s", min, max)));
		}
	}

	public static void notBlankValidator(String value, String fieldName, List<ErrorMessage> errors) {
		if (!ValidationUtil.notBlankValidator(value)) {
			errors.add(new ErrorMessage(fieldName, String.format("不可为空")));
		}
	}

	public static void urlValidator(String value, String protocol, String host, int port, String fieldName,
			List<ErrorMessage> errors) {
		if (!ValidationUtil.urlValidator(value, protocol, host, port)) {
			errors.add(new ErrorMessage(fieldName, String.format("URL不合法")));
		}
	}

}
