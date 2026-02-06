package uz.brb.transaction_with_aop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.brb.transaction_with_aop.entity.Users;
import uz.brb.transaction_with_aop.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository userRepository;

    @Transactional
    public void createUsers() {
        Users user1 = new Users();
        user1.setFullName("Ali Aliyev");
        userRepository.save(user1);

        Users user2 = new Users();
        user2.setFullName("Vali Valiyev");
        userRepository.save(user2);

        // Xato chiqaramiz va xato borligi uchun hech qaysi biri bazaga saqlanmaydi
        if (true) {
            throw new RuntimeException("❌ Xatolik yuz berdi!");
        }

        Users user3 = new Users();
        user3.setFullName("Hasan Hasanov");
        userRepository.save(user3);
    }
}
