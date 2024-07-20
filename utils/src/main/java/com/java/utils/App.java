package com.java.utils;

import com.java.utils.filemgmt.FileOps;

import java.io.IOException;
import java.nio.file.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        FileOps.cleanEmptyFiles(Path.of("/Users/mayankmadan"), 0, ".*", ".*/node_modules/.*");
    }
}
