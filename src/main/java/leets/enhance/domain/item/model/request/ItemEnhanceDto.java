package leets.enhance.domain.item.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ItemEnhanceDto {
    @NotBlank
    private Long id;
    @NotBlank
    private Boolean isBoosted;
}
