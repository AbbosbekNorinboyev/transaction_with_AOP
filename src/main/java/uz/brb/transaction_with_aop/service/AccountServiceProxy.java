package uz.brb.transaction_with_aop.service;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import uz.brb.transaction_with_aop.repository.AccountRepository;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class AccountServiceProxy extends AccountService {
    private final TransactionInterceptor transactionInterceptor;
    private final AccountService accountService;

    public AccountServiceProxy(AccountRepository accountRepository,
                               TransactionInterceptor transactionInterceptor,
                               AccountService accountService) {
        super(accountRepository);
        this.transactionInterceptor = transactionInterceptor;
        this.accountService = accountService;
    }

    @Override
    public String transfer(Long from, Long to, double amount) {
        // get transactio()
//        return super.transfer(from, to, amount);
        // commit the transaction()
        // rollback the transaction()
        // get methodInvoke()

        try {
            // Construct MethodInvocation manually (pseudocode)
            Method method = AccountService.class.getMethod("transfer", Long.class, Long.class, double.class);
            MethodInvocation invocation = (MethodInvocation) new MethodInvocation() {
                @Override
                public Object proceed() throws Throwable {
                    return accountService;
                }

                @Override
                public Object getThis() {
                    return accountService;
                }

                @Override
                public AccessibleObject getStaticPart() {
                    return method;
                }

                @Override
                public Object[] getArguments() {
                    return new Object[]{from, to, amount};
                }

                @Override
                public Method getMethod() {
                    return method;
                }
            }.proceed();
            transactionInterceptor.invoke(invocation);
        } catch (Throwable e) {
            throw new RuntimeException("Transaction failed: " + e);
        }
        return "";
    }
}
