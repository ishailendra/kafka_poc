package dev.techsphere.kafka.service;

import dev.techsphere.kafka.model.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "BOOK1", containerFactory="kafkaListenerContainerFactory")
    public void bookListener(@Payload Book book,  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        System.out.println("Timestamp:: " + new Date(ts) + "TOPIC:: " + topic + "  Book Received:: " + book);
    }
}
