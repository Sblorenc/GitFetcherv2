package com.example.GitFetcherV2;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString


public class UserRepositoryInfo {
	private String username;
	private List<UserRepository>repos;
	
}
