package xyz.yangkai.spring.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    //类型为
    private Integer id;
    private String username;
    private String password;
    private Integer isDel;
    private Integer key_id;
}
