package uz.brb.transaction_with_aop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.brb.transaction_with_aop.entity.Item;
import uz.brb.transaction_with_aop.entity.OutboxEvent;
import uz.brb.transaction_with_aop.producer.ItemProducer;
import uz.brb.transaction_with_aop.repository.ItemRepository;
import uz.brb.transaction_with_aop.repository.OutboxEventRepository;

import java.util.List;
import java.util.Optional;

import static uz.brb.transaction_with_aop.config.RabbitMQConfig.ITEM_QUEUE;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemProducer itemProducer;
    private final OutboxEventRepository outboxEventRepository;
    private final RabbitTemplate rabbitTemplate;

    // Create or Update Item
    @Transactional
    public Item saveItem(Item item) {
        itemProducer.send(item);
        Item saved = itemRepository.save(item);

        // Outbox event yaratish
        OutboxEvent event = new OutboxEvent();
        event.setEventType("ITEM_PURCHASED");
        event.setPayload(item.getId().toString());
        event.setProcessed(false);
        outboxEventRepository.save(event);

        return saved;
    }

    @Scheduled(fixedDelay = 5000)
    public void pushEventsToRabbitMQ() {
        List<OutboxEvent> events = outboxEventRepository.findByProcessedFalse();
        for (OutboxEvent event : events) {
            rabbitTemplate.convertAndSend(ITEM_QUEUE, event.getPayload());
            event.setProcessed(true);
            outboxEventRepository.save(event);
        }
    }

    // Get all Items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Get single Item by ID
    public Optional<Item> getItemById(long id) {
        return itemRepository.findById(id);
    }

    // Delete Item by ID
    @Transactional
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }
}