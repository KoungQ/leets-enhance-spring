package leets.enhance.domain.item.controller;

import leets.enhance.domain.item.entity.Item;
import leets.enhance.domain.item.model.request.ItemRegisterDto;
import leets.enhance.domain.item.service.ItemService;
import leets.enhance.domain.user.entity.User;
import leets.enhance.domain.user.service.UserService;
import leets.enhance.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    @PostMapping()
    public BaseResponse<Item> register(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ItemRegisterDto requestDto) {
        User user = userService.getUser(userDetails.getUsername());
        return BaseResponse.of(itemService.register(user, requestDto));
    }

    @GetMapping("")
    public BaseResponse<Item> findMyItem(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());
        return BaseResponse.of(user.getItem());
    }

    @GetMapping("/top10")
    public BaseResponse<List<Item>> findTop10() {
        return BaseResponse.of(itemService.findTop10());
    }
}
