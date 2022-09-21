package com.rachein.mmzf2.result;

import lombok.Data;

/**
 * @author 计算机系 ITAEM 吴远健
 * @date 2022/2/27 20:33
 */
@Data
public class CodeMsg {

    private int code;
    private String msg;

    //通用：
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(50001, "服务器发生异常！");
    public static CodeMsg BIND_ERROR = new CodeMsg(50002, "参数错误！");


    //文件: 5001xx
    public static CodeMsg FILE_EMPTY = new CodeMsg(500101, "上传的文件不能为空！");
    public static CodeMsg FILE_MEMORY_OVER = new CodeMsg(500101, "文件大小过大！");
    public static CodeMsg FILE_ERROR = new CodeMsg(500102, "文件失败，请稍后再试！");


    //网络: 5002xx
    public static CodeMsg RESPONSE_ERROR = new CodeMsg(500201, "服务器请求异常，请稍后重试！");


    public CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回带参数的错误码
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
