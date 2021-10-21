package com.block.rabbitmq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wangrongsong
 * @title: SpringRabbitListener
 * @projectName rabbitmq-study
 * @description: TODO
 * @date 2021-10-14 00:38
 */
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(Map<String,Object> map){

        System.out.println("消费到服务端发送的消息："+map);
        //处理业务逻辑
    }


    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue_1(Map<String,Object> map){

        System.out.println("消费到服务端发送的消息："+map+"_"+ LocalTime.now());
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue_2(Map<String,Object> map){

        System.err.println("消费到服务端发送的消息："+map+"_"+ LocalTime.now());
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue_1(String message){

        System.out.println("消费到fanout.queue1发送的消息："+message+"_"+ LocalTime.now());
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue_2(String message){

        System.out.println("消费到fanout.queue2发送的消息："+message+"_"+ LocalTime.now());
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * directExchange的方式路由
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue1"),
                    exchange = @Exchange(name = "block.direct",type = ExchangeTypes.DIRECT),
                    key = {"red","blue"}))
    public void listenDirectQueue_1(String message){

        System.out.println("消费到direct.queue1发送的消息："+message+"_"+ LocalTime.now());
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * directExchange的方式路由
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "block.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}))
    public void listenDirectQueue_2(String message){

        System.out.println("消费到direct.queue2发送的消息："+message+"_"+ LocalTime.now());
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * china.#是一个通配符，表示只要是china.开通的消息都感兴趣
     * *表示1个，#表示0个或多个
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="topic.queue1"),
            exchange =@Exchange(name="block.topic",type = ExchangeTypes.TOPIC) ,
            key = "china.#"
    ))
    public void listTopicExchange1(String message){
        System.out.println("消费者接收到topic.queue1的消息:"+message);
    }

    /**
     * #.news#是一个通配符，表示只要是.news结尾开通的消息都感兴趣
     * *表示1个，#表示0个或多个
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="topic.queue2"),
            exchange =@Exchange(name="block.topic",type = ExchangeTypes.TOPIC) ,
            key = "#.news"
    ))
    public void listTopicExchange2(String message){
        System.out.println("消费者接收到topic.queue2的消息:"+message);
    }

}
