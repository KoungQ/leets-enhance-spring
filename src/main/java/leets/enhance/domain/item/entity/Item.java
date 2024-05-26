package leets.enhance.domain.item.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import leets.enhance.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static leets.enhance.domain.item.entity.Status.*;

@Entity
@Table(name = "item")
@Getter @Setter
@RequiredArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Integer level;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private User user;

    @PrePersist
    public void init() {
        this.level = 1;
    }

    @Builder(builderMethodName = "of")
    public Item(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Status success() {
        this.level++;
        return SUCCESS;
    }

    public Status fail() {
        if(this.level == 0)     // 파괴된 아이템이라면 실패해도 강화 단계가 하락하지 않음
            return FAIL;

        this.level--;
        return FAIL;
    }

    public Status destroy() {
        this.level = 0;
        return DESTROY;
    }
}
