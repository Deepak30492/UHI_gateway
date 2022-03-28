package com.uhi.gateway.uhi_gateway.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.uhi.gateway.uhi_gateway.entity.Subscriber;

@Component
public class RegistryKeyFinder {

	@Value("${spring.application.registry_url}")
	private String registry_url;
	ObjectMapper mapper = new ObjectMapper();
	/*
	 * @Autowired private RestTemplate template;
	 */

	public List<Subscriber> lookup() {
		RestTemplate template = new RestTemplate();
		System.out.println("Registry URL:" + registry_url);
		// JSONArray responses = template.postForObject(registry_url+"/lookup",
		// subscriber.getSubscriberId(), JSONArray.class);
		//JSONArray responses = template.getForObject("http://localhost:8080/subscriber/lookup", JSONArray.class);
		Object[] objects = template.getForEntity(registry_url, Object[].class).getBody();
		System.out.println("Response|"+objects);
		/*
		 * JSONArray responses = new
		 * Call<JSONObject>().method(HttpMethod.POST).url(registry_url+"/lookup").
		 * input(subscriber.getInner()).inputFormat(InputFormat.JSON)
		 * .header("content-type", MimeType.APPLICATION_JSON.toString())
		 * .header("accept",MimeType.APPLICATION_JSON.toString()).getResponseAsJson();
		 */
		/*
		 * if (responses == null) { responses = new JSONArray(); }
		 */
		/*
		 * for (Iterator<?> i = responses.iterator(); i.hasNext(); ) { JSONObject
		 * object1 = (JSONObject) i.next();
		 * 
		 * if ("SUBSCRIBED".equals(object1.get("status"))){ i.remove(); }
		 * 
		 * if ("No".equals(object1.get("subscribed"))){ i.remove(); } }
		 */
		/*
		 * List<Subscriber> subscribers = new ArrayList<>(); for (Object o : responses)
		 * { subscribers.add(new Subscriber((JSONObject) o)); } return subscribers;
		 */
		return Arrays.stream(objects)
				  .map(object -> mapper.convertValue(object, Subscriber.class))
				  .collect(Collectors.toList());
	}
	
	
}
