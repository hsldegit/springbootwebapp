package guru.springframework.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties({MyDruidDataSourceProperties.class})
@MapperScan(value = {"guru.springframework.dao"}, sqlSessionFactoryRef = "mySqlSessionFactory")
@ConditionalOnProperty(name = "druid.datasource.url", matchIfMissing = false)
public class MyDruidDataSourceConfiguration {

    static final String MAPPER_LOCATION = "classpath*:sqlmap/*Mapper.xml";

    @Autowired
    private MyDruidDataSourceProperties myDataSourceProperties;

    @Bean(name = "myDruidDataSource", initMethod = "init", destroyMethod = "close")
    @ConditionalOnMissingBean(name = "myDruidDataSource")
    public DruidDataSource shopDruidDataSourc() throws Exception {
        DruidDataSource result = new DruidDataSource();
        result.setName(myDataSourceProperties.getName());
        result.setUrl(myDataSourceProperties.getUrl());
        result.setUsername(myDataSourceProperties.getUsername());
        result.setPassword(myDataSourceProperties.getPassword());
//        result.setConnectionProperties("config.decrypt=true;config.decrypt.key="
//                + myDataSourceProperties.getPwdPublicKey());
        result.setFilters("config");
        result.setMaxActive(myDataSourceProperties.getMaxActive());
        result.setInitialSize(myDataSourceProperties.getInitialSize());
        result.setMaxWait(myDataSourceProperties.getMaxWait());
        result.setMinIdle(myDataSourceProperties.getMinIdle());
        result.setTimeBetweenEvictionRunsMillis(myDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        result.setMinEvictableIdleTimeMillis(myDataSourceProperties.getMinEvictableIdleTimeMillis());
        result.setValidationQuery(myDataSourceProperties.getValidationQuery());
        result.setValidationQueryTimeout(myDataSourceProperties.getValidationQueryTimeout());
        result.setTestWhileIdle(myDataSourceProperties.isTestWhileIdle());
        result.setTestOnBorrow(myDataSourceProperties.isTestOnBorrow());
        result.setTestOnReturn(myDataSourceProperties.isTestOnReturn());
        result.setPoolPreparedStatements(myDataSourceProperties.isPoolPreparedStatements());
        result.setMaxOpenPreparedStatements(myDataSourceProperties.getMaxOpenPreparedStatements());

        if (myDataSourceProperties.isEnableMonitor()) {
            StatFilter filter = new StatFilter();
            filter.setLogSlowSql(myDataSourceProperties.isLogSlowSql());
            filter.setMergeSql(myDataSourceProperties.isMergeSql());
            filter.setSlowSqlMillis(myDataSourceProperties.getSlowSqlMillis());
            List<Filter> list = new ArrayList<Filter>();
            list.add(filter);
            result.setProxyFilters(list);
        }
        return result;
    }

    @Bean(name = "myTransactionManager")
    @ConditionalOnMissingBean(name = "myTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("myDruidDataSource") DruidDataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }

    @Bean(name = "myTransactionTemplate")
    @ConditionalOnMissingBean(name = "myTransactionTemplate")
    public TransactionTemplate transactionTemplate(@Qualifier("myTransactionManager") PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

    @Bean(name = "mySqlSessionFactory")
    @ConditionalOnMissingBean(name = "mySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("myDruidDataSource") DruidDataSource druidDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(druidDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();

    }
}
