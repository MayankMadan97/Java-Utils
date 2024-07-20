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

    public static boolean cleanEmptyFiles(Path path, int n, String include, String exclude) {
        boolean deleteSuccess = false;

        Pattern incPattern = Pattern.compile(include);
        Pattern excPattern = Pattern.compile(exclude);

        if (path != null && !path.toString().isEmpty() && Files.exists(path) && Files.isDirectory(path)) {

            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    private int currentDepth = 0;

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        if (n != 0 && currentDepth == n) {
                            return FileVisitResult.SKIP_SUBTREE; // Don't enter subdirectories beyond n levels
                        }
                        currentDepth++;
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                        Matcher incMatcher = incPattern.matcher(path.toString());
                        Matcher excMatcher = excPattern.matcher(path.toString());
                        if (!Files.isDirectory(path) && incMatcher.matches()
                                && !excMatcher.matches() && path.toFile().length() == 0) {
                            System.out.println("File: " + file); // Process file
                            System.out.println("Exclude -count: " + excMatcher.matches());
                        }
                        return FileVisitResult.CONTINUE;
                    }

                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return deleteSuccess;
    }

}
