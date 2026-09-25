package org.example;
import java.util.List;
import java.util.Scanner;
public class Dialog {
    private Collection collection;
    Scanner scanner = new Scanner(System.in);

    public Dialog(Collection collection) {
        this.collection = collection;
    }

    public void run(){
        while (true) {
            List<String> subjects = collection.listSubjects();
            System.out.print("Какой предмет тебя интересует? ");
            String subject = scanner.nextLine().trim();

            if (!subjects.contains(subject)) {
                System.out.println("Такого предмета нет.");
                continue;
            }

            List<String> authors = collection.listAuthors(subject);
            System.out.println("Авторы: " + authors);
            System.out.print("Выбери автора: ");
            String author = scanner.nextLine().trim();

            if (!authors.contains(author)) {
                System.out.println("Такого автора нет.");
                continue;
            }

            String content = collection.readContent(subject, author);
            System.out.println("---- " + subject + " / " + author + " ----");
            System.out.println(content);
            System.out.println("------------------------------------");
        }

    }
}
