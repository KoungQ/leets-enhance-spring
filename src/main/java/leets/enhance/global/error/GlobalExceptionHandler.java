package leets.enhance.global.error;

import leets.enhance.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";
    @ExceptionHandler(ServiceException.class)
    public BaseResponse handleApplicationException(ServiceException ex) {
        log.error(LOG_FORMAT, ex.getClass().getSimpleName(), ex.getErrorCode(), ex.getMessage());
        return BaseResponse.of(ex.getErrorCode(), ex.getMessage());
    }
}
