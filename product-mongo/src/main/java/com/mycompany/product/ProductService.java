package com.mycompany.product;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.product.exceptions.BadRequestException;

/**
 * The microservice responsible for implementing the services and APIs
 */
@RestController
public class ProductService {

	@Autowired
	ProductRepository prodRepo;

	@Autowired
	ProductMsgProducer producer;

	@RequestMapping("/product/{id}")
	Product getProduct(@PathVariable("id") String id) {
		Product product = this.prodRepo.findOne(id);
		if (product == null){
			throw new BadRequestException(
					BadRequestException.ID_NOT_FOUND,
					MessageFormat.format("No product for id {0}", id));
		}
		return product;
	}

	@RequestMapping("/products")
	List<Product> getProductsForCategory(@RequestParam("id") int id) {
		return this.prodRepo.findByCatId(id);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	Product deleteProduct(@PathVariable("id") String id) {

		Product exitingProduct = this.prodRepo.findOne(id);
		if (exitingProduct == null) {
			throw new BadRequestException(
					BadRequestException.ID_NOT_FOUND,
					MessageFormat.format("Product {0} not found", id));
		}

		prodRepo.delete(exitingProduct);
		producer.sendUpdate(exitingProduct, "DELETE");
		return exitingProduct;
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		Product existing = prodRepo.findOne(id);
		existing.setCatId(product.getCatId());
		existing.setName(product.getName());

		Product saved = prodRepo.save(existing);
		producer.sendUpdate(saved, "UPDATED");
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	ResponseEntity<Product> insertProduct(@RequestBody Product product) {
		Product saved = prodRepo.save(product);
		producer.sendUpdate(product, "CREATED");
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

}
