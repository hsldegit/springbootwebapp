package guru.springframework;

import guru.springframework.configuration.EnableLogFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = { "guru.springframework" })
//@MapperScan({"guru.springframework.dao","guru.springframework.dao.custom"})

@EnableLogFilter
public class SpringBootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
