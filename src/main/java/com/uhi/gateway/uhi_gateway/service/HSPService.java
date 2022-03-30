package com.uhi.gateway.uhi_gateway.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dhp.sdk.beans.Ack;
import com.dhp.sdk.beans.Error;
import com.dhp.sdk.beans.MessageAck;
import com.dhp.sdk.beans.Response;
import com.uhi.gateway.uhi_gateway.controller.GatewayController;
import com.uhi.gateway.uhi_gateway.entity.Subscriber;

@Service
public class HSPService {
	
	@Autowired
	RestTemplate template;

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

	public Response generateAck(String req) {
		Ack ack = new Ack();
		Response resp = new Response();
		MessageAck msgack = new MessageAck();
		Error err = new Error("", "", "", "");
		ack.setStatus("ACK");
		msgack.setAck(ack);
		resp.setMessage(msgack);
		resp.setError(err);
		
		return resp;
	}
	
	public Response generateNack(String req, Error err) {
		Ack ack = new Ack();
		Response resp = new Response();
		MessageAck msgack = new MessageAck();
		ack.setStatus("NACK");
		msgack.setAck(ack);
		resp.setMessage(msgack);
		resp.setError(err);
		
		return resp;
	}

	public void forwardToRequestor(@Valid String req, List<Subscriber> subsribers) {
		// TODO Auto-generated method stub
		
		template.postForObject("requestor_URL", subsribers, null);
	}

}
