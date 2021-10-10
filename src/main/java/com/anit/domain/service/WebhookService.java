package com.anit.domain.service;

import com.anit.domain.model.CheckWebhookResult;
import com.anit.domain.model.value.GitEvent;
import com.anit.domain.model.value.Payload;
import com.anit.domain.model.value.Ref;
import com.anit.domain.model.value.ShouldSendMessageToChat;
import com.anit.domain.model.value.action.PullRequestEventAction;
import lombok.RequiredArgsConstructor;

/**
 * @author anit
 */
@RequiredArgsConstructor
public class WebhookService {
    private final ChatMessegeBuilder chatMessegeBuilder;

    public CheckWebhookResult checkHook(GitEvent event, Payload payload) {
        CheckWebhookResult.CheckWebhookResultBuilder resultBuilder = CheckWebhookResult.builder();
        if (event.isPullRequest()) {
            PullRequestEventAction action = payload.getPullRequestAction();
            if (action.isOpened() || action.isReopened()) {
                resultBuilder.shouldSendMessageToChat(ShouldSendMessageToChat.ofTrue());
                resultBuilder.chatMessage(chatMessegeBuilder.buildPullRequestOpened(payload));
                return resultBuilder.build();
            }
        }
        if (event.isPullRequestReviewComment() || event.isIssueComment()) {
            resultBuilder.shouldSendMessageToChat(ShouldSendMessageToChat.ofTrue());
            resultBuilder.chatMessage(chatMessegeBuilder.buildPullRequestComment(payload));
            return resultBuilder.build();
        }

        if (event.isPush()) {
            Ref ref = Ref.of(payload.getValueAsString("ref"));
            if (ref.isMaster() || ref.isStaging() || ref.isProduction()) {
                resultBuilder.shouldSendMessageToChat(ShouldSendMessageToChat.ofTrue());
                resultBuilder.chatMessage(chatMessegeBuilder.buildPushToMaster(payload));
                return resultBuilder.build();
            }

        }
        resultBuilder.shouldSendMessageToChat(ShouldSendMessageToChat.of(Boolean.FALSE));
        return resultBuilder.build();
    }
}
