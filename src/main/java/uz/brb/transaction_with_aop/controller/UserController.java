package uz.brb.transaction_with_aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brb.transaction_with_aop.dto.response.Response;
import uz.brb.transaction_with_aop.entity.Users;
import uz.brb.transaction_with_aop.service.UserService;
import uz.brb.transaction_with_aop.util.validator.CurrentUser;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public Response<?> me(@CurrentUser Users user) {
        return userService.me(user);
    }
}
