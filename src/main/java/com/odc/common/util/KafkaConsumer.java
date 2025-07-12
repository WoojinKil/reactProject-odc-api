package com.odc.common.util;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	
    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void consume(String message) {
        //System.out.println("Consumed message: " + message);
    }
}
