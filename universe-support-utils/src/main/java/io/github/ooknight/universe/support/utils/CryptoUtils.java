package io.github.ooknight.universe.support.utils;

import cn.hutool.crypto.asymmetric.KeyType;

public final class CryptoUtils {

    public RSA rsa(String privateKey, String publicKey) {
        return new RSA(privateKey, publicKey);
    }

    public RSA rsa() {
        return new RSA();
    }

    public AES aes() {
        return new AES();
    }

    public static class RSA {

        private final cn.hutool.crypto.asymmetric.RSA rsa;

        private RSA() {
            rsa = new cn.hutool.crypto.asymmetric.RSA();
        }

        private RSA(String publicKey, String privateKey) {
            rsa = new cn.hutool.crypto.asymmetric.RSA(privateKey, publicKey);
        }

        public byte[] encryptByPrivateKey(byte[] value) {
            return rsa.encrypt(value, KeyType.PrivateKey);
        }

        public byte[] encryptByPublicKey(byte[] value) {
            return rsa.encrypt(value, KeyType.PublicKey);
        }

        public byte[] decryptByPrivateKey(byte[] value) {
            return rsa.decrypt(value, KeyType.PrivateKey);
        }

        public byte[] decryptByPublicKey(byte[] value) {
            return rsa.decrypt(value, KeyType.PublicKey);
        }
    }

    public static class AES {

        private final cn.hutool.crypto.symmetric.AES aes;

        private AES() {
            aes = new cn.hutool.crypto.symmetric.AES();
        }

        public byte[] encrypt(byte[] value) {
            return aes.encrypt(value);
        }

        public byte[] decrypt(byte[] value) {
            return aes.decrypt(value);
        }
    }
}
