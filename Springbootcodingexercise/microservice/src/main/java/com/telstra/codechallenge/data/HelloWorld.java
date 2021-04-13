package com.telstra.codechallenge.dto;


public class HelloWorld {
  private final long id;
  private final String content;

  public HelloWorld(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
