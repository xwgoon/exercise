package com.horstmann.corejava.v2ch02;

import com.horstmann.corejava.v1ch06.MyInterface;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.codec.Charsets;

import java.io.*;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(File.separator);

//        int unicode = '知';
//        System.out.println(unicode);
//        String hex = Integer.toHexString(unicode);
//        System.out.println(hex);
//        System.out.println((char) Integer.parseInt(hex, 16));

//        String s = "os";
//        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
//        System.out.println(Arrays.toString(bytes));
//        String s1 = new String(bytes, StandardCharsets.UTF_8);
//        System.out.println(s1);

//        String charset = "UTF-8";
//        Path path = Paths.get("test.txt");
//        try (PrintWriter out = new PrintWriter("test.txt", charset);
//             Scanner in = new Scanner(path, charset)) {
//            out.println(new Object() {
//            }.getClass().getEnclosingClass().getName());
//            out.println("Hello World!");
//            out.flush();
//
//            System.out.println("Scanner: ");
//            while (in.hasNextLine()) {
//                System.out.println(in.nextLine());
//            }
//
//            System.out.println("readAllBytes: ");
//            String content = new String(Files.readAllBytes(path), charset);
//            System.out.println(content);
//
//            System.out.println("readAllLines: ");
//            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//            lines.forEach(System.out::println);
//
//            System.out.println("lines: ");
//            Stream<String> sl = Files.lines(path, StandardCharsets.UTF_8);
//            sl.forEach(System.out::println);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try (DataInputStream in = new DataInputStream(new FileInputStream("employee.dat"))) {
//            File file = new File("employee.dat");
//            System.out.println(file.length());
//            byte[] bytes = new byte[(int) file.length()];
//            in.readFully(bytes);
//            String s = new String(bytes, StandardCharsets.UTF_16);
//            System.out.println(s);
//        }

//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("enum.dat"))) {
//            out.writeObject(Gender.FEMALE);
//        }
//
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("enum.dat"))) {
//            Gender gender = (Gender) in.readObject();
//            System.out.println(gender);
//            System.out.println(gender == Gender.FEMALE);
//        }

//        Path path=Paths.get("/myapp/test");
//        System.out.println(path);
//
//        path=Paths.get("/myapp", "test");
//        System.out.println(path);
//
//        path=Paths.get("myapp", "test");
//        System.out.println(path);
//
//        path=path.resolve("java");
//        System.out.println(path);
//
//        path=path.resolveSibling("c++");
//        System.out.println(path);
//
//        path=path.toAbsolutePath();
//        System.out.println(path);
//
//        path=path.getParent();
//        System.out.println(path);
//
//        System.out.println(path.toFile());
//
//        Path path1=Paths.get("com", "myapp");
//        Path path2=Paths.get("com", "company");
//        System.out.println(path1.relativize(path2));
//
//        System.out.println(path2.getFileName());
//
//        System.out.println(path.getRoot());
//        Path path = Paths.get("test.txt");
//        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//        lines.forEach(System.out::println);
//
//        String content = "write some words.";
//        List<String> contents=new ArrayList<>();
//        contents.add("\nHello");
//        contents.add("World!");
//        Files.write(path, contents, StandardOpenOption.APPEND);
//        lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//        lines.forEach(System.out::println);


//        Path path0 = Paths.get("test");
//        Path filePath = directoryPath.resolve("test.txt");
//        Files.createDirectories(directoryPath);
//        Files.createFile(filePath);

//        String content = "Hello World!\n";
//        Files.write(filePath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
//
//        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
//        lines.forEach(System.out::println);

        Path path1 = Paths.get("test2", "test");
//        Files.copy(path0, path1, StandardCopyOption.REPLACE_EXISTING);
//        Files.move(path1, path0, StandardCopyOption.ATOMIC_MOVE);
        Files.deleteIfExists(path1);

//        System.out.println(Files.size(filePath));

//        Path path=Paths.get("d3.txt");
//        Files.createFile(path);



    }
}

//enum Gender {
//
//    MALE(0, "男"),
//    FEMALE(1, "女");
//
//    private int value;
//    private String label;
//
//    Gender(int value, String label) {
//        this.value = value;
//        this.label = label;
//    }
//
//    public int getValue() {
//        return value;
//    }
//
//    public String getLabel() {
//        return label;
//    }
//}
