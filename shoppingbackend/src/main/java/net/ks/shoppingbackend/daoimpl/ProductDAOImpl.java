package net.ks.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.ks.shoppingbackend.dao.ProductDAO;
import net.ks.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	/*
	 * SINGLE
	 */

	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * LIST
	 */

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	/*
	 * INSERT
	 */

	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * UPDATE
	 */

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * DELETE
	 */
	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);

			// can do with two different ways 1 -> call session factory again
			// and 2 -> call update method defined before
			// 1
			// sessionFactory.getCurrentSession().update(product);
			// 2
			return this.update(product);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<Product> listActiveProducts() {

		// 1 way ->
		/*
		 * String selectActiveCategory = "FROM Category WHERE active = :active";
		 * Query query =
		 * sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		 * query.setParameter("active", true);
		 * //System.out.println(query.getResultList()); return
		 * query.getResultList();
		 */

		// 2 way ->
		// From Product -> Product is entity name if no name mentioned than it
		// will take class name, active is field name in product .java, :active
		// is parameter
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductByCategory(int categoryId) {
		String selectActiveProductByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductByCategory, Product.class)
				.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
		// need to send limited set of product thats why we are using
		// setFirstResult and setMaxResult from 0 to count value provided by
		// user
	}

}
