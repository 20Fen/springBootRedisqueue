package com.example.demo.production;


import com.example.demo.model.Test;
import com.example.demo.util.JedisUtil;
import com.example.demo.util.ObjectUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 测试
 */
@Component
@RequestMapping("/in")
public class Producer extends Thread{

    public static String redisKey = "key";

    @RequestMapping("/out")
    private  void pop() throws Exception {
        byte[] bytes = JedisUtil.rpop(redisKey);
        Test msg = (Test) ObjectUtil.bytesToObject(bytes);
        if(msg != null){
            System.out.println(msg.getId()+"   "+msg.getContent());
        }
    }
    @RequestMapping("/in")
    private  void init() throws Exception {
        Test msg1 = new Test("1", "内容1");
        JedisUtil.lpush(redisKey, msg1);
        Test msg2 = new Test("2", "内容2");
        JedisUtil.lpush(redisKey, msg2);
        Test msg3 = new Test("3", "内容3");
        JedisUtil.lpush(redisKey, msg3);
    }

}