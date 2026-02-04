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
        user1.setName("Ali");
        userRepository.save(user1);

        Users user2 = new Users();
        user2.setName("Vali");
        userRepository.save(user2);

        // Xato chiqaramiz va xato borligi uchun hech qaysi biri bazaga saqlanmaydi
        if (true) {
            throw new RuntimeException("❌ Xatolik yuz berdi!");
        }

        Users user3 = new Users();
        user3.setName("Hasan");
        userRepository.save(user3);
    }
}
