package com.aksoft.todolist.utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    public static ResponseEntity<String> finalResponse(HttpStatus httpStatus, String message, Object data) {
        ApiResponse<Object> response = new ApiResponse<>(httpStatus == HttpStatus.OK || httpStatus == HttpStatus.CREATED, message, data);
        return ResponseEntity.status(httpStatus).body(gson.toJson(response));
    }
}