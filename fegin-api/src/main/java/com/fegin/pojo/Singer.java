package com.fegin.pojo;


import lombok.Data;
import java.util.Date;


@Data
public class Singer {

    private Integer singerId;

    private String singerName;

    private Byte sex;

    private String pic;

    private Date birth;

    private String location;

    private String introduction;

}
