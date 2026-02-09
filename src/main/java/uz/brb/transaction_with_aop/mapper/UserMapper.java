package uz.brb.transaction_with_aop.mapper;

import org.springframework.stereotype.Component;
import uz.brb.transaction_with_aop.dto.response.UserResponse;
import uz.brb.transaction_with_aop.entity.Users;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserResponse toResponse(Users entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build();
    }

    public List<UserResponse> responseList(List<Users> users) {
        if (users != null && !users.isEmpty()) {
            return users.stream().map(this::toResponse).toList();
        }
        return new ArrayList<>();
    }

    public void update(Users entity, UserResponse response) {
        if (response == null) {
            return;
        }
        if (response.getFullName() != null && !response.getFullName().trim().isEmpty()) {
            entity.setFullName(response.getFullName());
        }
        if (response.getPassword() != null && !response.getPassword().trim().isEmpty()) {
            entity.setPassword(response.getPassword());
        }
        if (response.getUsername() != null && !response.getUsername().trim().isEmpty()) {
            entity.setUsername(response.getUsername());
        }
        if (response.getRole() != null) {
            entity.setRole(response.getRole());
        }
    }
}