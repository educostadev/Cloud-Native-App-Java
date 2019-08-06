package com.mycompany.product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	/**
	 * The query builder mechanism built into the Spring Data repository infrastructure is useful for building queries over entities of the repository.
	 *
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	 * @param catId
	 * @return
	 */
	List<Product> findByCatId(int catId);

}
