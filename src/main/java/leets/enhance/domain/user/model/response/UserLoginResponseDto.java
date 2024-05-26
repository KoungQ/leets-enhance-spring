package leets.enhance.domain.user.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponseDto {

    String accessToken;
    String refreshToken;

}
