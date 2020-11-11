package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create (Payment payment) {
        log.info("********消费者插入");
        return restTemplate.postForObject(PAYMENT_URL+ "/payment/create/", payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/createForEntity")
    public CommonResult<Payment> create2 (Payment payment) {
        log.info("********消费者插入");
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL+ "/payment/create/", payment,CommonResult.class);
        log.info("233"+entity.getHeaders());
        return entity.getBody();
    }


    @GetMapping("/consumer/payment/get/{id}")
    public  CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("********消费者查询");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public  CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        log.info("********消费者查询");
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            log.info("233"+ entity.getHeaders());
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"查询失败");
        }

    }
}
