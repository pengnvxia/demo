package xyz.yangkai.spring.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ppk {
    private Integer id;
    private String public_key;
    private String private_key;
    private Integer version;
}
