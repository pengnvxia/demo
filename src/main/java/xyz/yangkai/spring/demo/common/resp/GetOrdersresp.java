package xyz.yangkai.spring.demo.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class GetOrdersresp {
    private Integer id;
    private Integer money;
    private String goods;
    private Integer users_id;
}
