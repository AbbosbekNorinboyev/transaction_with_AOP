package uz.brb.transaction_with_aop.service;

import uz.brb.transaction_with_aop.dto.response.Response;
import uz.brb.transaction_with_aop.entity.Users;

public interface UserService {
    Response<?> me(Users user);
}
