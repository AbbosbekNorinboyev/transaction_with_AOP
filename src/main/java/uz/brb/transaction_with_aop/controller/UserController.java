package uz.brb.transaction_with_aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brb.transaction_with_aop.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/test-tx")
    public String testTransaction() {
        try {
            userService.createUsers();
        } catch (Exception e) {
            return "Transaction rollback qilindi!";
        }
        return "Transaction commit qilindi!";
    }
}
