package com.mycompany.product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findByCatId(int catId);

}
