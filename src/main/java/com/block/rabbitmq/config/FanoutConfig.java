package com.block.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangrongsong
 * @title: FanoutConfig
 * @projectName rabbitmq-study
 * @description: TODO
 * @date 2021-10-14 11:45
 */
@Configuration
public class FanoutConfig {

    /**
     * 配置广播交换机，名称为block.com
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("block.com");
    }

    /**
     * 配置一个fanout.queue1的队列
     * @return
     */
    @Bean()
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }

    /**
     * 配置一个fanout.queue2的队列
     * @return
     */
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    /**
     * 绑定队列1到交换机,名字跟上面一样
     * @return
     */
    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    /**
     * 绑定队列2到交换机,名字跟上面一样
     * @return
     */
    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

}
