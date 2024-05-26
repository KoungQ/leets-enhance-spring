package leets.enhance.domain.item.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ItemRegisterDto {

    @NotBlank
    @Size(min = 5)
    private String name;
}
