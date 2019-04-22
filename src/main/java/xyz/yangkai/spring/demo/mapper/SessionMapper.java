package xyz.yangkai.spring.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.yangkai.spring.demo.domain.Session;
import xyz.yangkai.spring.demo.domain.Users;

@Mapper
public interface SessionMapper {
    void saveSession(Session session);
    Users getUserBySessionId(String session_id);
    void deleteSession(String session_id);
}
