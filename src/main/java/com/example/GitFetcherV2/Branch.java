package com.example.GitFetcherV2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Branch {
	
	private String branchName;
	private String commitSHA;
	
	public Branch(String branchName, String commitSHA) {
		this.branchName = branchName;
		this.commitSHA = commitSHA;
	}
	

}
