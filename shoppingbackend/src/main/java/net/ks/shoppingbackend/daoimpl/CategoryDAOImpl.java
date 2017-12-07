package net.ks.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.ks.shoppingbackend.dao.CategoryDAO;
import net.ks.shoppingbackend.dto.Category;

//categoryDAO is an ref/obj name from Autowired value in page controller to let spring framework know that CategoryDAOImpl need to be used. 
//Spring framework only knows about object of CategoryDAO interface but which override method of which class need to be used is given by this @Repository annotation

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
		//HQL // entity name Category // active is from entity not from table // :parameter
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		//System.out.println(query.getResultList());
		return query.getResultList();
		
	}
	
	/*Example for list for TV
	 * 	String selectActiveCategory = "FROM Category WHERE name = :a";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("a", "TV");
		return query.getResultList();*/

	// Getting Single category based on Id
	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
		// return particular id value of Category class
	}

	@Override
	public boolean add(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	// Updating a single category
	@Override
	public boolean update(Category category) {
		try {

			sessionFactory.getCurrentSession().update(category);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {

			sessionFactory.getCurrentSession().update(category);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
	}
	}
}
