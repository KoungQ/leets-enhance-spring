package leets.enhance.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private int code;
    private String message;
    private T data;

    public static <T> BaseResponse<T> of(int code, String message) {
        return new BaseResponse<>(code, message, null);
    }

    public static <T> BaseResponse<T> of(T dto) {
        return new BaseResponse<>(200, "요청에 성공했습니다.", dto);
    }
}
