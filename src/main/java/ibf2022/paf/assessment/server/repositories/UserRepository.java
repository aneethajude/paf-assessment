package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3

@Repository
public class UserRepository {

    public static final String SQL_FIND_CUSTOMER_BY_NAME ="select * from user where username = ?";

    @Autowired
	private JdbcTemplate jdbcTemplate;

    //use the following method as provided
    // public Optional<User> findUserByUsername(String username){
    //     SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_CUSTOMER_BY_NAME, username);
    //     if(!rs.next())
    //     return Optional.empty();

    //  return Optional.of(toUser(rs));
        
    // }
}
