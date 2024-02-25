package com.example.GitFetcherV2;






import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import com.example.GitFetcherV2.service.RepoService;




@RestController

@RequestMapping("api/v2/")
public class GitFetchController {
	
	private RepoService repoService;
	private String url = "https://api.github.com/users/";
	@Autowired
	 public GitFetchController(RepoService repoService) {
	        this.repoService = repoService;
	    }
	@GetMapping("/{username}")
	public ResponseEntity<UserRepositoryInfo> getUserRepositoryInfo(@PathVariable String username) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		try {
		UserRepositoryInfo userRepoInfo = new UserRepositoryInfo();
			userRepoInfo.setUsername(username);
			userRepoInfo.setRepos(repoService.getRepositories(url,username,headers));
		return ResponseEntity.ok(userRepoInfo);
		} catch (HttpClientErrorException e) {
			throw new UserNotFoundException();
		}
	}
}

