package xyz.yangkai.spring.demo.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UpdateOrderResp {
    private Integer statusCode;
    private String msg;
}
