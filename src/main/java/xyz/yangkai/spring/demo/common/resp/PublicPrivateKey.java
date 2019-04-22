package xyz.yangkai.spring.demo.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class PublicPrivateKey {
    private Integer id;
    private String publicKey;
    private Integer version;
}
