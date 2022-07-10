package com.cursosdedesarrollo.springbootremoting;

import com.cursosdedesarrollo.springbootremoting.hessian.HelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@SpringBootApplication
public class SpringBootRemotingApplication {

    @Bean
    RmiProxyFactoryBean rmiProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(HelloWorldRMI.class);
        bean.setServiceUrl("rmi://localhost:1099/helloworldrmi");

        return bean;
    }

    @Bean
    public HessianProxyFactoryBean hessianInvoker() {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8080/hellohessian");
        invoker.setServiceInterface(HelloWorld.class);
        return invoker;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRemotingApplication.class, args);
        HelloWorldRMI helloWorldRMI = context.getBean(HelloWorldRMI.class);

        System.out.println("================Client Side ========================");

        System.out.println(helloWorldRMI.sayHelloRmi("Sajal"));

        System.out.println("========Client Side===============");
        HelloWorld helloWorld =     context.getBean(HelloWorld.class);
        System.out.println(helloWorld.sayHelloWithHessian("Sajal"));

        System.out.println("========Client Side===============");
        HelloWorld helloWorld2 =     context.getBean(HelloWorld.class);
        System.out.println(helloWorld.sayHelloWithHessian("Sajal"));
    }

}
