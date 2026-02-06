package uz.brb.transaction_with_aop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.brb.transaction_with_aop.dto.request.AccountRequest;
import uz.brb.transaction_with_aop.dto.response.Response;
import uz.brb.transaction_with_aop.entity.Account;
import uz.brb.transaction_with_aop.exception.ResourceNotFoundException;
import uz.brb.transaction_with_aop.repository.AccountRepository;

import java.time.LocalDateTime;

import static uz.brb.transaction_with_aop.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    // spring transaction : AOP
    // around advice : before me + after me
    // before me -> create or get the transaction
    // transfer() method invoke
    // after me (success) -> commit the transaction
    // after me (failure) -> rollback the transaction

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public String transfer(Long from, Long to, double amount) {
        Account senderAccount = accountRepository.findById(from)
                .orElseThrow(() -> new ResourceNotFoundException("Sender account not found"));

        Account receiverAccount = accountRepository.findById(to)
                .orElseThrow(() -> new ResourceNotFoundException("Receiver account not found"));

        //deduct balance from sender
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        accountRepository.save(senderAccount);

//        forcefullyThrowingException();

        //add balance on receiver account
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        accountRepository.save(receiverAccount);

        return "Amount transferred successfully from " + from + " to" + to;
    }

    public void forcefullyThrowingException() {
        throw new RuntimeException("receiver bank server is down ");
    }

    public Response<?> add(AccountRequest accountRequest) {
        Account account = new Account();
        account.setBalance(accountRequest.getBalance());
        account.setHolderName(accountRequest.getHolderName());
        account.setCreatedAt(LocalDateTime.now());
        accountRepository.save(account);
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("Account successfully added")
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}
