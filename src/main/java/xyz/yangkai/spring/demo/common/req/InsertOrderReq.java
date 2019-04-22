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
public class InsertOrderReq {
    @NotNull(message = "money不能为空")
    private Integer money;
    @NotNull(message = "goods不能为空")
    private String goods;
    @NotNull(message="users_id不能为空")
    private Integer users_id;
}
