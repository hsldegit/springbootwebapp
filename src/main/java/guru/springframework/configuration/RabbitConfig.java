package guru.springframework.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangshilu
 * @date 2018/12/11 14:49
 * @description
 */

@Configuration
public class RabbitConfig {

    @Bean
    public Queue QueueA() {
        return new Queue("hello");
    }

    @Bean
    public Queue QueueB() {
        return new Queue("helloObj");
    }

    /**
     * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 要通过routingKey将queue绑定到exchange
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingFanoutExchangeA(Queue QueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(QueueA).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchangeB(Queue QueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(QueueB).to(fanoutExchange);
    }


    @Bean
    Binding bindingExchangeA(Queue QueueA, TopicExchange exchange) {
        return BindingBuilder.bind(QueueA).to(exchange).with("keyA");
    }

    @Bean
    Binding bindingExchangeB(Queue QueueB, TopicExchange fanoutExchange) {
        return BindingBuilder.bind(QueueB).to(fanoutExchange).with("keyB");
    }


}
