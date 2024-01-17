package com.poc.function.repository;

import com.poc.function.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static final String GET_USERS_SQL = "SELECT * FROM users";
    public static final String INSERT_USER_SQL = "INSERT INTO users(id, name, email, phone, address) VALUES(:id, :name, :email, :phone, :address)";

    public List<User> getUsers() {
            String sql = "SELECT * FROM users";
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapUser(rs));
        }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(BigInteger.valueOf(rs.getLong("id")).toString());
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setAddress(rs.getString("address"));
        return user;
    }



    public void saveUser(User user) {
        System.out.println("id: " + user.getId());
        System.out.println("name: " + user.getName());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", BigInteger.valueOf(Long.parseLong(user.getId())));
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("phone", user.getPhone());
        parameters.put("address", user.getAddress());

        jdbcTemplate.update(INSERT_USER_SQL, parameters);
    }
    public Long getNextSequenceId() {
        return jdbcTemplate.queryForObject("SELECT nextval('users_id_seq')", Long.class);
    }
}
