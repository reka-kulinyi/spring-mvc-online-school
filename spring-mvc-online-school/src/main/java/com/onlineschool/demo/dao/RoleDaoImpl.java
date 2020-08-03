package com.onlineschool.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineschool.demo.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role getRoleByName(String roleName) {
		
		if(roleName == null || roleName.trim().length() == 0) {
			return null;
		}
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Role> query = currentSession.createQuery("from Role where name=:roleName", Role.class);
		query.setParameter("roleName", roleName);
		Role role = null;
		
		try {
			role = query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return role;
	}

}
