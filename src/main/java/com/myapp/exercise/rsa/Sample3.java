package com.myapp.exercise.rsa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Sample3 {

    static private Base64.Encoder encoder = Base64.getEncoder();

    static private void writeBinary(OutputStream out, Key key)
            throws java.io.IOException
    {
        out.write(key.getEncoded());
    }

    static public void main(String[] args) throws Exception
    {
//        if ( args.length != 4 ) {
//            System.err.println("generate digital signature.");
//            System.err.println("usage: java algo pvtKeyFile dataFile signFile");
//            System.exit(1);
//        }

//        int index = 0;
//        String algo = args[index]; index++;
//        String keyFile = args[index]; index++;
//        String dataFile = args[index]; index++;
//        String signFile = args[index]; index++;

        String algo = "RSA";
        Integer keySize = 1024;
        String outFile = "E:\\project\\exercise\\src\\main\\java\\com\\myapp\\exercise\\rsa\\";
        String dataFile = "";
        String signFile="";

        /* Read the private key bytes */
        Path path = Paths.get(outFile+"private.key");
        byte[] bytes = Files.readAllBytes(path);

        /* Generate private key. */
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(bytes));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pvt = kf.generatePrivate(ks);

        Signature sign = Signature.getInstance("SHA256withRSA");
//        sign.initSign(pvt);
//
//        InputStream in = null;
//        try {
//            in = new FileInputStream(dataFile);
//            byte[] buf = new byte[2048];
//            int len;
//            while ((len = in.read(buf)) != -1) {
//                sign.update(buf, 0, len);
//            }
//        } finally {
//            if ( in != null ) in.close();
//        }
//
//        OutputStream out = null;
//        try {
//            out = new FileOutputStream(signFile);
//            byte[] signature = sign.sign();
//            out.write(signature);
//        } finally {
//            if ( out != null ) out.close();
//        }
    }
}
