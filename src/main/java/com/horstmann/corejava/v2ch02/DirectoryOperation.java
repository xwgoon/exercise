package com.horstmann.corejava.v2ch02;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class DirectoryOperation {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get("test/cities.zip").toAbsolutePath();
//        System.out.println(source);
        Path target = Paths.get("F:/test").toAbsolutePath();
//        System.out.println(target);

//        copyDirectory(source, target);

        FileSystem fs0 = FileSystems.newFileSystem(source, null);
//        FileSystem fs1 = FileSystems.newFileSystem(target, null);
//        Files.walk(fs0.getPath("/")).forEach(System.out::println);
//        Files.copy(fs0.getPath("cities.txt"), target);
//        copyDirectory(source, target);
        copyDirectory(fs0.getPath("/"), target);

    }

    /**
     * 复制文件夹或压缩文件中的内容，将 source 文件夹或压缩文件中的内容复制到 target 文件夹中
     */
    private static void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source).forEach(it -> {
            Path p0 = source.relativize(it);
            Path p1 = target.resolve(p0.toString());
            try {
                //不覆盖已存在的文件
//                if (!Files.exists(p1)) {
//                    if (Files.isDirectory(it)) {
//                        Files.createDirectory(p1);
//                    } else {
//                        Files.copy(it, p1);
//                    }
//                }

                //覆盖已存在的文件
                if (Files.isDirectory(it)) {
                    Files.createDirectories(p1);
                } else {
                    Files.copy(it, p1, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

        });
    }

    /**
     * 移动文件夹，将 source 文件夹移动到 target 文件夹下
     */
    private static void moveDirectory(Path source, Path target) throws IOException {
        Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
    }

    /**
     * 删除文件夹，删除 source 文件夹
     */
    private static void deleteDirectory(Path source) throws IOException {

        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) throw exc;
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }


}
