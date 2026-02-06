package uz.brb.transaction_with_aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.brb.transaction_with_aop.dto.request.AccountRequest;
import uz.brb.transaction_with_aop.dto.response.Response;
import uz.brb.transaction_with_aop.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/transfer")
    public String transfer(@RequestParam Long from,
                           @RequestParam Long to,
                           @RequestParam double amount) {
        accountService.transfer(from, to, amount);
        return "Transfer successful!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public Response<?> add(@RequestBody AccountRequest accountRequest) {
        return accountService.add(accountRequest);
    }
}