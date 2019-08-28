package com.mycompany.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class ProductMsgProducer {

	@Autowired
	JmsTemplate prodUpdtemplate;

	@Value("${custom.product-topic}")
	private String productTopic;

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	public void sendUpdate(Product product, String action) {
		ProductUpdMessage msg = new ProductUpdMessage(product, action);
		prodUpdtemplate.convertAndSend(productTopic, msg);
	}

}