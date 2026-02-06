package uz.brb.transaction_with_aop.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Account extends BaseEntity {
    private String holderName;
    private double balance;
}