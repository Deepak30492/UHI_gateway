package com.uhi.gateway.uhi_gateway.controller;

import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uhi.gateway.uhi_gateway.entity.Subscriber;
import com.uhi.gateway.uhi_gateway.registry.RegistryKeyFinder;
import com.uhi.gateway.uhi_gateway.service.HSPService;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);
	
	@Autowired
	public static List<Subscriber> subsribers = new RegistryKeyFinder().lookup();

	@Autowired
	HSPService Hspservice;

	@PostMapping("/search")
	public ResponseEntity<String> search(@RequestBody Subscriber req) {
		LOGGER.info("Search request| " + req);
		Hspservice.verify(subsribers, req);
		return ResponseEntity.status(HttpStatus.OK).body("Acknowledged");
	}

	@PostMapping("/on_search")
	public ResponseEntity<String> on_search(@RequestBody String req) {
		LOGGER.info("on_Search request| " + req);
		return ResponseEntity.status(HttpStatus.OK).body("Acknowledged");
	}

}
