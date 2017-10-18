package net.ks.shoppingbackend.dao;

import java.util.List;

import net.ks.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);
}
