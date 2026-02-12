package uz.brb.transaction_with_aop.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static uz.brb.transaction_with_aop.config.RabbitMQConfig.ITEM_QUEUE;

@Service
public class NotificationService {

    @RabbitListener(queues = ITEM_QUEUE)
    public void sendNotification(String itemId) {
        System.out.println("Notification: Item purchased with ID " + itemId);
    }
}
