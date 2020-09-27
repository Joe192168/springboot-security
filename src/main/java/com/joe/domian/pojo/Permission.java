package com.joe.domian.pojo;

import lombok.Data;

@Data
public class Permission {

    private Integer id;
    private String code;
    private String url;
    private String method;
    private String description;

}