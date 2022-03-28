package com.uhi.gateway.uhi_gateway.entity;

import java.time.Instant;
import java.time.OffsetDateTime;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subscriber {
	
	@JsonProperty("subscriber_id")
	private String subscriberId;

	private String country;

	private String city;

	private String domain;
	
	@JsonProperty("signing_public_key")
	private String signingPublicKey;
	
	@JsonProperty("encr_public_key")
	private String encryptedPublicKey;

	private Instant validFrom;

	private Instant validUntil;
	
	private boolean subscribed;
	
	private String endpoint;
	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;

	public Subscriber() {

	}

	public Subscriber(String subscriber_id, String country, String city, String domain, String signingPublicKey,
			String encryptedPublicKey,boolean subscribed, String endpoint,
			String type) {
		this.subscriberId = subscriberId;
		this.country = country;
		this.city = city;
		this.domain = domain;
		this.signingPublicKey = signingPublicKey;
		this.encryptedPublicKey = encryptedPublicKey;
		this.validFrom = Instant.now();
		this.validUntil = OffsetDateTime.now().plusYears(1).toInstant();
		this.subscribed = subscribed;
		this.endpoint = endpoint;
		this.type = type;
	}

	

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Subscriber(JSONObject o) {
		// TODO Auto-generated constructor stub
	}

	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSigningPublicKey() {
		return signingPublicKey;
	}

	public void setSigningPublicKey(String signingPublicKey) {
		this.signingPublicKey = signingPublicKey;
	}

	public String getEncryptedPublicKey() {
		return encryptedPublicKey;
	}

	public void setEncryptedPublicKey(String encryptedPublicKey) {
		this.encryptedPublicKey = encryptedPublicKey;
	}

	public Instant getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Instant validFrom) {
		this.validFrom = validFrom;
	}

	public Instant getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Instant validUntil) {
		this.validUntil = validUntil;
	}
}
