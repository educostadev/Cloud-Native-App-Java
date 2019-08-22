package com.mycompany.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
		return rTemplate.getForObject("http://PRODUCT/product/" + id, Product.class);
	}

	/**
	 * From: https://spring.io/guides/gs/service-registration-and-discovery/
	 *
	 */
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/client/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}
}
