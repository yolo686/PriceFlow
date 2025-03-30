package com.priceflow.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



/**
 * @author 33954
 * #Description Result
 * #Date: 2025/3/17 14:32
 */

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description= "返回结果")
public class Result<T> implements Serializable {

    private Integer code; //编码：200 成功，500 服务器异常，401 没有登录，403 没有权限，400 参数错误
    private String msg; //错误信息
    private T data; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 200;
        result.msg = "OK";
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 200;
        result.msg = "OK";
        return result;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = code;
        return result;
    }

}

