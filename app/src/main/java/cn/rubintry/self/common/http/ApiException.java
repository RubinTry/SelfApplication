package cn.rubintry.self.common.http;

import androidx.annotation.Nullable;

/**
 * @author logcat
 */
public class ApiException extends RuntimeException {
    private String message;

    public ApiException(String message) {
        this.message = message;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}
