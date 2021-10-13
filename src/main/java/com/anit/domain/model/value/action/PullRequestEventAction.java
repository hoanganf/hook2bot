package com.anit.domain.model.value.action;

import com.anit.domain.model.ApiException;
import com.anit.domain.model.value.ErrorCode;
import com.anit.domain.model.value.ErrorMessage;

public enum PullRequestEventAction {
    ASSIGNED("assigned"),
    UNASSIGNED("unassigned"),
    AUTO_MERGE_DISABLED("auto_merge_disabled"),
    AUTO_MERGE_ENABLED("auto_merge_enabled"),
    CLOSED("closed"),
    CONVERTED_TO_DRAFT("converted_to_draft"),
    EDITED("edited"),
    LABELED("labeled"),
    UNLABELED("unlabeled"),
    LOCKED("locked"),
    UNLOCKED("unlocked"),
    OPENED("opened"),
    READY_FOR_REVIEW("ready_for_review"),
    REOPENED("reopened"),
    REVIEW_REQUESTED("review_requested"),
    REVIEW_REQUEST_REMOVED("review_request_removed"),
    SYNCHRONIZE("synchronize");
    private final String value;

    PullRequestEventAction(String value) {
        this.value = value;
    }

    public static PullRequestEventAction of(String value) {
        for (PullRequestEventAction e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new ApiException(ErrorCode.BAD_REQUEST, ErrorMessage.of("PullRequestEventAction value not valid. value=".concat(value)));
    }

    public boolean isOpened() {
        return this == OPENED;
    }

    public boolean isClosed() {
        return this == CLOSED;
    }

    public boolean isReopened() {
        return this == REOPENED;
    }

    @Override
    public String toString() {
        return value;
    }

}
