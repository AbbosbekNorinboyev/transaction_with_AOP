package uz.brb.transaction_with_aop.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import uz.brb.transaction_with_aop.entity.Item;

import static uz.brb.transaction_with_aop.config.RabbitMQConfig.QUEUE;

@Service
public class ItemConsumer {

    @RabbitListener(queues = QUEUE)
    public void receive(Item item) {
        System.out.println("Received item: " + item);
    }
}
