package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCollection implements Collection{

    private String BASE_DIR;
    public FileCollection() {
        this("C:/Users/platon/Documents/subjects");
    }

    public FileCollection(String baseDir) {
        BASE_DIR = baseDir;
    }

    @Override
    public List<String> listSubjects() {
        List<String> result = new ArrayList<>();
        File base = new File(BASE_DIR);
        if (!base.isDirectory()) {
            return result; // папки нет — вернём пустой список
        }
        File[] items = base.listFiles();
        if (items == null) {
            return result;
        }
        for (File item : items) {
            if (item.isDirectory()) {
                result.add(item.getName());
            }
        }
        return result;
    }

    @Override
    public List<String> listAuthors(String subject) {
        List<String> result = new ArrayList<>();
        File base = new File(BASE_DIR, subject);
        if (!base.isDirectory()) {
            return result; // папки нет — вернём пустой список
        }
        File[] items = base.listFiles();
        if (items == null) {
            return result;
        }
        for (File item : items) {
            String name = item.getName();
            if (name.endsWith(".txt")) {
                result.add(name.substring(0, name.length() - 4)); // убрать ".txt"
            }
        }
        return result;
    }

    @Override
    public String readContent(String subject, String author) {
        File file = new File(BASE_DIR, subject + "/" + author + ".txt");
        try {
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл " + file, e);
        }
    }
}
