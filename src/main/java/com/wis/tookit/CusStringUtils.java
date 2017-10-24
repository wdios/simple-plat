package com.wis.tookit;

/**
 * 字符串工具类
 * @author wd
 */
public class CusStringUtils {
	/**
	 * 是否为空或者 null
	 * @param str 被检查的字符串
	 * @return true 结果
	 */
	public static boolean isEmpty(String str) {
		if ((str == null) || trim(str).equals("") || str.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 是否不为空或者 null
	 * @param str 被检查的字符串
	 * @return true 结果
	 */
	public static boolean isNotEmpty(String str) {
		if ((str == null) || trim(str).equals("") || str.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

	/**
	 * 对象是否为空
	 * @param obj 被检查对象
	 * @return true 结果
	 */
	public static boolean isEmpty(Object obj) {
		if ((null == obj) || obj.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 过滤显示的 null 字符串
	 */
	public static String StringFilteNull(String input) {
		if ((null == input) || input.equals("") || input.equalsIgnoreCase("null")) {
			return "";
		}
		return input;
	}
	
	
	/**
	 * 删除字符串前后空格
	 * @param str 带处理字符串
	 * @return trimed 处理后字符串
	 */
	public static String trim(String str) {
		if ((str == null) || str.trim().equals("")) {
			return "";
		}

		String newStr = str.trim();
		boolean startFull = newStr.startsWith("　"); // 12288
		boolean endFull = newStr.endsWith("　"); // 12288
		boolean startHalf = newStr.startsWith(" "); // 97
		boolean endHalf = newStr.endsWith(" "); // 97

		while (startFull || endFull || startHalf || endHalf) {
			startFull = newStr.startsWith("　"); // 12288
			endFull = newStr.endsWith("　"); // 12288

			if (startFull) {
				if (newStr.length() == 1) {
					return "";
				}

				newStr = newStr.substring(1);
			}

			if (endFull) {
				if (newStr.length() == 1) {
					return "";
				}

				newStr = newStr.substring(0, newStr.length() - 1);
			}

			startHalf = newStr.startsWith(" "); // 97
			endHalf = newStr.endsWith(" "); // 97

			if (startHalf) {
				newStr = newStr.substring(1);
			}

			if (endHalf) {
				newStr = newStr.substring(0, newStr.length() - 1);
			}
		}

		return newStr;
	}

	
	/**
	 * 格式化字符串，前置补充，比如 1 前置补零变成 001
	 * @param strInput 待处理字符串
	 * @param prefix 前置补充字符
	 * @param length > 0 字符串长度
	 * @return String 处理后字符串
	 */
	public static String stringFormat(String strInput, String prefix, int length) {
		String strResult = "";
		if (isNotEmpty(strInput) && prefix != null && length > 0) {
			strResult = strInput.trim();
			while (strResult.length() < length) {
				strResult = prefix + strResult;
			}
		}
		return strResult;
	}
	
	/**
	 * 去除指定字符串中的分隔符，并返回字符数组
	 * @param targetStr 目标字符串
	 * @param mark 要去除的分隔符
	 * @return 去掉分隔符后的字符数组
	 */
	public static String[] splitMark(String targetStr,String mark){
		String[] strArray = new String[]{};
		if ( !"".equals(targetStr)) {
			if (targetStr.indexOf(mark) != -1) {
				if ("|".equals(mark)) {
					strArray = targetStr.split("\\|");
				} else {
					strArray = targetStr.split(mark);
				}
			} else {
				strArray = new String[]{targetStr};
			}
		}
		return strArray;
	}
	
}
