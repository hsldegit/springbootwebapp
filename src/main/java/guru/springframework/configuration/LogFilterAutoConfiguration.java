package guru.springframework.configuration;

import guru.springframework.filter.LogFilter;
import guru.springframework.filter.LogFilterRegistrationBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangshilu
 * @date 2020/3/22 19:04
 * @description
 */

/**
 *
 *@Configuration 通常与@Bean相配合，
 * 使用这两个注解可以创建一个简单的Spring配置类，
 * 代替相应的xml配置文件。@ConditionalOnClass声明只有当某个或某些class位于类路径上，
 * 才会实例化一个Bean。如上述代码中只有当LogFilterRegistrationBean和LogFilter的class在类路径上，
 * LogFilterAutoConfiguration配置类才会生效。
 */
@Configuration
@ConditionalOnClass({LogFilterRegistrationBean.class, LogFilter.class})
public class LogFilterAutoConfiguration {

    /**
     * 添加@Bean注解的方法将返回一个对象，该对象会被注册为Spring上下文中的Bean。
     * @ConditionalOnMissingBean 声明仅仅在当前Spring上下文中不存在某个对象时，
     * 才会实例化一个Bean。上述代码中，当LogFilterRegistrationBean不存在于Spring
     * 上下文时，才会创建LogFilterRegistrationBean的Bean并注入到Spring上下文中。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(LogFilterRegistrationBean.class)
    public LogFilterRegistrationBean logFilterRegistrationBean(){
        return new LogFilterRegistrationBean();
    }
}
