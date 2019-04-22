package xyz.yangkai.spring.demo.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderResp {
    private Integer statusCode;
    private String msg;
}
