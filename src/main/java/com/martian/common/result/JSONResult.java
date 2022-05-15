package com.martian.common.result;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回数据封装
 */
public class JSONResult {
    /**
     * 成功返回码
     */
    public static final int CODE_SUCCESS = 0;

    /**
     * 返回码
     */
    private Integer code = CODE_SUCCESS;

    /**
     * 返回消息说明
     */
    private String msg;

    /**
     * 数据体
     */
    private Object data;

    /**
     * 当前时间戳
     */
    private Long ts;

    public JSONResult() {
    }

    public JSONResult(Object data) {
        this(CODE_SUCCESS, null, data);
    }

    public JSONResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public JSONResult(String key, Object value) {
        this.ts = System.currentTimeMillis();
        this.code = 0;
        Map<String, Object> m = new HashMap<String, Object>(1);
        m.put(key, value);
        data = m;
    }

    public JSONResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        ts = System.currentTimeMillis();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
