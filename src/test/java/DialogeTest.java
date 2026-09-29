import org.example.Dialog;
import org.example.FileCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DialogeTest {

    private InputStream origIn;
    private PrintStream origOut;

    @BeforeEach
    void saveStreams(){
        origIn = System.in;
        origOut = System.out;
    }

    @AfterEach
    void reastoreStreams(){
        System.setIn(origIn);
        System.setOut(origOut);
    }

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

    private String runWithInput(String input){
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out,true, StandardCharsets.UTF_8));

        return out.toString(StandardCharsets.UTF_8);
    }

    @Test

}
