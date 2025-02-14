package com.synergisticit.repository;

import com.synergisticit.domain.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleRepository3")
public class RoleRepository3 {

    @Autowired
    SessionFactory sessionFactory;

    public Role save(Role role) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Role retrievedRole = findById(role.getRoleId());
            if(retrievedRole == null){
                session.save(role);
            }else{
                session.merge(role);
            }
            session.getTransaction().commit();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return role;
    }

    public Role findById(Long roleId) {
        Role role = null;
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            role=session.get(Role.class, roleId);
            session.getTransaction().commit();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return role;
    }

    public List<Role> findAll() {
        List<Role> roles = null;
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            roles=session.createQuery("from Role").list();
            session.getTransaction().commit();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return roles;
    }

    public void update(Role role) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();

        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteById(int roleId) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Role retrievedRole = session.get(Role.class, roleId);
            if(retrievedRole !=null) {
                session.delete(retrievedRole);
            }
            session.getTransaction().commit();
        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}
