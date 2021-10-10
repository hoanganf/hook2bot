package com.anit.domain.service;

import com.anit.domain.model.value.ChatMessage;
import com.anit.domain.model.value.Payload;
import com.anit.domain.model.value.Ref;

import java.util.Map;

/**
 * @author anit
 */
public class ChatMessegeBuilder {
    private static final String KEY_REPOSITORY = "repository";
    private static final String KEY_REF = "ref";
    private static final String KEY_ISSUE = "issue";
    private static final String KEY_PULL_REQUEST = "pull_request";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_COMMENT = "comment";
    private static final String KEY_SENDER = "sender";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_HTML_URL = "html_url";
    private static final String KEY_BODY = "body";

    public ChatMessage buildPullRequestOpened(Payload payload) {
        Integer pullRequestNo = payload.getValue(KEY_NUMBER);
        Map<String, String> repository = payload.getValue(KEY_REPOSITORY);
        Map<String, String> pullRequest = payload.getValue(KEY_PULL_REQUEST);
        Map<String, String> sender = payload.getValue(KEY_SENDER);

        String repositoryFullName = repository.get(KEY_FULL_NAME);
        String repositoryHtmlUrl = repository.get(KEY_HTML_URL);
        String pullRequestHtmlUrl = pullRequest.get(KEY_HTML_URL);
        String login = sender.get(KEY_LOGIN);

        return ChatMessage.of("User *" + login + "* open pull request <" + pullRequestHtmlUrl + "|#" + pullRequestNo + "> on repository <" + repositoryHtmlUrl + "|" + repositoryFullName + ">");
    }

    public ChatMessage buildPullRequestComment(Payload payload) {
        Map<String, ?> issue = payload.getValue(KEY_ISSUE);
        Map<String, String> repository = payload.getValue(KEY_REPOSITORY);
        Map<String, String> comment = payload.getValue(KEY_COMMENT);
        Map<String, String> sender = payload.getValue(KEY_SENDER);

        Integer pullRequestNo = (Integer) issue.get(KEY_NUMBER);
        String pullRequestHtmlUrl = (String) issue.get(KEY_HTML_URL);
        String repositoryFullName = repository.get(KEY_FULL_NAME);
        String repositoryHtmlUrl = repository.get(KEY_HTML_URL);
        String login = sender.get(KEY_LOGIN);
        String commentBody = comment.get(KEY_BODY);

        return ChatMessage.of("User *" + login + "*" + " comment on pull request <" + pullRequestHtmlUrl + "|#" + pullRequestNo + "> of repository <" + repositoryHtmlUrl + "|" + repositoryFullName + "> ```\n" + commentBody + "\n```");
    }

    public ChatMessage buildPushToMaster(Payload payload) {
        Ref ref = Ref.of(payload.getValueAsString(KEY_REF));
        Map<String, String> repository = payload.getValue(KEY_REPOSITORY);
        Map<String, String> sender = payload.getValue(KEY_SENDER);

        String repositoryFullName = repository.get(KEY_FULL_NAME);
        String repositoryHtmlUrl = repository.get(KEY_HTML_URL);
        String login = sender.get(KEY_LOGIN);

        return ChatMessage.of("User *" + login + "*" + " merge into " + ref.extractBranch() + " of repository <" + repositoryHtmlUrl + "|" + repositoryFullName + ">");
    }
}
