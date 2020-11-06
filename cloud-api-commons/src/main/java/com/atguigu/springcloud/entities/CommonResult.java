package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T>
{
    private Integer code;
    private String message;
    //返回的实体
    private  T  data;

    public CommonResult (Integer code, String message){

    }
}
