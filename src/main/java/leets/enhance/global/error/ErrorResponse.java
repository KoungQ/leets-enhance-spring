package leets.enhance.global.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }
}
