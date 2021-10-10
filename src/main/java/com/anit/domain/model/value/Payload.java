package com.anit.domain.model.value;

import com.anit.domain.model.value.action.PullRequestEventAction;

import java.util.Map;

/**
 * @author anit
 */
public class Payload extends AbstractValue<Map<?, ?>> {

    protected Payload(Map<String, ?> value) {
        super(value);
    }

    public static Payload of(Map<String, ?> value) {
        return new Payload(value);
    }

    public <T> T getValue(String key) {
        return (T) this.getValue().get(key);
    }

    public String getValueAsString(String key) {
        return (String) this.getValue(key);
    }

    public PullRequestEventAction getPullRequestAction() {
        return PullRequestEventAction.of(this.getValueAsString("action"));
    }

}
