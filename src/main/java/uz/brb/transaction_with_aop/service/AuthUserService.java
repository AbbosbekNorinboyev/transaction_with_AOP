package uz.brb.transaction_with_aop.service;

import uz.brb.transaction_with_aop.dto.request.LoginRequest;
import uz.brb.transaction_with_aop.dto.request.RegisterRequest;
import uz.brb.transaction_with_aop.dto.request.UpdatePasswordRequest;
import uz.brb.transaction_with_aop.dto.response.Response;

public interface AuthUserService {
    Response<?> register(RegisterRequest registerRequest);

    Response<?> login(LoginRequest loginRequest);

    Response<?> changePassword(UpdatePasswordRequest request);
}