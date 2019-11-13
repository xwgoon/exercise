package com.myapp.exercise.rsa;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RsaTest {

    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        Base64.Encoder encoder = Base64.getEncoder();

        System.out.println("Public key format: " + publicKey.getFormat()); //"Public key format: X.509"
        System.out.println("Public key: " + encoder.encodeToString(publicKey.getEncoded()));

        System.out.println("Private key format: " + privateKey.getFormat()); //"Private key format: PKCS#8"
        System.out.println("Private key: " + encoder.encodeToString(privateKey.getEncoded()));


//        String outFolder = "E:\\project\\exercise\\src\\main\\java\\com\\myapp\\exercise\\rsa";
//
//        String pubKeyFile=outFolder+"public.key";
//        FileOutputStream out = new FileOutputStream(pubKeyFile);
//        out.write(pubKey.getEncoded());
//        out.close();
//
//        String pvtKeyFile=outFolder+"private.key";
//        out = new FileOutputStream(pvtKeyFile);
//        out.write(pvtKey.getEncoded());
//        out.close();


        /* Read all the public key bytes */
//        Path path = Paths.get(pubKeyFile);
//        byte[] bytes = Files.readAllBytes(path);

        /* Generate public key. */
//        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bytes);
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        PublicKey publicKey = kf.generatePublic(x509EncodedKeySpec);


        /* Read all bytes from the private key file */
//        path = Paths.get(pvtKeyFile);
//        bytes = Files.readAllBytes(path);

        /* Generate private key. */
//        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
//        kf = KeyFactory.getInstance("RSA");
//        PrivateKey privateKey = kf.generatePrivate(pkcs8EncodedKeySpec);


//        out = new FileWriter(outFile + ".pub");
//        out.write("-----BEGIN RSA PUBLIC KEY-----\n");
//        out.write(encoder.encodeToString(kp.getPublic()));
//        out.write("\n-----END RSA PUBLIC KEY-----\n");
//        out.close();


//        Base64.Encoder encoder = Base64.getEncoder();

//        Writer out = new FileWriter(outFile + ".key");
//        out.write("-----BEGIN RSA PRIVATE KEY-----\n");
//        out.write(encoder.encodeToString(pvt.getEncoded()));
//        out.write("\n-----END RSA PRIVATE KEY-----\n");
//        out.close();

    }
}
