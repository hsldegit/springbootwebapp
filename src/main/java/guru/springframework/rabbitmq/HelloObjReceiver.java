package guru.springframework.rabbitmq;


import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import guru.springframework.domain.entity.Product;
import guru.springframework.services.ProductService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author huangshilu
 * @date 2018/12/11 15:08
 * @description
 */
@Component
@RabbitListener(queues = "helloObj")
public class HelloObjReceiver {

    @Autowired
    private ProductService productService;

    @RabbitHandler
    public void process(String hello, Channel channel, Message message) throws  Exception{
        System.out.println("HelloObjReceiver收到  : " + hello + "收到时间" + new Date());
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            Product product = JSONObject.parseObject(hello, Product.class);
            productService.saveProduct(product);
            System.out.println("成功保存从MQ拿到的product数据");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("receiver fail");
        }

    }


}
