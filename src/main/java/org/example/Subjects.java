package org.example;

public class Subjects {
    private String m_subject;
    private String m_author;

    public Subjects(String subject, String author){
        m_subject = subject;
        m_author = author;
    }

    public String getSubject(){
        return m_subject;
    }

    public String getAuthor() {
        return m_author;
    }
}
