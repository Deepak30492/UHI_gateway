package com.uhi.gateway.uhi_gateway.controller;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhp.sdk.beans.Response;
import com.uhi.gateway.uhi_gateway.entity.Subscriber;
import com.uhi.gateway.uhi_gateway.registry.RegistryKeyFinder;
import com.uhi.gateway.uhi_gateway.service.HSPService;

@RestController
@Validated
@RequestMapping("/gateway")
public class GatewayController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);

	@Autowired
	public static List<Subscriber> subsribers = new RegistryKeyFinder().lookup();

	@Autowired
	HSPService Hspservice;

	@PostMapping(value = "/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> search(@Valid @RequestBody String req) {
		LOGGER.info("Search request| " + req);
		// Hspservice.forwardToRequestor(req, subsribers);
		return ResponseEntity.status(HttpStatus.OK).body(Hspservice.verify(subsribers, req));
	}

	@PostMapping(value = "/on_search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> on_search(@Valid @RequestBody String req) {
		LOGGER.info("on_Search request| " + req);
		return ResponseEntity.status(HttpStatus.OK).body(Hspservice.generateAck(req));
	}

}
