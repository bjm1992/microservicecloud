package com.atguigu.springcloud.entities;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {
    private long deptno; //主键
    private String dname; //部门名称
    private String db_source;  //来自哪个数据库，微服务架构可一个服务对应一个数据库，同一个信息可被存储到到不同数据库中


}
