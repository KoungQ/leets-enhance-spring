package leets.enhance.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import leets.enhance.domain.item.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@RequiredArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @NotBlank
    private String name;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Item item;

    private Integer booster;

    @PrePersist
    public void init() {
        this.booster = 3;
    }

    @Builder(builderMethodName = "of")
    public User(String email, String pwd, String name) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }

    public void registerItem(Item item) {
        this.item = item;
    }

    public void useBooster() {
        this.booster--;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
