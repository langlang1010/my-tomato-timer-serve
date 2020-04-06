package cn.smileyan.demo.util;

/**
 * 用来检查邮箱格式是否正确
 * @author Smileyan
 */
public class EmailFormCheck {
    /**
     * 判断email格式是否正确
     * @param email 邮箱字符串
     * @return 是否正确
     */
    public static boolean checkEmailForm(String email) {
        email = email+"";
        return email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }
}
