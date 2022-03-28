package com.uhi.gateway.uhi_gateway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uhi.gateway.uhi_gateway.entity.Subscriber;

@Service
public class HSPService {

	public void verify(List<Subscriber> subsribers, Subscriber req) {
		// TODO Auto-generated method stub
		subsribers.stream()
		  .filter(customer -> req.getSubscriberId().equals(customer.getSubscriberId()))
		  .findAny()
		  .orElseThrow(null);
	}

}
