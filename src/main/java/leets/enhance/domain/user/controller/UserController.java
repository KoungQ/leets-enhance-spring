package leets.enhance.domain.user.controller;

import leets.enhance.domain.user.entity.User;
import leets.enhance.domain.user.model.request.UserCheckDuplicateIdDto;
import leets.enhance.domain.user.model.request.UserLoginDto;
import leets.enhance.domain.user.model.request.UserRegisterDto;
import leets.enhance.domain.user.service.UserService;
import leets.enhance.global.common.BaseResponse;
import leets.enhance.global.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse<User> register(@RequestBody UserRegisterDto requestDto) {
        return BaseResponse.of(userService.register(requestDto));
    }

    @PostMapping("/login")
    public BaseResponse<JwtToken> login(@RequestBody UserLoginDto requestDto) {
        return BaseResponse.of(userService.login(requestDto));
    }

    @GetMapping("/check-duplicate-id")
    public BaseResponse<Boolean> checkDuplicateId(@RequestBody UserCheckDuplicateIdDto requestDto) {
        return BaseResponse.of(userService.check(requestDto));
    }
}