package guru.springframework.druid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangshilu
 * @date 2019/3/12 15:22
 * @description
 */
@Configuration
public class DruidAutoConfiguration {

    /**
     * 注册druid数据源统计servlet， 用于监控数据源页面
     * <p>
     * 访问地址：http://ip:port/druid/index.html
     *
     * @return
     */
    @Bean(name = "druidServlet")
    @ConditionalOnProperty(name = "druid.datasource.enable-monitor", havingValue = "true")
    @ConditionalOnMissingBean(name = "druidServlet")
    public ServletRegistrationBean registerDruidStatServlet() {
        return new ServletRegistrationBean(new com.alibaba.druid.support.http.StatViewServlet(), "/druid/*");
    }
}
