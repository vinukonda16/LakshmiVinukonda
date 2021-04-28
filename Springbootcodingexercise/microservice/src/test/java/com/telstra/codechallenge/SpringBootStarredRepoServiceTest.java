package com.telstra.codechallenge;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.telstra.codechallenge.dto.StarredRepo;
import com.telstra.codechallenge.service.SpringBootStarredRepoService;
import com.telstra.codechallenge.util.InternalSeverException;
import com.telstra.codechallenge.util.RepoNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class SpringBootStarredRepoServiceTest {
	
	@InjectMocks
	SpringBootStarredRepoService service = new SpringBootStarredRepoService();

	@Mock
	RestTemplate restTemplate;

	@Mock
	private Environment env;
	
	StarredRepo repo = null;
	

	@Test
	public void testgetRepoGitApi() {
		
		
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        //System.out.println("Date = "+ cal.getTime());

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String lastWeekDate = format1.format(cal.getTime());
        
		StarredRepo repoObj = new StarredRepo();
		List<StarredRepo.Items> list = Arrays.asList(new StarredRepo.Items(309, "c#","Get file less command execution for lateral movement." ,"SharpNoPSExec","https://github.com/juliourena/SharpNoPSExec"),
				new StarredRepo.Items(309, "c#","Get file less command execution for lateral movement." ,"SharpNoPSExec","https://github.com/juliourena/SharpNoPSExec"));
		repoObj.setItems(list);
		when(env.getProperty("repo.base.url")).thenReturn("https://api.github.com");
		when(restTemplate.getForObject(
				env.getProperty("repo.base.url") + "/search/repositories?q=created:>"+lastWeekDate+"&sort=stars&order=desc&per_page="+ 2,
				StarredRepo.class)).thenReturn(repoObj);
		StarredRepo repo = service.getRepo(2);
		assertEquals(repoObj, repo);
		assertEquals(2, repo.getItems().size());
	
	}

	@Test(expected = InternalSeverException.class)
	public void testgetRepoGitApiInternalServerexception() {
		
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        //System.out.println("Date = "+ cal.getTime());

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String lastWeekDate = format1.format(cal.getTime());
        
		StarredRepo repoObj = new StarredRepo();
		List<StarredRepo.Items> list = Arrays.asList(new StarredRepo.Items(309, "c#","Get file less command execution for lateral movement." ,"SharpNoPSExec","https://github.com/juliourena/SharpNoPSExec"),
				new StarredRepo.Items(309, "c#","Get file less command execution for lateral movement." ,"SharpNoPSExec","https://github.com/juliourena/SharpNoPSExec"));
		repoObj.setItems(list);
		when(env.getProperty("repo.base.url")).thenReturn("https://api.githubtest.com");
		when(restTemplate.getForObject(
				env.getProperty("repo.base.url") + "/search/repositories?q=created:>"+lastWeekDate+"&sort=stars&order=desc&per_page="+ 2,
				StarredRepo.class)).thenThrow(new InternalSeverException("Error while accessing Git API"));
		service.getRepo(2);
	}

	@Test(expected = com.telstra.codechallenge.util.MethodArgumentNotValidException.class)
	public void testgetRepoMethodArgumentNotValidException() {
		int limit = 0;
		service.getRepo(limit);
	}

	@Test(expected = RepoNotFoundException.class)
	public void testRepoNotFoundExceptionforemptyObj() {
		StarredRepo repoObj = new StarredRepo();
		List<StarredRepo.Items> list = Arrays.asList();

		repoObj.setItems(list);
		when(env.getProperty("repo.base.url")).thenReturn("https://api.github.com");

		when(restTemplate.getForObject(
				env.getProperty("repo.base.url") + "/search/repositories?q=created:>2021-04-04&sort=stars&order=desc&per_page=1" + 2,
				StarredRepo.class)).thenReturn(repoObj);
		service.getRepo(2);

	}

	@Test(expected = RepoNotFoundException.class)
	public void testRepoNotFoundExceptionforemptyList() {
		StarredRepo repoObj = null;
		when(restTemplate.getForObject(
				env.getProperty("repo.base.url") + "/search/repositories?q=created:>2021-04-04&sort=stars&order=desc&per_page=" + 2,
				StarredRepo.class)).thenReturn(repoObj);
		service.getRepo(2);
	}

}
