package com.digitalchina.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by xiaoning.sun on 2016/11/30.
 */
public class NumberUtil {

    /**
     * Convert a String to an int, returning zero if the conversion fails
     * @param str
     * @return
     */
    public static int stringToInt(String str) {
        return toInt(str,0);
    }
    /**
     * Convert a String to an int, returning a
     * default value if the conversion fails.
     *
     * If the string is null, the default value is returned.
     *
     * <pre>
     *   NumberUtils.toInt(null, 1) = 1
     *   NumberUtils.toInt("", 1)   = 1
     *   NumberUtils.toInt("1", 0)  = 1
     * </pre>
     *
     * @param str  the string to convert, may be null
     * @param defaultValue  the default value
     * @return the int represented by the string, or the default if conversion fails
     */
    public static int toInt(String str, int defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * Convert a String to a Float.
     *
     * @param val  a String to convert
     * @return converted Float
     * @throws NumberFormatException if the value cannot be converted
     */
    public static Float createFloat(String val) {
        return Float.valueOf(val);
    }
    /**
     * Convert a String to a Double.
     *
     * @param val  a String to convert
     * @return converted Double
     * @throws NumberFormatException if the value cannot be converted
     */
    public static Double createDouble(String val) {
        return Double.valueOf(val);
    }

    /**
     * Convert a String to a Integer, handling
     * hex and octal notations.
     *
     * @param val  a String to convert
     * @return converted Integer
     * @throws NumberFormatException if the value cannot be converted
     */
    public static Integer createInteger(String val) {
        // decode() handles 0xAABD and 0777 (hex and octal) as well.
        return Integer.decode(val);
    }

    /**
     * Convert a String to a byte, returning
     * zero if the conversion fails.
     *
     * If the string is null, zero is returned.
     *
     * <pre>
     *   NumberUtils.toByte(null) = 0
     *   NumberUtils.toByte("")   = 0
     *   NumberUtils.toByte("1")  = 1
     * </pre>
     *
     * @param str  the string to convert, may be null
     * @return the byte represented by the string, or zero if
     *  conversion fails
     */
    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }
    /**
     * Convert a String to a byte, returning a
     * default value if the conversion fails.
     *
     * If the string is null, the default value is returned.
     *
     * <pre>
     *   NumberUtils.toByte(null, 1) = 1
     *   NumberUtils.toByte("", 1)   = 1
     *   NumberUtils.toByte("1", 0)  = 1
     * </pre>
     *
     * @param str  the string to convert, may be null
     * @param defaultValue  the default value
     * @return the byte represented by the string, or the default if conversion fails
     */
    public static byte toByte(String str, byte defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }
    /**
     * Convert a String to a short, returning
     * zero if the conversion fails.
     *
     * If the string is null, zero is returned.
     *
     * <pre>
     *   NumberUtils.toShort(null) = 0
     *   NumberUtils.toShort("")   = 0
     *   NumberUtils.toShort("1")  = 1
     * </pre>
     *
     * @param str  the string to convert, may be null
     * @return the short represented by the string, or zero if
     *  conversion fails
     */
    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }
    /**
     * Convert a String to an short, returning a
     * default value if the conversion fails.
     *
     * If the string is null, the default value is returned.
     *
     * <pre>
     *   NumberUtils.toShort(null, 1) = 1
     *   NumberUtils.toShort("", 1)   = 1
     *   NumberUtils.toShort("1", 0)  = 1
     * </pre>
     *
     * @param str  the string to convert, may be null
     * @param defaultValue  the default value
     * @return the short represented by the string, or the default if conversion fails
     */
    public static short toShort(String str, short defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }
    /**
     * Convert a String to a Long.
     *
     * Returns null if the string is null.
     *
     * @param str  a String to convert, may be null
     * @return converted Long
     * @throws NumberFormatException if the value cannot be converted
     */
    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.valueOf(str);
    }
    /**
     * Convert a String to a BigInteger.
     *
     * Returns null if the string is null.
     *
     * @param str  a String to convert, may be null
     * @return converted BigInteger
     * @throws NumberFormatException if the value cannot be converted
     */
    public static BigInteger createBigInteger(String str) {
        if (str == null) {
            return null;
        }
        return new BigInteger(str);
    }
    /**
     * Convert a String to a BigDecimal.
     *
     * Returns null if the string is null.
     *
     * @param str  a String to convert, may be null
     * @return converted BigDecimal
     * @throws NumberFormatException if the value cannot be converted
     */
    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtil.isEmpty(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        return new BigDecimal(str);
    }
    /**
     * Returns the minimum value in an array.
     *
     * @param array  an array, must not be null or empty
     * @return the minimum value in the array
     * @throws IllegalArgumentException if array is null
     * @throws IllegalArgumentException if array is empty
     */
    public static long min(long[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        // Finds and returns min
        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }
    /**
     * Returns the minimum value in an array.
     *
     * @param array  an array, must not be null or empty
     * @return the minimum value in the array
     * @throws IllegalArgumentException if array is null
     * @throws IllegalArgumentException if array is empty
     */
    public static int min(int[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        // Finds and returns min
        int min = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] < min) {
                min = array[j];
            }
        }

        return min;
    }
    /**
     * Returns the minimum value in an array.
     *
     * @param array  an array, must not be null or empty
     * @return the minimum value in the array
     * @throws IllegalArgumentException if array is null
     * @throws IllegalArgumentException if array is empty
     */
    public static short min(short[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        // Finds and returns min
        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }
    /**
     * Returns the minimum value in an array.
     *
     * @param array  an array, must not be null or empty
     * @return the minimum value in the array
     * @throws IllegalArgumentException if array is null
     * @throws IllegalArgumentException if array is empty
     */
    public static double min(double[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        // Finds and returns min
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Double.isNaN(array[i])) {
                return Double.NaN;
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }
    /**
     * Checks whether the String contains only
     * digit characters.
     *
     * Null and empty String will return
     * false.
     *
     * @param str  the String to check
     * @return true if str contains only unicode numeric
     */
    public static boolean isDigits(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
















}
