package com.example.GitFetcherV2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.GitFetcherV2.service.RepoService;

@SpringBootTest

public class GitFetchTest {
	@InjectMocks
	private GitFetchController gitFetchController;
	@Mock
	private RepoService repoServiceMock;
	
	@BeforeEach
	void setUp() {
		    MockitoAnnotations.openMocks(this);
		    
	}
	
		    
	
	
	
	
	@Test
    void shouldReturnCorrectResult() throws Exception {
		String username = "Sblorenc";
		 
		String url = "https://api.github.com/users/";
		
		 HttpHeaders headers = new HttpHeaders();
		 	headers.set("Accept", "application/json");
		
	    List<UserRepository> repositories = new ArrayList<>();

	    repositories.add(new UserRepository("repo1", Arrays.asList(new Branch("master", "123"))));

	    repositories.add(new UserRepository("repo2", Arrays.asList(new Branch("main", "213"))));

	    UserRepositoryInfo expectedUserRepoInfo = new UserRepositoryInfo();
	    expectedUserRepoInfo.setRepos(repositories);
	    expectedUserRepoInfo.setUsername(username);
		 
		 List<Branch>branches2 = new ArrayList<>();
		 branches2.add(new Branch("main", "213"));
		 repositories.add(new UserRepository("repo2",branches2));
		
		
		 expectedUserRepoInfo.setRepos(repositories);
		
		
		 
		
		    when(repoServiceMock.getRepositories(url, username, headers)).thenReturn(repositories);
	
		
		ResponseEntity<UserRepositoryInfo> responseEntity = gitFetchController.getUserRepositoryInfo(username);
		
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		
		assertThat(responseEntity.getBody()).usingRecursiveComparison().isEqualTo(expectedUserRepoInfo);
          
    }
	
	
	@Test
	void shouldReturnUserNotFoundException() throws Exception  {
	String username = "Sblorenc1";
	 HttpHeaders headers = new HttpHeaders();
	 	headers.set("Accept", "application/json");
	 	when(repoServiceMock.getRepositories(anyString(), eq(username), any(HttpHeaders.class)))
        .thenThrow(new UserNotFoundException());
	 	
	 	assertThrows(UserNotFoundException.class, ()-> {
	 		gitFetchController.getUserRepositoryInfo(username);
	 		
	 	});
	}
}


