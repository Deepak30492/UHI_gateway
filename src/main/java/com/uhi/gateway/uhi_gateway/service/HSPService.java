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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uhi.gateway.uhi_gateway.entity.Subscriber;

@Service
public class HSPService {

	@Autowired
	RestTemplate template;

	ObjectMapper mapper = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(HSPService.class);

	public Response verify(List<Subscriber> subsribers, String req) {
		try {
			JsonNode rootNode = mapper.readTree(req);
			JsonNode metaNode = rootNode.get("context");
			System.out.println("node|" + metaNode.get("consumer_id").textValue());
			Optional.ofNullable(subsribers.stream()
					.filter(subs -> metaNode.get("consumer_id").textValue().equals(subs.getSubscriberId())).findAny()
					.orElseThrow(() -> new Exception("Subscriber not found - " + metaNode.get("consumer_id"))));
			return generateAck(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Exception in {} {}", HSPService.class, e.getMessage());
			Error err = new Error("", "404", "", "Subscriber not found.");
			return generateNack(req, err);
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
