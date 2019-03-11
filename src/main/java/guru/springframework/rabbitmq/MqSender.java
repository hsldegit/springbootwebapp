package guru.springframework.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huangshilu
 * @date 2018/12/11 14:56
 * @description
 */
@Service
public class MqSender implements RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送字符串消息.
     *
     * @param exchange
     * @param routingKey
     * @param context
     */
    public void sendMsg(String exchange, String routingKey, String context) {
        System.out.println("Sender发送内容 : " + context);
        this.rabbitTemplate.setReturnCallback(this);
        //一个rabbitTemplate只能设置一次ConfirmCallback 所以尽量走一个发送方法 对象可以转成JSON在发出去
        //或者借用Spring的lookup方法 每次生成一个新的rabbitTemplate，但是没有必要

        CorrelationData correlationDataSend = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            //通过实现ConfirmCallBack接口，消息发送到交换器Exchange后触发回调。
            if (!ack) {
                //可以发送之前根据uuid把消息丢到redis里 失败的时候取出来在处理
                System.out.println("sender消息发送失败" + cause + correlationData.toString() + "消息id=" + correlationData.getId());
            } else {
                System.out.println("sender 消息发送成功 ");
            }
        });
        this.rabbitTemplate.convertAndSend(exchange, routingKey, context, correlationDataSend);
    }


    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        //如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
        System.out.println("sender return success" + message.toString() + "===" + replyText + "===" + exchange + "===" + routingKey);
    }

//这里内容放到rabbitTemplate.setConfirmCallback
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        System.out.println("sender success");
//    }


}
