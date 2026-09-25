import org.example.FileCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileCollectionTest {
    @TempDir
    Path tempDir;

    private FileCollection collection;
    // подготовка виртуальной директории
    private void CreateVDir() throws IOException {
        Files.createDirectory(tempDir.resolve("math"));
        Files.createDirectory(tempDir.resolve("physics"));

        Files.writeString(tempDir.resolve("math/Abby.txt"),
                "Abby tolk about geometry", StandardCharsets.UTF_8);
        Files.writeString(tempDir.resolve("math/Andrey.txt"),
                "Andrey tolk about algebra", StandardCharsets.UTF_8);
        Files.writeString(tempDir.resolve("physics/Bibob.txt"),
                "Bibob tolk about newton", StandardCharsets.UTF_8);
        // файл в корне — не должен попасть в список предметов
        Files.writeString(tempDir.resolve("readme.md"), "you must dont know wtf is there");

        collection = new FileCollection(tempDir.toString());
    }
    @Test
    void ListSubjectsReturnOnlyDirectories() throws IOException {
        CreateVDir();
        List<String> subjects = collection.listSubjects();
        Assertions.assertEquals(2, subjects.size());
        assertTrue(subjects.contains("math"));
        assertTrue(subjects.contains("physics"));
        assertFalse(subjects.contains("readme.md"));
    }
    // создаем путь которого не существует
    @Test
    void ListSubjectReturnEmptyWhenDirDosntExist() throws IOException{
        FileCollection c = new FileCollection(tempDir.resolve("doesnt_exist").toString());
        assertTrue(c.listSubjects().isEmpty());
    }
    //создаем пустую папку
    @Test
    void ListSubjectReturnEmptyWhenDirIsEmpty() throws IOException{
        Files.createDirectory(tempDir.resolve("empty_dir"));
        FileCollection c = new FileCollection(tempDir.resolve("empty_dir").toString());
        assertTrue(c.listSubjects().isEmpty());
    }
    //проверяем есть ли все авторы и то что они выводятся без txt
    @Test
    void ListAuthorsReturnAllAuthors() throws IOException {
        CreateVDir();
        List<String> authors = collection.listAuthors("math");
        Assertions.assertEquals(2, authors.size());
        assertTrue(authors.contains("Abby"));
        assertTrue(authors.contains("Andrey"));
        assertFalse(authors.contains("Abby.txt"));
    }
    //авторов не должно быть по несуществующей папке
    @Test
    void ListAuthorsReturnEmptyWhenDirDosntExist() throws IOException{
        CreateVDir();
        List<String> authors = collection.listAuthors("doesnt_exist");
        assertTrue(authors.isEmpty());
    }
    //другие форматы кроме txt должны игнорироваться
    @Test
    void ListAuthorsIgnoreNoTxtFormat() throws IOException{
        CreateVDir();
        Files.writeString(tempDir.resolve("math/VolanDeMort.md"), "не txt");
        List<String> authors = collection.listAuthors("math");
        assertFalse(authors.contains("VolanDeMort"));
    }
}
