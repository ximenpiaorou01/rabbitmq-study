package com.block.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangrongsong
 * @title: MQConfig
 * @projectName rabbitmq-study
 * @description: TODO
 * @date 2021-10-14 01:03
 */
@Configuration
public class MQConfig {

    @Bean
    public Queue rabbitQueue(){
        return new Queue("simple.queue",true);
    }

    /**
     * Spring的消息对象处理是由org.springframwork.amqp.support.converter.MessageConverter
     * 来处理。而默认实现是由SimpleMessageConverter，基于jdk的ObjectOutputStream完成序列化，不安全和效率不高，
     * 如果要修改只需定义一个MessageConverter类型的Bean即可。
     * <dependency>
     *             <groupId>com.fasterxml.jackson.core</groupId>
     *             <artifactId>jackson-databind</artifactId>
     *             <version>2.10.1</version>
     *         </dependency>
     * @return
     */
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
