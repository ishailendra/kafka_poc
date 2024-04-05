package dev.techsphere.kafka.service;

import dev.techsphere.kafka.model.Book;
import dev.techsphere.kafka.model.Student;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "BOOK1", containerFactory="kafkaListenerContainerFactory2")
    public void bookListener(@Payload Book book,  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        System.out.println("Book:: Timestamp:: " + new Date(ts) + "TOPIC:: " + topic + "  Book Received:: " + book);
    }

    @KafkaListener(topics = "STUDENT", containerFactory="kafkaListenerContainerFactory2")
    public void studentListener(@Payload Student student, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        System.out.println("Student::  Timestamp:: " + new Date(ts) + "TOPIC:: " + topic + "  Book Received:: " + student);
    }

    @KafkaListener(topics = { "STUDENTS", "BOOK2" }, containerFactory="kafkaListenerContainerFactory2")
    public void genericListener(@Payload ConsumerRecord record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                    @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        System.out.println(record.value());
        System.out.println(record.value().getClass());
        System.out.println("Generic::  Timestamp:: " + new Date(ts) + "TOPIC:: " + topic + "  Object Received:: " + record);
    }
}
