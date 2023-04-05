package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3

@Repository
public class UserRepository {

   
    public static final String SQL_FIND_CUSTOMER_BY_NAME ="select * from user where username = ?";
    public static final String SQL_INSERT_CUSTOMER ="insert into user(user_id, username, name) values(?, ?, ?)";
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public Optional<User> findUserByUsername(String username){
        User user = new User();
        boolean isExist = false;
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_CUSTOMER_BY_NAME, username);        
        while (rs.next()) { 
            user.setUserId(rs.getString("user_id"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            isExist = true;
        }

        if(!isExist)
            return Optional.empty();

        return Optional.of(user);
    }

    public String insertUser(User user){
        String cUserId = null;
        UUID uuid = UUID.randomUUID();
        cUserId = uuid.toString().substring(0, 8);
        jdbcTemplate.update(SQL_INSERT_CUSTOMER, cUserId, user.getUsername(), user.getName());
        return cUserId;
    }
}
