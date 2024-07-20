package com.java.utils.filemgmt;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileOps {
    public static void cleanEmptyFiles(Path path, int n, String include, String exclude) {

        Pattern incPattern = Pattern.compile(include);
        Pattern excPattern = Pattern.compile(exclude);

        if (path != null && Files.exists(path) && Files.isDirectory(path)) {

            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    private int currentDepth = 0;

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        if (currentDepth == n) {
                            return FileVisitResult.SKIP_SUBTREE; // Don't enter subdirectories beyond n levels
                        }
                        currentDepth++;
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                        if (!Files.isDirectory(file)) {
                            String fileName = file.getFileName().toString();
                            Matcher incMatcher = incPattern.matcher(fileName);
                            Matcher excMatcher = excPattern.matcher(fileName);
                            if (incMatcher.matches() && !excMatcher.matches() && file.toFile().length() == 0) {
                                System.out.println("File to delete: " + file); // Process file (example: print file to
                                                                               // delete)
                                try {
                                    Files.delete(file); // Delete the file
                                } catch (IOException e) {
                                    System.err.println("Failed to delete file: " + file);
                                }
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.err.println("Path is invalid or not a directory: " + path);
        }
    }
}
