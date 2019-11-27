package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 实体类
 */
@Data
public class Test implements Serializable {

    private String id;
    private String Content;

    public Test(String id, String content) {
        this.id = id;
        Content = content;
    }

    public Test() {
    }
}
