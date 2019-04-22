package mapper;

import domain.Session;
import domain.Users;

@Mapper
public interface SessionMapper {
    Users getUserBySessionId(String session_id);
}
