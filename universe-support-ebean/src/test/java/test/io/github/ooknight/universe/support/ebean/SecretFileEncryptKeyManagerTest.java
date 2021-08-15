package test.io.github.ooknight.universe.support.ebean;

import io.github.ooknight.universe.support.ebean.SecretFileEncryptKeyManager;
import static io.github.ooknight.universe.support.utils.COMBINE.x;

import io.ebean.config.EncryptKeyManager;
import org.junit.jupiter.api.Test;

public class SecretFileEncryptKeyManagerTest {

    @Test
    void test() {
        EncryptKeyManager encryptKeyManager = new SecretFileEncryptKeyManager();
        x.console.echo(encryptKeyManager);
    }
}
