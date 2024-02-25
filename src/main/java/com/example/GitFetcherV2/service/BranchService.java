package com.example.GitFetcherV2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.GitFetcherV2.Branch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class BranchService {
	private RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	
	
	public BranchService (RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.restTemplate = restTemplate;
	}
	
	public List<Branch> getBranches(String username, String repoName, HttpHeaders headers) throws JsonMappingException, JsonProcessingException{
		String url = "https://api.github.com/repos/"+username+"/"+repoName+"/branches";
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<String>response = restTemplate.exchange(url, HttpMethod.GET , request, String.class);
		List<Branch>listOfBranches = new ArrayList<Branch>();
		JsonNode root = objectMapper.readTree(response.getBody());
		for (JsonNode jnode : root) {
			String name = jnode.get("name").asText();
			String commitSHA = jnode.get("commit").get("sha").asText();
			Branch branch = new Branch(name,commitSHA);
			listOfBranches.add(branch);
		}
		return listOfBranches;
}
	
	
}
