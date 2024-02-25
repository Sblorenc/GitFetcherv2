package com.example.GitFetcherV2;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UserRepository {

	private String repoName;
	private List<Branch>branches;
	
	public UserRepository(String repoName, List<Branch>branches) {
		this.repoName = repoName;
		this.branches = branches;
	}
	
}

