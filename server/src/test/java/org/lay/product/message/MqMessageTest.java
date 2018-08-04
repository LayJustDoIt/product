package org.lay.product.message;

import org.junit.Test;
import org.lay.product.ProductApplicationTests;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Create by Lay
 * 2018-03-25 11:35
 */
@Component
public class MqMessageTest extends ProductApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送MQ消息
     */
    @Test
    public void sendMessage() {
        amqpTemplate.convertAndSend("myQueue", "now ==== " + new Date());
    }

    @Test
    public void sendMessageOfOrder() {
        amqpTemplate.convertAndSend("myOrder", "fruit", "now ==== " + new Date());
    }

    @Test
    public void sendMessageOfComputer() {
        amqpTemplate.convertAndSend("myOrder", "computer", "now ==== " + new Date());
    }

}