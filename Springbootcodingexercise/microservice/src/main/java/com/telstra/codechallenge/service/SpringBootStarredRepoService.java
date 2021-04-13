package com.telstra.codechallenge.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.telstra.codechallenge.dto.Users;
import com.telstra.codechallenge.util.InternalSeverException;
import com.telstra.codechallenge.util.MethodArgumentNotValidException;
import com.telstra.codechallenge.util.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class SpringBootStarredRepoService{

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;
	
	
	public StarredRepo getRepo() {

		StarredRepo repo = null;
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        System.out.println("Date = "+ cal.getTime());

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String lastWeekDate = format1.format(cal.getTime());

		try {
			repo = restTemplate.getForObject(env.getProperty("repo.base.url")
					+ "/search/repositories?q=created:>"+lastWeekDate+"&sort=stars&order=desc&per_page=1", StarredRepo.class);
		} catch (Exception e) {
			throw new InternalSeverException("Error while accessing Git API");
		}
		if (Objects.isNull(repo))
			throw new RepoNotFoundException("Git API response is null or empty:");

		else if (CollectionUtils.isEmpty(repo.getItems()))
			throw new RepoNotFoundException("Requested number of accounts:");

		return repo;
	}

}
