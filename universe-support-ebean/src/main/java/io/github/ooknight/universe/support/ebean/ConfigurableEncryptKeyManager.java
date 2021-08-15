package io.github.ooknight.universe.support.ebean;

import io.ebean.config.EncryptKey;
import io.ebean.config.EncryptKeyManager;

public class ConfigurableEncryptKeyManager implements EncryptKeyManager {

    private final EncryptKey key;

    public ConfigurableEncryptKeyManager(String secret) {
        key = new SimpleEncryptKey(secret);
    }

    @Override
    public EncryptKey getEncryptKey(String tableName, String columnName) {
        return key;
    }
}
