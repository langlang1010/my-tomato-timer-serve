package cn.smileyan.demo;

public class UtilTest {
    public static void main(String[] args) {
        String email = "root@smileyan.cn";
        boolean matches = email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        System.out.println(matches);
    }

}
