package uz.brb.transaction_with_aop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.brb.transaction_with_aop.dto.response.Response;
import uz.brb.transaction_with_aop.entity.Users;
import uz.brb.transaction_with_aop.mapper.UserMapper;
import uz.brb.transaction_with_aop.service.UserService;

import java.time.LocalDateTime;

import static uz.brb.transaction_with_aop.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public Response<?> me(Users user) {
        if (user == null) {
            return Response.builder()
                    .code(HttpStatus.OK.value())
                    .status(HttpStatus.OK)
                    .message("USER IS NULL")
                    .success(false)
                    .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                    .build();
        }
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("AuthUser successfully found")
                .success(true)
                .data(userMapper.toResponse(user))
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}
