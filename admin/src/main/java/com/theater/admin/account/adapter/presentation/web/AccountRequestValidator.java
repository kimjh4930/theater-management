package com.theater.admin.account.adapter.presentation.web;

import org.springframework.stereotype.Component;

@Component
public class AccountRequestValidator {
//    private final AccountRepository accountRepository;
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.isAssignableFrom(NewAccount.class);
//    }
//
//    @Override
//    public void validate(Object object, Errors errors) {
//        NewAccount account = (NewAccount) object;
//
//        if(accountRepository.existByEmail(account.getEmail())){
//            errors.rejectValue(
//                    "email",
//                    "invalid.email",
//                    new Object[]{account.getEmail()},
//                    "이미 사용중인 이메일 입니다."
//            );
//        }
//
//        if(accountRepository.existByNickname(account.getNickname())){
//            errors.rejectValue(
//                    "nickname",
//                    "invalid.nickname",
//                    new Object[]{account.getEmail()},
//                    "이미 사용중인 닉네임 입니다."
//            );
//        }
//    }
}
