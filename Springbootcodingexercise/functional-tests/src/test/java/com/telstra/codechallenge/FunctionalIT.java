package com.telstra.codechallenge;

import com.intuit.karate.junit5.Karate;

public class FunctionalIT {
	@Karate.Test
	Karate testMicroservice() {
		return Karate.run("microservice").relativeTo(getClass());
	}

	@Karate.Test
	Karate testHelloWorld() {
		return Karate.run("helloworld").relativeTo(getClass());
	}

	@Karate.Test
	Karate testQuotes() {
		return Karate.run("quotes").relativeTo(getClass());
	}

	@Karate.Test
	Karate testUsers() {
		return Karate.run("users").relativeTo(getClass());
	}
	@Karate.Test
	Karate testStarredRepo() {
		return Karate.run("starredrepo").relativeTo(getClass());
	}
	

}