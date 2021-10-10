package com.anit.domain.model.value;

import com.anit.domain.model.ApiException;

/**
 * @author anit
 */
public enum GitEvent {
    COMMIT_COMMENT("commit_comment"),
    CREATE("create"),
    DELETE("delete"),
    DEPLOYMENT("deployment"),
    DEPLOYMENT_STATUS("deployment_status"),
    FORK("fork"),
    GOLLUM("gollum"),
    ISSUE_COMMENT("issue_comment"),
    ISSUES("issues"),
    MEMBER("member"),
    PAGE_BUILD("page_build"),
    PUBLIC("public"),
    PULL_REQUEST("pull_request"),
    PULL_REQUEST_REVIEW_COMMENT("pull_request_review_comment"),
    PUSH("push"),
    RELEASE("release"),
    STATUS("status"),
    TEAM_ADD("team_add"),
    WATCH("watch");

    private final String value;

    GitEvent(String value) {
        this.value = value;
    }

    public static GitEvent of(String value) {
        for (GitEvent e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new ApiException(ErrorCode.BAD_REQUEST, ErrorMessage.of("GitEvent value not valid. value=".concat(value)));
    }

    public boolean isPullRequestReviewComment() {
        return this == PULL_REQUEST_REVIEW_COMMENT;
    }
    public boolean isIssueComment() {
        return this == ISSUE_COMMENT;
    }
    public boolean isPullRequest() {
        return this == PULL_REQUEST;
    }
    public boolean isPush() {
        return this == PUSH;
    }

    @Override
    public String toString() {
        return value;
    }
}
