package com.lyd;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * @ClassName: MyBatisConfig
 * @Description: Spring Boot集成Mybatis的基本入口
 * @author S1ow
 * @date 2017年5月23日 上午9:59:56
 *
 */
@Configuration
@MapperScan(basePackages="com.lyd")
public class MyBatisConfig {


    @Value( "${jdbc.driverClassName}" )
    String driverClassName;
    @Value( "${jdbc.url}" )
    String url;
    @Value( "${jdbc.username}" )
    String username;
    @Value( "${jdbc.password}" )
    String password;
    @Value("${mybatis.typeAliasesPackage}")
    String typeAliasesPackage;
    @Value("${mybatis.mapperLocations}")
    String mapperLocations;

    /**
     * @Title: getDataSource
     * @Description: 创建数据源
     * @param @return
     * @return DataSource
     * @throws
     */
    @Bean
    public DataSource getDataSource(){
        Properties props = new Properties();
        props.put("driverClass", driverClassName);
        props.put("url", url);
        props.put("username", username);
        props.put("password", password);
        try {
            return DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: sqlSessionFactory
     * @Description:  根据数据源创建SqlSessionFactory
     * @param @param ds
     * @param @return
     * @param @throws Exception
     * @return SqlSessionFactory
     * @throws
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory( @Qualifier("getDataSource") DataSource ds) throws Exception{
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(ds);
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        sfb.setTypeAliasesPackage(typeAliasesPackage);
//        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return sfb.getObject();
    }
}
