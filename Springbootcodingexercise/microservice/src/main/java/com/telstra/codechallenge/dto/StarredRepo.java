package com.telstra.codechallenge.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StarredRepo {

	@JsonProperty("items")
	private List<Items> items;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	public static class Items {
		@JsonCreator
		public Items(@JsonProperty("watchers_count") int watchersCount, @JsonProperty("language") String language,@JsonProperty("description") String description,@JsonProperty("name") String name,
				@JsonProperty("html_url") String htmlUrl) {
			super();
			this.watchersCount = watchersCount;
			this.language = language;
			this.description = description;
			this.name = name;
			this.htmlUrl = htmlUrl;
		}
		@JsonProperty("watchers_count")
		private int watchersCount;
		private String language;
		private String description;
		private String name;
		@JsonProperty("html_url")
		private String htmlUrl;
	}

}
