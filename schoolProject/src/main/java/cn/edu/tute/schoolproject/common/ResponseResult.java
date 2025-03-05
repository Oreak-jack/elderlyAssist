package cn.edu.tute.schoolproject.common;

import java.io.Serializable;

/**
 * 通用响应结果类
 * @param <T> 数据类型
 */
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success; // 是否成功
    private String message;  // 提示信息
    private T data;         // 数据
    private Integer code;    // 状态码

    // 成功的静态方法
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>().setSuccess(true).setCode(200);
    }

    public static <T> ResponseResult<T> success(String message) {
        return new ResponseResult<T>().setSuccess(true).setCode(200).setMessage(message);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>().setSuccess(true).setCode(200).setData(data);
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<T>().setSuccess(true).setCode(200).setMessage(message).setData(data);
    }

    // 失败的静态方法
    public static <T> ResponseResult<T> error() {
        return new ResponseResult<T>().setSuccess(false).setCode(500);
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<T>().setSuccess(false).setCode(500).setMessage(message);
    }

    public static <T> ResponseResult<T> error(Integer code, String message) {
        return new ResponseResult<T>().setSuccess(false).setCode(code).setMessage(message);
    }

    // Getter and Setter
    public Boolean getSuccess() {
        return success;
    }

    public ResponseResult<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }
} 