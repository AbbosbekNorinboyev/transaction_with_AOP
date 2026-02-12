package uz.brb.transaction_with_aop.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "transaction-queue";
    public static final String ITEM_QUEUE = "item-queue";
    public static final String EXCHANGE = "transaction-exchange";
    public static final String ROUTING_KEY = "transaction-routing-key";

    // 1. Queue yaratish
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    public Queue itemQueue() {
        return new Queue(ITEM_QUEUE, true);
    }

    // 2. Exchange yaratish
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    // 3. Binding (Queue + Exchange + RoutingKey)
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // 4. JSON converter
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 5. RabbitTemplate -> JSON converter bilan
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter); // MUHIM QISM
        return template;
    }
}