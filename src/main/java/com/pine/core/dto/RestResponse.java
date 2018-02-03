package com.pine.core.dto;


import com.pine.core.util.DateUtils;

import java.beans.ConstructorProperties;

/**
 * Created by chenbupang on 2017-11-23 16:11
 */
public class RestResponse<T> {
    private T payload;
    private boolean success;
    private String msg;
    private int code;
    private long timestamp;

    public RestResponse() {
        this.timestamp = DateUtils.getCurrentDateTime();
    }

    public RestResponse(boolean success) {
        this.timestamp = DateUtils.getCurrentDateTime();
        this.success = success;
    }

    public RestResponse(boolean success, T payload) {
        this.timestamp = DateUtils.getCurrentDateTime();
        this.success = success;
        this.payload = payload;
    }

    public T getPayload() {
        return this.payload;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public static RestResponse ok() {
        return builder().success(true).build();
    }

    public static <T> RestResponse ok(T payload) {
        return builder().success(true).payload(payload).build();
    }

    public static <T> RestResponse ok(T payload, int code) {
        return builder().success(true).payload(payload).code(code).build();
    }

    public static RestResponse fail() {
        return builder().success(false).build();
    }

    public static RestResponse fail(String msg) {
        return builder().success(false).msg(msg).build();
    }

    public static RestResponse ok(String msg) {
        return builder().success(true).msg(msg).build();
    }

    public static RestResponse fail(int code) {
        return builder().success(false).code(code).build();
    }

    public static RestResponse fail(int code, String msg) {
        return builder().success(false).msg(msg).code(code).build();
    }

    private static int $default$code() {
        return -1;
    }

    private static long $default$timestamp() {
        return DateUtils.getCurrentDateTime();
    }

    public static <T> RestResponse.RestResponseBuilder<T> builder() {
        return new RestResponse.RestResponseBuilder();
    }

    @ConstructorProperties({"payload", "success", "msg", "code", "timestamp"})
    public RestResponse(T payload, boolean success, String msg, int code, long timestamp) {
        this.payload = payload;
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.timestamp = timestamp;
    }

    public static class RestResponseBuilder<T> {
        private T payload;
        private boolean success;
        private String msg;
        private boolean code$set;
        private int code;
        private boolean timestamp$set;
        private long timestamp;

        RestResponseBuilder() {
        }

        public RestResponse.RestResponseBuilder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

        public RestResponse.RestResponseBuilder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public RestResponse.RestResponseBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public RestResponse.RestResponseBuilder<T> code(int code) {
            this.code = code;
            this.code$set = true;
            return this;
        }

        public RestResponse.RestResponseBuilder<T> timestamp(long timestamp) {
            this.timestamp = timestamp;
            this.timestamp$set = true;
            return this;
        }

        public RestResponse<T> build() {
            return new RestResponse(this.payload, this.success, this.msg, this.code$set ? this.code : RestResponse.$default$code(), this.timestamp$set ? this.timestamp : RestResponse.$default$timestamp());
        }

        public String toString() {
            return "RestResponse.RestResponseBuilder(payload=" + this.payload + ", success=" + this.success + ", msg=" + this.msg + ", code=" + this.code + ", timestamp=" + this.timestamp + ")";
        }
    }
}