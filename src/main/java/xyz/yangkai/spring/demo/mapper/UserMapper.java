package xyz.yangkai.spring.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.yangkai.spring.demo.domain.Ppk;
import xyz.yangkai.spring.demo.domain.Session;
import xyz.yangkai.spring.demo.domain.Users;
@Mapper
public interface UserMapper {
    void save(Users users);
    void savePpk(Ppk ppk);
    Ppk getPublicPrivateKey();
    Ppk getPrivateKeyByVersion(Integer version);
    Integer getVersion();
    Integer getUserByUserName(String username);
    Users getUserByUsernameAndPassword(Users users);
}
