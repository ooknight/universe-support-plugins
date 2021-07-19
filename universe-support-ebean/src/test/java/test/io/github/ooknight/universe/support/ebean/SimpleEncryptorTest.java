package test.io.github.ooknight.universe.support.ebean;

import io.github.ooknight.universe.support.ebean.JasyptEncryptor;
import io.github.ooknight.universe.support.ebean.SimpleEncryptKey;

import io.ebean.config.EncryptKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SimpleEncryptorTest {

    private static final EncryptKey KEY = new SimpleEncryptKey("123456");

    @Test
    void test1() {
        String plain = "123456";
        JasyptEncryptor encryptor = new JasyptEncryptor();
        byte[] byte1 = encryptor.encryptString(plain, KEY);
        String decrypted = encryptor.decryptString(byte1, KEY);
        assertEquals(plain, decrypted);
    }

    @Test
    void test2() {
        byte[] plain = "123456".getBytes();
        JasyptEncryptor encryptor = new JasyptEncryptor();
        byte[] byte1 = encryptor.encrypt(plain, KEY);
        byte[] decrypted = encryptor.decrypt(byte1, KEY);
        assertEquals(plain, decrypted);
    }

    @Test
    void test3() {
        JasyptEncryptor encryptor = new JasyptEncryptor();
        byte[] byte1 = encryptor.encryptString(null, KEY);
        String decrypted = encryptor.decryptString(byte1, KEY);
        assertNull(decrypted);
    }

    @Test
    void test4() {
        JasyptEncryptor encryptor = new JasyptEncryptor();
        byte[] byte1 = encryptor.encrypt(null, KEY);
        byte[] decrypted = encryptor.decrypt(byte1, KEY);
        assertNull(decrypted);
    }
}
