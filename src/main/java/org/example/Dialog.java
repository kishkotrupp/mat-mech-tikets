package org.example;
import java.util.List;
import java.util.Scanner;
public class Dialog {
    private Collection collection;
    Scanner scanner = new Scanner(System.in);
    public Dialog(Collection collection) {
        this.collection = collection;
    }
    private void printHelp(){
        System.out.println("=== СПРАВКА ===\n 1) Введи название нужного предмета.\n" +
                "2) Выбери нужного для тебя автора.\n" +
                "3) Выучи билет и сдай экзамен на отлично!");
    }
    private boolean isHelp(String input){
        if (input.equalsIgnoreCase("\\help")) {
            printHelp();
            return true;
        }
        return false;
    }
    public void run(){
        System.out.println("Привет! Я - бот, созданный для упрощения подготовки к экзаменам на великом матмехе!\n" +
                "Я помогу найти расписанные билеты!\n" +
                "Введи '\\help' для справки!");
        String subject = null;
        List<String> authors = null;
        while (true) {
            if (subject == null){
                List<String> subjects = collection.listSubjects();
                System.out.print("Какой предмет тебя интересует? ");
                String input = scanner.nextLine().trim();

                if (isHelp(input)) continue;

                if (!subjects.contains(input)) {
                    System.out.println("Такого предмета нет.");
                    continue;
                }
                subject = input;
                authors = collection.listAuthors(subject);
                System.out.println("Авторы: " + authors);
                continue;
            }

            System.out.print("Выбери автора: ");
            String author = scanner.nextLine().trim();

            if (isHelp(author)) continue;

            if (!authors.contains(author)) {
                System.out.println("Такого автора нет.");
                continue;
            }

            String content = collection.readContent(subject, author);
            System.out.println("---- " + subject + " / " + author + " ----");
            System.out.println(content);
            System.out.println("------------------------------------");

            subject = null;
        }

    }
}
