package xyz.yangkai.spring.demo.common.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//请求
public class UpdateUserReq {
    @NotNull(message = "id不能为空")
    private Integer id;
    private String username;
    private String password;
}
