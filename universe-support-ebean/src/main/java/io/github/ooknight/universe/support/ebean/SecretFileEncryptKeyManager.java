package io.github.ooknight.universe.support.ebean;

import cn.hutool.core.io.file.FileReader;
import io.ebean.config.EncryptKey;
import io.ebean.config.EncryptKeyManager;

public class SecretFileEncryptKeyManager implements EncryptKeyManager {

    private EncryptKey key;

    @Override
    public EncryptKey getEncryptKey(String table, String column) {
        return key;
    }

    @Override
    public void initialise() {
        FileReader reader = new FileReader("ebean.secret");
        String secret = reader.readString().trim();
        this.key = new SimpleEncryptKey(secret);
    }
}
