package net.ks.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ks.shoppingbackend.dao.ProductDAO;
import net.ks.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;
	//
	@RequestMapping("/all/products")
	@ResponseBody // return object in form of json , convert automatically
	public List<Product> getAllProduct() {

		return productDAO.listActiveProducts();
	}

	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductByCategory(@PathVariable int id) {

		return productDAO.listActiveProductByCategory(id);
	}

}
