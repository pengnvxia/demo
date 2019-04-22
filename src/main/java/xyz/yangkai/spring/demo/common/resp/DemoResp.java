package xyz.yangkai.spring.demo.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//响应格式
public class DemoResp {
    private Integer statusCode;
    private String msg;
    private String sessionId;
    private Integer userId;
}
