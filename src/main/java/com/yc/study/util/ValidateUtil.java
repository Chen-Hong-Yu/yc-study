package com.yc.study.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 非空验证工具类
 */
public class ValidateUtil {

	private ValidateUtil() {
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		} else {
			if (o instanceof String) {
				if (o.toString().trim().equals("")) {
					return true;
				}
			} else if (o instanceof List) {
				if (((List) o).size() == 0) {
					return true;
				}
			} else if (o instanceof Map) {
				if (((Map) o).size() == 0) {
					return true;
				}
			} else if (o instanceof Set) {
				if (((Set) o).size() == 0) {
					return true;
				}
			} else if (o instanceof Object[]) {
				if (((Object[]) ((Object[]) o)).length == 0) {
					return true;
				}
			} else if (o instanceof int[]) {
				if (((int[]) ((int[]) o)).length == 0) {
					return true;
				}
			} else if (o instanceof long[] && ((long[]) ((long[]) o)).length == 0) {
				return true;
			}

			return false;
		}
	}

	public static boolean isOneEmpty(Object... os) {
		Object[] var1 = os;
		int var2 = os.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			Object o = var1[var3];
			if (isEmpty(o)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isAllEmpty(Object... os) {
		Object[] var1 = os;
		int var2 = os.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			Object o = var1[var3];
			if (!isEmpty(o)) {
				return false;
			}
		}

		return true;
	}

}
