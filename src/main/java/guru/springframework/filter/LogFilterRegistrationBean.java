package guru.springframework.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * @author huangshilu
 * @date 2020/3/22 18:58
 * @description
 */
public class LogFilterRegistrationBean extends FilterRegistrationBean<LogFilter> {

    public LogFilterRegistrationBean(){
        super();
        this.setFilter(new LogFilter()); //添加LogFilter过滤器
        this.addUrlPatterns("/*"); // 匹配所有路径
        this.setName("LogFilter"); // 定义过滤器名
        this.setOrder(1); // 设置优先级
    }


}
