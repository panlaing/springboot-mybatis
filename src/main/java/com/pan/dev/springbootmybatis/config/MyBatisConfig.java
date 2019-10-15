package com.pan.dev.springbootmybatis.config;


import com.pan.dev.springbootmybatis.util.AutoEnumTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * @author liang.pan
 * @Description: TODO
 * @date 2019/10/9 11:39
 */
@Configuration
@MapperScan(basePackages = {"com.pan.dev.springbootmybatis.dao"},
        sqlSessionFactoryRef = MyBatisConfig.SESSIONFACTORY_NAME)
public class MyBatisConfig {

    /**
     * SqlSessionFactory名称.
     */
    public final static String SESSIONFACTORY_NAME = "sqlSessionFactory";
    /**mapper包路径，必须与其他SqlSessionFactory-mapper路径区分.*/
    /**
     * mapper.xml文件路径，必须与其他SqlSessionFactory-mapper路径区分.
     */
    public final static String MAPPER_XML_PATH = "classpath*:mapper/*.xml";


    @Bean(name = "dataSource")
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create()
                .build();
    }

    //默认Bean首字母小写，简化配置
    //将SqlSessionFactory作为Bean注入到Spring容器中，成为配置一部分。
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        // 取得类型转换注册器
        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
        // 注册默认枚举转换器
        typeHandlerRegistry.setDefaultEnumTypeHandler(AutoEnumTypeHandler.class);

        return sqlSessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager());
    }
}
