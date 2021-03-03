package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }


    @RequestMapping(value = "/dept/get/{id}",method = RequestMethod.GET)
   @HystrixCommand(fallbackMethod = "processHystrix_Get")//当当前方法出错或者超时异常时，hystrix熔断服务并执行设置的可执行备用方法
    public Dept get(@PathVariable("id")Long id){
        Dept dept = deptService.get(id);
        if(dept==null){
            throw new RuntimeException("该ID："+id+"没有对应的信息");
        }else{
            return dept;
        }

    }

    public Dept processHystrix_Get(@PathVariable("id")Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("该ID："+id+"没有对应的信息,null--@HystrixCommond")
                .setDb_source("no this database in MySQL");
    }


    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> list(){
        return deptService.list();
    }


    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
    public Object discovery(){
        List<String>  list = discoveryClient.getServices();
        System.out.println("********"+list);

        List<ServiceInstance> srvlist = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element:srvlist) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }
        return this.discoveryClient;
    }

}
