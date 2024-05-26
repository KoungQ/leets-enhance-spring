package leets.enhance.domain.item.controller;

import leets.enhance.domain.item.entity.Item;
import leets.enhance.domain.item.model.request.ItemEnhanceDto;
import leets.enhance.domain.item.model.response.ItemEnhanceResponseDto;
import leets.enhance.domain.item.service.ItemService;
import leets.enhance.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enhance")
@RequiredArgsConstructor
public class enhanceController {

    private final ItemService itemService;

    @PostMapping()
    public BaseResponse<ItemEnhanceResponseDto> enhance(@RequestBody ItemEnhanceDto requestDto) {
        Item item = itemService.getItem(requestDto.getId());

        return BaseResponse.of(
                ItemEnhanceResponseDto.builder()
                .item(item)
                .status(itemService.enhance(item, requestDto.getIsBoosted()))
                .build());
    }
}
