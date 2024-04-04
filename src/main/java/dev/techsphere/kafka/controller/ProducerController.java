package dev.techsphere.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dev.techsphere.kafka.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class ProducerController {

    @Autowired
//    private KafkaTemplate<String, Book> template;
    private KafkaTemplate<String, String> template;

//    private static final String TOPIC = "BOOKS";

    @PostMapping("/publish/{topic}")
    public String sendMessage(@RequestBody Book book, @PathVariable String topic) throws ExecutionException, InterruptedException, JsonProcessingException {
//        CompletableFuture<SendResult<String, Book>> resultFuture = template.send(topic, book);
//        resultFuture.whenComplete((result, ex) -> {
//            if (ex == null) {
//                System.out.println("=====  SUCCESS  =====");
//                System.out.println(result.getRecordMetadata().toString());
//                System.out.println(result.getProducerRecord().toString());
//            } else {
//                System.err.println("=====  FAILURE  =====");
//                ex.printStackTrace();
//            }
//        });

        CompletableFuture<SendResult<String, String>> resultFuture = template.send(topic, new Gson().toJson(book));
        resultFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("=====  SUCCESS  =====");
                System.out.println(result.getRecordMetadata().toString());
                System.out.println(result.getProducerRecord().toString());
            } else {
                System.err.println("=====  FAILURE  =====");
            }
        });


        return "SUCCESS";
    }
}
