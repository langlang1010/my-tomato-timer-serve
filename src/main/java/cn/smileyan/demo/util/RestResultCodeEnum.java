package cn.smileyan.demo.util;

/**
 * @author Smileyan
 */

public enum RestResultCodeEnum {
    /**
     * 请求成功
      */
    SUCCESS(200),
    /**
     * 用户名不存在
     */
    LOGIN_USER_NOT_EXSIST(421),
    /**
     * 密码错误
     */
    LOGIN_FAULT_PASSWORD(422),
    /**
     * 用户名为空
     */
    LOGIN_NULL_USERNAME(423),
    /**
     * 密码为空
     */
    LOGIN_NULL_PASSWORD(424),
    /**
     * 用户名已存在
     */
    REGISTER_USER_EXISTED(411),
    /**
     * 密码不能为空
     */
    REGISTER_NULL_PASSWORD(412),
    /**
     * 密码太短
     */
    REGISTER_TOO_SHORT_PASSWORD(413),
    /**
     * 邮箱为空
     */
    REGISTER_NULL_EMAIL(414),
    /**
     * 邮箱格式错误
     */
    REGISTER_FAULT_EMAIL(415),
    /**
     * 授权码超时
     */
    TIMEOUT_TOKEN(415),
    /**
     * 请求资源未找到
     */
    NONE_RESULT(404);
    private int seq;
    RestResultCodeEnum(int seq) {
        this.seq = seq;
    }
    public int getSeq() {
        return this.seq;
    }
}
