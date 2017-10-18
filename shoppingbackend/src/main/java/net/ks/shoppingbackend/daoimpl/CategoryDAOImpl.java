package net.ks.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.ks.shoppingbackend.dao.CategoryDAO;
import net.ks.shoppingbackend.dto.Category;

//categoryDAO is an ref/obj name from Autowired value in page controller to let spring framework know that CategoryDAOImpl need to be used. 
//Spring framework only knows about object of CategoryDAO interface but which override method of which class need to be used is given by this @Repository annotation

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	// static data testing purpose
	private static List<Category> categories = new ArrayList<>();
	// initialize at start itself
	static {
		Category category = new Category();

		// adding first category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is tv");
		category.setImageURL("Cat_1.png");

		categories.add(category);

		// second category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is mobile");
		category.setImageURL("Cat_2.png");

		categories.add(category);

		// third category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is laptop");
		category.setImageURL("Cat_3.png");

		categories.add(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {

		// enhanced for loop
		for (Category category : categories) {

			if (category.getId() == id)
				return category;
		}

		return null;

	}

}
