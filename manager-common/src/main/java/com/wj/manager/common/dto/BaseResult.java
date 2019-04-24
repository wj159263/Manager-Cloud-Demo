package com.wj.manager.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseResult implements Serializable {
    public static final String RESULT_OK = "ok";
    public static final String RESULT_OK_MESSAGE = "操作成功";
    public static final String RESULT_NOT_OK = "not_ok";
    public static final String RESULT_NOT_OK_MESSAGE = "操作失败";
    private String result;
    private Object data;
    private String success;
    private List<Error> errors;
    private Cursor cursor;

    private BaseResult(){}
    public static BaseResult ok(){
        return createResult(RESULT_OK,null,RESULT_OK_MESSAGE,null,null);
    }
    public static BaseResult ok(Object data){
        return createResult(RESULT_OK,data,RESULT_OK_MESSAGE,null,null);
    }

    public static BaseResult notOk(List<Error> errors){
        return createResult(RESULT_NOT_OK,null,RESULT_NOT_OK_MESSAGE,null,errors);
    }



    private static BaseResult createResult(String result,Object data,String success,
                                           Cursor cursor, List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);
        return baseResult;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error implements Serializable{
        private String field;
        private String message;
    }
    @Data
    public static class Cursor implements Serializable{
        private int total;
        private int offset;
        private int limit;
    }
}
