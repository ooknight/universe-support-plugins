package io.github.ooknight.universe.support.utils.component;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;

public final class CryptoUtils {

    public RSA rsa(String publicKey, String privateKey) {
        return new RSA(publicKey, privateKey);
    }

    public RSA rsa() {
        return new RSA();
    }

    public AES aes() {
        return new AES();
    }

    public AES aes(String key) {
        return new AES(key);
    }

    //public enum KEY {
    //
    //    PUBLIC(KeyType.PublicKey), PRIVATE(KeyType.PrivateKey);
    //
    //    private final KeyType type;
    //
    //    KEY(KeyType type) {
    //        this.type = type;
    //    }
    //
    //    KeyType type() {
    //        return type;
    //    }
    //
    //}

    public static class RSA {

        private final cn.hutool.crypto.asymmetric.RSA rsa;

        private RSA() {
            rsa = new cn.hutool.crypto.asymmetric.RSA();
        }

        private RSA(String publicKey, String privateKey) {
            rsa = new cn.hutool.crypto.asymmetric.RSA(Base64.decode(privateKey), Base64.decode(publicKey));
        }

        public byte[] encrypt(byte[] data, boolean key) {
            return rsa.encrypt(data, KeyType.PrivateKey);
        }

        public byte[] encrypt(byte[] data) {
            return rsa.encrypt(data, KeyType.PublicKey);
        }

        public byte[] decrypt(byte[] data) {
            return rsa.decrypt(data, KeyType.PrivateKey);
        }

        public byte[] decrypt(byte[] data, boolean key) {
            return rsa.decrypt(data, KeyType.PublicKey);
        }

        public String publicKey() {
            return rsa.getPublicKeyBase64();
        }

        public String privateKey() {
            return rsa.getPrivateKeyBase64();
        }
    }

    public static class AES {

        private final cn.hutool.crypto.symmetric.AES aes;

        private AES() {
            aes = new cn.hutool.crypto.symmetric.AES();
        }

        private AES(String key) {
            aes = new cn.hutool.crypto.symmetric.AES(Base64.decode(key));
        }

        public byte[] encrypt(byte[] value) {
            return aes.encrypt(value);
        }

        public byte[] decrypt(byte[] value) {
            return aes.decrypt(value);
        }

        public String key() {
            return Base64.encode(aes.getSecretKey().getEncoded());
        }
    }
}
