package com.sefrWahed.apeye.api;

/**
 * Created by Abdel-Rahman El-shikh
 */
public class ApiResponse<T> {
    private Exception apiException;
    private T data;
    private ApiError apiError;
    private Status status;

    public Exception getApiException() {
        return apiException;
    }

    public void setApiException(Exception apiException) {
        this.apiException = apiException;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
