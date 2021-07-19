package io.github.ooknight.universe.support.ebean;

import io.ebean.config.EncryptKey;

public class SimpleEncryptKey implements EncryptKey {

    private final String secret;

    public SimpleEncryptKey(String secret) {
        this.secret = secret;
    }

    @Override
    public String getStringValue() {
        return secret;
    }
}
