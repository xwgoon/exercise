package com.myapp.exercise.rsa;

import java.io.*;
import java.security.*;
import java.util.Base64;

public class GenerateKeys {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
    }

    public void createKeys() {
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public void writeBase64(String fileName, Key key)
            throws java.io.IOException
    {
        String outFile = "E:\\project\\exercise\\src\\main\\java\\com\\myapp\\exercise\\rsa\\";
        Writer out = new FileWriter(outFile + fileName);
        byte[] buf = key.getEncoded();

        Base64.Encoder encoder = Base64.getEncoder();
        out.write(encoder.encodeToString(buf));
        out.write("\n");

        out.close();
    }


    public static void main(String[] args) {
        GenerateKeys gk;
        try {
            gk = new GenerateKeys(1024);
            gk.createKeys();
//            gk.writeToFile("KeyPair/publicKey", gk.getPublicKey().getEncoded());
//            gk.writeToFile("KeyPair/privateKey", gk.getPrivateKey().getEncoded());

            gk.writeBase64("public.key", gk.getPublicKey());
            gk.writeBase64("private.key", gk.getPrivateKey());

        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

}
