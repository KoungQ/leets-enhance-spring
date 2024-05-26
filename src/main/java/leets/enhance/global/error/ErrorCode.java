package leets.enhance.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ITEM_ALREADY_EXIST(400, "이미 아이템이 존재합니다."),
    MISMATCH_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    UNCHECKED_EMAIL(400, "이메일 중복 확인을 먼저 해주세요."),

    INVALID_ITEM(404, "존재하지 않는 아이템입니다."),
    INVALID_USER(404, "존재하지 않는 유저입니다."),
    INVALID_ID(404, "존재하지 않는 아이디입니다."),
    INVALID_PASSWORD(404, "존재하지 않는 비밀번호입니다.");

    private final int code;
    private final String message;
}