package leets.enhance.domain.user.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRegisterDto {  // 수정: DTO 모두 class -> record

    @NotNull
    private String email;   // 수정: Validation 추가

    @NotNull
    private String name;

    @NotNull
    private String pwd;

    @NotNull
    private String checkPwd;

    @NotNull
    private Boolean isChecked;
}
