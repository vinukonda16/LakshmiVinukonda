package com.telstra.codechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.dto.Users.Items;
import com.telstra.codechallenge.service.SpringBootUsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SpringBootStarredRepoController {

	@Autowired
	private SpringBootStarredRepoService springBootStarredRepoService;
	

    @GetMapping("/hottestRepo")
    public List<Items>  getRepo(){
         return  this.springBootStarredRepoService.getRepo();
    }

	}

