package uz.brb.transaction_with_aop.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import uz.brb.transaction_with_aop.entity.Item;

import static uz.brb.transaction_with_aop.config.RabbitMQConfig.EXCHANGE;
import static uz.brb.transaction_with_aop.config.RabbitMQConfig.ROUTING_KEY;

@Component
@RequiredArgsConstructor
public class ItemProducer {
    private final RabbitTemplate rabbitTemplate;

    public void send(Item item) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, item);
        System.out.println("Message sent: " + item);
    }
}
