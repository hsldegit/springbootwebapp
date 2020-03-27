package guru.springframework.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author huangshilu
 * @date 2020/3/22 17:41
 * @description
 */
public class LogFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LogFilter init..");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("uri {} is working.", request.getRequestURI());
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("logFilter destroy...");
    }
}
