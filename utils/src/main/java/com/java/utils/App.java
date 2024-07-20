package com.java.utils;

import java.nio.file.Path;

import com.java.utils.filemgmt.FileOps;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        FileOps.cleanEmptyFiles(Path.of("/Users/mayankmadan/Downloads"), 3, ".*", ".*/node_modules/.*");
    }
}
