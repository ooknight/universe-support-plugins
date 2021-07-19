package test.io.github.ooknight.universe.support.ebean;

import io.github.ooknight.universe.support.ebean.SimpleEncryptKeyManager;

import io.ebean.config.EncryptKeyManager;
import org.junit.jupiter.api.Test;

import static oo.UTILS.x;

public class SimpleEncryptKeyManagerTest {

    @Test
    void test() {
        EncryptKeyManager encryptKeyManager = new SimpleEncryptKeyManager();
        x.console.echo(encryptKeyManager);
    }
}
