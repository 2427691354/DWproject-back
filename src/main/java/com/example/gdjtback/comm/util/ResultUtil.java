package com.example.gdjtback.comm.util;

import lombok.Data;

@Data
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result warn(String msg) {
        Result result = new Result();
        result.setCode(300);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }
}
