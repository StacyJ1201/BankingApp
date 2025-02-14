package com.synergisticit.repository;

import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("roleRepository2")
public class RoleRepository2 {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Role findRoleById(Long roleId){
        String sql = "Select * from role where role_id = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Role.class), roleId);
    }

    public Role saveRole(Role role){
        String sql = "INSERT INTO role (role_name) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, role.getRoleName());
            return ps;
        }, keyHolder);

        role.setRoleId(keyHolder.getKey().longValue());

        return role;
    }

    public List<Role> findAllRoles(){
        String sql = "SELECT * FROM role";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
    }

    public Role updateRoe(Role role){
        String sql = "UPDATE role SET role_name = ? where role_id = ?";

        jdbcTemplate.update(sql,
                role.getRoleName(),
                role.getRoleId()
        );

        return role;
    }

    public void deleteRole(Long roleId){
        String sql = "DELETE FROM role where role_id = ?";

        jdbcTemplate.update(sql, roleId);
    }
}
