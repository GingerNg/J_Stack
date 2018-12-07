/**
 * @Description
 */
package com.ginger.study.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;


public class CommonUtil {

	/**
	 * obj的null断言
	 * @Title assertNull
	 * @Description
	 * @param t
	 * @param defaultValue
	 * @return
	 */
	public static <T> T assertNull(T t, T defaultValue){

		if(null==t){
			return defaultValue;
		}
		return t;

	}

	public static boolean isCollectionEmpty(Collection t){
		if(null!=t && t.size()!=0){
			return false;
		}
		return true;
	}

	/**
	 * 从一定数值中随机抽取一定数量的样本
	 * @param sourceCount
	 * @param sampleCount
	 * @return
	 */
	public static int[] sample(int sourceCount, int sampleCount) {
		Hashtable hash = new Hashtable();
		if (sampleCount > sourceCount) {
			sampleCount = sourceCount;
		}
		int[] keys = new int[sampleCount];
		int sampleIndex = 0;
		int index = 0;
		for (int i = 0; i < sampleCount; i++) {
			while (true) {
				sampleIndex = (int) Math.round(Math.random() * (sourceCount - 1));
				if (!hash.containsKey("" + sampleIndex)) {
					hash.put("" + sampleIndex, "" + sampleIndex);
					break;
				}
			}
			keys[index++] = sampleIndex;
		}
		Arrays.sort(keys);
		return keys;
	}

	/**
	 * 字段只能由数字和小写字母组成
	 * @param idNumber
	 * @return
	 */
	public static boolean validateIdNumber(String idNumber) {
		String regex = "^[a-z0-9]+$";
		return idNumber.matches(regex);
	}
}
