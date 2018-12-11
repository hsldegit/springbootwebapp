package guru.springframework.common.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description StringUtils.java
 * @author mapenghui
 * @date 2017年6月30日 下午4:30:25
 */
public class StringUtils {

    public static Random random = new Random();
    
    
    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }
    
    public static boolean isBlank(Object str) {
        return str == null || "".equals((str + "").trim());
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 产生一个随机字符串
     * 
     * @param range 可选字符
     * @param len 生成字符串的长度
     * @return
     */
    public static String genRandomStr(String range, int len) {
        if (isBlank(range) || len <= 0)
            return "";

        StringBuilder builder = new StringBuilder();
        char[] arr = range.toCharArray();
        for (int i = 0; i < len; i++) {
            builder.append(arr[random.nextInt(arr.length)]);
        }

        return builder.toString();
    }

    public static String trimRight(String s) {
        Pattern pattern = Pattern.compile("-* ");
        for (Matcher matcher = pattern.matcher(s); matcher.matches();) {
            s = s.substring(0, s.length() - 1);

            matcher = pattern.matcher(s);
        }

        return s;
    }

    /**
     * 字符串拼接
     * 
     * @param len 开始拼接的位置
     * @param str 待处理的字符串
     * @param padChar 要拼接的字符
     * @param direction 拼接方向
     * @return 拼接好的字符串
     */
    public static String stringPading(int len, String str, String padChar, String direction) {
        String result = "";
        StringBuilder tmpResult = new StringBuilder("");
        if (str == null) {
            return result;
        }
        int strLen = str.trim().length();
        if ((str != null) && (strLen > len)) {
            result = str.trim().substring(0, len);
        } else if ((str != null) && (strLen == len)) {
            result = str.trim();
        } else {
            result = str.trim();
            for (int i = 0; i < len - strLen; ++i) {
                tmpResult = tmpResult.append(padChar);
            }
            if ("L".equalsIgnoreCase(direction)) {
                result = tmpResult.toString() + result;
            } else {
                result = result + tmpResult.toString();
            }
        }
        return result;
    }

    /**
     * 使用递归判断并返回自定义长度的字符串
     * @param len 保留长度
     * @param str 目标字符串
     * @return prefix+0..str -> dm00795
     */
    public static String stringJoint(int len, String str, String prefix) {
    	
        if(str.length() < len) {
        	str = "0" + str;
        	return stringJoint(len,str,prefix);
        }
        
        return prefix + str;
    }
    
	/**
	 * 获取长度
	 * @param obj
	 * @return length
	 */
	public static int getLength(Object obj){
		String str = obj + "";
		if(isNotBlank(str)) {
			return str.length();
		}
		return 0;
	}
	
    /**
     * @param value
     * @param tClass
     * @return
     */
    public static Object castToBaseObject(String value, Class<?> tClass) {
        if (isNotNullOrEmpty(value)) {
            tClass.cast(value);
        }

        return null;
    }
    
    /**
     * @param name
     * @return
     */
    public static String capitalize(String name) {
        if (isNotNullOrEmpty(name)) {
            char[] cs = name.toCharArray();

            cs[0] -= 32;

            return String.valueOf(cs);
        }

        return null;
    }
    
    public static boolean validaStrRule(String str){
        boolean result = false;
        if (!isBlank(str)) {
            if (str.matches("^([0-9]\\d*|[0-9]\\d*\\.\\d*)$") && new BigDecimal(str).compareTo(BigDecimal.ZERO)>0) {
                result = true;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
       System.out.println(validaStrRule("0.0")); 
    }
	
}
