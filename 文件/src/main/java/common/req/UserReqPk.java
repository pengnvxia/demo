package xyz.yangkai.spring.demo.common.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReqPk {
    private String username;
    private String passward;
    private String public_key;
}
