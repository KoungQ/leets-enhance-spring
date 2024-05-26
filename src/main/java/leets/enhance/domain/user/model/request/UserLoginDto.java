package leets.enhance.domain.user.model.request;

import lombok.Getter;

@Getter
public class UserLoginDto {

    String email;
    String pwd;
}
