package leets.enhance.domain.user.exception;

import leets.enhance.global.error.ErrorCode;
import leets.enhance.global.error.ServiceException;

public class InvalidAccessException extends ServiceException {
    public InvalidAccessException(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
