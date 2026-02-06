package uz.brb.transaction_with_aop.entity;

import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Item extends BaseEntity {
    private long orderId;
    private String name;
    private String category;
    private int qty;
    private String sourceSystem;

}