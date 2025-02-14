package com.synergisticit.repository;

import com.synergisticit.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleRepository1")
public class RoleRepository1 {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public Long findMaxRoleId(){
        String sql = "SELECT MAX(r.role_id) FROM role r";

        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Long.class);
    }

    public Role saveRole(Role role){
        String sql = "INSERT INTO role(role_id, role_name) VALUES (:roleId, :roleName)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("roleId", role.getRoleId())
                .addValue("roleName", role.getRoleName());

        jdbcTemplate.update(sql, params);

        return role;
    }

    public List<Role> findAllRoles(){
        String sql = "SELECT * FROM role";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
    }

    public Role findRoleById(Long roleId){
        String sql = "SELECT * FROM role WHERE role_id = :roleId";

        return jdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource("roleId", roleId),
                new BeanPropertyRowMapper<>(Role.class));

    }

    public void updateRoleById(Long roleId, Role role){
        String sql = "UPDATE role set role_name=:roleName where role_id=:roleId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("roleId", roleId)
                .addValue("roleName", role.getRoleName());

        jdbcTemplate.update(sql, params);
    }

    public void deleteRoleById(Long roleId){
        String sql = "DELETE FROM role where role_id = :roleId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("roleId", roleId);

        jdbcTemplate.update(sql, params);
    }
}
