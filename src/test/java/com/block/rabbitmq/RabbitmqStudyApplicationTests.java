package com.block.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitmqStudyApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {

        for(int i=1;i<=50;i++){
            rabbitTemplate.convertAndSend("simple.queue",i);
        }
    }


    @Test
    void contextLoads11() {
        Map<String,Object> map=new HashMap<>();
        map.put("name","柳岩");
        map.put("age",20);
        rabbitTemplate.convertAndSend("simple.queue",map);

    }


    @Test
    public void testSendFanoutExchange(){
        //交换机名称
        String exchange="block.com";
        String message="hello,every one!";
        rabbitTemplate.convertAndSend(exchange,"",message);
    }

    @Test
    public void testSendDirectExchange(){
        //交换机名称
        String exchange="block.direct";
        String message="hello,hello!";
        rabbitTemplate.convertAndSend(exchange,"red",message);
    }

    @Test
    public void testSendTopicExchange(){
        //交换机名称
        String exchange="block.topic";
        String message="今天天气不错！";
        rabbitTemplate.convertAndSend(exchange,"china.weather",message);
    }
}
