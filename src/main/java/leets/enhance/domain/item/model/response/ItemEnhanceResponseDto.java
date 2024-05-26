package leets.enhance.domain.item.model.response;

import leets.enhance.domain.item.entity.Item;
import leets.enhance.domain.item.entity.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemEnhanceResponseDto {
    Item item;
    Status status;
}
