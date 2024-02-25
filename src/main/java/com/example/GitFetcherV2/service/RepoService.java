package com.example.GitFetcherV2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.GitFetcherV2.Branch;
import com.example.GitFetcherV2.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RepoService {
	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;
	private BranchService branchService;
	
	
	public RepoService(RestTemplate restTemplate, ObjectMapper objectMapper, BranchService branchService) {
		this.objectMapper = objectMapper;
		this.restTemplate = restTemplate;
		this.branchService = branchService;
	}
	 
	public List<UserRepository>getRepositories(String url,String username, HttpHeaders headers) throws JsonMappingException, JsonProcessingException {
		url = url + username+"/repos";
		List<UserRepository>listOfRepositories = new ArrayList<>();
		HttpEntity<String>request = new HttpEntity<>(headers);
		ResponseEntity<String> response =  restTemplate.exchange(url, HttpMethod.GET,request, String.class);
		JsonNode root = objectMapper.readTree(response.getBody());
		
		for (JsonNode jnode : root) {			
			String isFork = jnode.get("fork").asText();
			switch (isFork) {
			case "true":
				break;
			case "false":
				String name = jnode.get("name").asText();
				List<Branch>branches = branchService.getBranches(username, jnode.get("name").asText(), headers);
				UserRepository userRepository = new UserRepository(name,branches);
				
				listOfRepositories.add(userRepository);
				break;
			}
		}
		return listOfRepositories;
	}
	
	
}
