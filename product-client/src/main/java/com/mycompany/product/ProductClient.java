package com.mycompany.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * The client to test out the service lookup
 */
@RestController
public class ProductClient {

	@Autowired
	private RestTemplate rTemplate;

	@Value("${env}")
	private String env;

	/**
	 * Does a lookup of the product service from the service registry and directs it to the
	 *
	 * The URL construction http://PRODUCT/ will be translated at runtime by Ribbon after doing a lookup.
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/client/{id}")
	Product getProduct(@PathVariable("id") int id) {
		return rTemplate.getForObject("http://PRODUCT/" + env + "/product/" + id, Product.class);
	}

}
