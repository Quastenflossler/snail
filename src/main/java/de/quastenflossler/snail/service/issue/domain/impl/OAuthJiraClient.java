package de.quastenflossler.snail.service.issue.domain.impl;

import javax.inject.Named;

@Named(value = OAuthJiraClient.RESOURCE_NAME)
public class OAuthJiraClient {

    public static final String RESOURCE_NAME = "OAuthJiraClient";

    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBK";
}
