package uz.brb.transaction_with_aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brb.transaction_with_aop.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}