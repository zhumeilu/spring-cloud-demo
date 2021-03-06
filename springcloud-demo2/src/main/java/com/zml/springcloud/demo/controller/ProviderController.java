package com.zml.springcloud.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: zhumeilu
 * Date: 2017/7/10
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class ProviderController {

    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        List<String> services = client.getServices();
        String description = client.description();
        List<ServiceInstance> instances = client.getInstances("compute-service");
        logger.info("services:"+services+"----description:"+description+"-------instances"+instances);
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
    @RequestMapping(value = "/add2" ,method = RequestMethod.POST)
    public Integer add2(@RequestParam Integer a, @RequestParam Integer b) {
        Integer r = a + b;
        return r;
    }
}
