package com.mycompany.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The microservice responsible for implementing the services and APIs
 */
@RestController
public class ProductService {

	@Autowired
	ProductRepository prodRepo;

	@RequestMapping("/product/{id}")
	Product getProduct(@PathVariable("id") String id) {
		return this.prodRepo.findOne(id);
	}

	@RequestMapping("/products")
	List<Product> getProductsForCategory(@RequestParam("id") int id) {
		return this.prodRepo.findByCatId(id);
	}
}
