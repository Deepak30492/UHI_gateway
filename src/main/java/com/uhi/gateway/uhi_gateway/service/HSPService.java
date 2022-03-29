package com.uhi.gateway.uhi_gateway.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uhi.gateway.uhi_gateway.controller.GatewayController;
import com.uhi.gateway.uhi_gateway.entity.Subscriber;

@Service
public class HSPService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HSPService.class);

	public void verify(List<Subscriber> subsribers, Subscriber req) {
		// TODO Auto-generated method stub
		try {
			Optional.ofNullable(subsribers.stream()
			  .filter(customer -> req.getSubscriberId().equals(customer.getSubscriberId()))
			  .findAny()
			  .orElseThrow(() -> new Exception("Subscriber not found - "+req.getSubscriberId())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Exception in {} {}",HSPService.class,e.getMessage());
		}
	}

}
