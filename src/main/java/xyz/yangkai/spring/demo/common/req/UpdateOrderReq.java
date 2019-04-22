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
public class UpdateOrderReq {
    @NotNull(message = "id不能为空")
    private Integer id;
    private Integer money;
    private String goods;
    private Integer users_id;
}
