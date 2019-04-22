package xyz.yangkai.spring.demo.common.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//请求
public class DemoReq {
    @NotNull(message = "username不能为空")
    private String username;
    @NotNull(message = "password不能为空")
    private String password;
}
