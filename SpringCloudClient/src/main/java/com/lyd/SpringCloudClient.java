package com.lyd;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableAutoConfiguration
public class SpringCloudClient {

    public static void main( String[] args ) {
        SpringApplication.run( SpringCloudClient.class, args );
    }

    @Value("${server.port}")
    String port;

    @Resource
    private UserDao userDao;

    @GetMapping(value = "/hi")
    public String hi( ) {
        return port;
    }


    @ApiOperation(value = "测试用接口", notes = "测试专用")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String", paramType = "body")
    })// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
    @PostMapping( value = "user")
    public Response insertUser(@RequestBody String name ) {
        System.out.println(name+"---------------" );
        userDao.insert( name );
        return new Response( ).success( "插入成功" );
    }
}
