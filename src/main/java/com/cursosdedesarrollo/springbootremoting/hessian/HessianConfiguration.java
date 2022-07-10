package com.cursosdedesarrollo.springbootremoting.hessian;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class HessianConfiguration {

    @Bean(name = "/hellohessian")
    RemoteExporter sayHelloServiceHessian() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new HelloWorldImpl());
        exporter.setServiceInterface(HelloWorld.class);
        return exporter;
    }

}
