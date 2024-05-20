package com.eneskaya.veterinarymanagementsystem.core.utilies;

import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.dto.response.cursor.CursorResponse;
import org.springframework.data.domain.Page;

// Result Helper class, generate this class for write less code
public class ResultHelper {
    public static <T> ResultData<T> createData(T data) {
        return new ResultData<>(true,Msg.CREATED, "201", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(true,Msg.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> successData(T data) {
        return new ResultData<>(true,Msg.OK, "200", data);
    }
    public static Result ok() {
        return new Result(true,Msg.OK, "200");
    }

    public static Result notFound(String msg) {
        return new Result(true, msg, "404");
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageSize(pageData.getSize());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setTotalElements(pageData.getTotalElements());

        return ResultHelper.successData(cursor);
    }

}
