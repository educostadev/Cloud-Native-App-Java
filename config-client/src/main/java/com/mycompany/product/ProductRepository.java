package com.mycompany.product;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	/**
	 * The query builder mechanism built into the Spring Data repository infrastructure is useful for building queries over entities of the repository.
	 * <p>
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	 *
	 * @param catId
	 * @return
	 */
	@Cacheable("productsByCategoryCache")
	List<Product> findByCatId(int catId);

	/**
	 * only clearing the entries related to the category being operated.
	 * Use Spring Expression Language (SpEL)
	 * @param product
	 * @return
	 */
	@CacheEvict(cacheNames = "productsByCategoryCache", key = "#result?.catId")
	Product save(Product product);

	@CacheEvict(cacheNames = "productsByCategoryCache", key = "#p0.catId")
	void delete(Product product);
}
