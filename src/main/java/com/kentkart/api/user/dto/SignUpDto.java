package com.kentkart.api.user.dto;

public record SignUpDto (String firstName, String lastName, String login, char[] password) { }
