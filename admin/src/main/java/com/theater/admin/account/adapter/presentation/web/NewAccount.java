package com.theater.admin.account.adapter.presentation.web;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
@ToString
public class NewAccount {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String name;

    @NotNull
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String nickname;

    @NotBlank
    @Length(min = 8, max = 20)
    private String password;

    public NewAccount () {}

    public NewAccount (Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.nickname = builder.nickname;
        this.password = builder.password;
    }

    public static class Builder {
        private String email;
        private String name;
        private String nickname;
        private String password;

        public Builder () {}

        public Builder email (String email){
            this.email = email;
            return this;
        }

        public Builder name (String name){
            this.name = name;
            return this;
        }

        public Builder nickname (String nickname){
            this.nickname = nickname;
            return this;
        }

        public Builder password (String password){
            this.password = password;
            return this;
        }

        public NewAccount build() {
            return new NewAccount(this);
        }
    }
}
