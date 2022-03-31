package com.uhi.gateway.uhi_gateway.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.uhi.gateway.uhi_gateway.controller.GatewayController;
import com.uhi.gateway.uhi_gateway.entity.Subscriber;

public class RegistryKeyFinder {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);
	public String registry_url="http://localhost:3030/api/lookup";

	ObjectMapper mapper = new ObjectMapper();
	RestTemplate template = new RestTemplate();

	public List<Subscriber> lookup() {

		System.out.println("Registry URL:" + registry_url);
		LOGGER.info("Registry URL {}", registry_url);

		Map<String, Object> subscriberMap = new HashMap<String, Object>();
		subscriberMap.put("country", "");
		subscriberMap.put("city", "");
		subscriberMap.put("domain", "");
		subscriberMap.put("type", "BPP");
		subscriberMap.put("status", "SUBSCRIBED");
		Subscriber[] subscribers = template.postForEntity(registry_url, subscriberMap, Subscriber[].class).getBody();
		System.out.println("Lookup Response|" +  Arrays.toString(subscribers));
		LOGGER.info("Lookup Response {}", subscribers);
		
		return Arrays.stream(subscribers).map(object -> mapper.convertValue(object, Subscriber.class))
				.collect(Collectors.toList());
	}

}
