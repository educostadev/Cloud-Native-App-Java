package com.mycompany.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdListener {

	@Autowired
	ProductService prodService;

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new
				MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.BYTES);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@JmsListener(destination = "${custom.product-topic}", subscription = "productSearchListener")
	public void receiveMessage(ProductUpdMessage msg) {
		Product product = msg.getProduct() ;
		if ("DELETED".equals(msg.getAction())) {
			prodService.deleteProduct(product);
			System.out.println("deleted " + product.getId());
		} else {
			prodService.insertUpdateProduct(product);
			System.out.println("upserted " + product.getId());
		}
	}
}
