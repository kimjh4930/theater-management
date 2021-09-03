package com.theater.admin.account.adapter.presentation.web;

import com.theater.admin.account.application.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/signup")
    public String signup (Model model){
        model.addAttribute("newAccount", new NewAccount());
        return "account/signup";
    }

    @PostMapping(value = "/signup")
    public String signUpSummit (
            @Valid @ModelAttribute NewAccount newAccount,
            Errors errors){

        if(errors.hasErrors()){
            errors.getAllErrors().forEach(System.out::println);
            return "account/signup";
        }

        accountService.saveNewAccount(newAccount);

        return "redirect:/";
    }

}
