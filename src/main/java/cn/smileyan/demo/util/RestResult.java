package cn.smileyan.demo.util;

/**
 * 用于RestController返回数据的格式
 * @author Smileyan
 */
public class RestResult {
    /**
     * message
     */
    private String msg;
    /**
     * 结果码
     */
    private int code;
    /**
     * 请求结果数据
     */
    private Object data;

    public String getMsg() {
        return msg;
    }

    public RestResult() {
        this.code = RestResultCodeEnum.SUCCESS.getSeq();
        this.msg = "请求成功";
        this.data = null;
    }

    public RestResult(Object data) {
        this.data = data;
        this.code = RestResultCodeEnum.SUCCESS.getSeq();
        this.msg = "请求成功";
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
