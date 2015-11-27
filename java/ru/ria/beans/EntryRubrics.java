package ru.ria.beans;

public class EntryRubrics {
    private String sectionRef;
    private String title;
    private Integer number;

    public EntryRubrics(String sectionRef, String title) {
        this.sectionRef = sectionRef;
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSectionRef() {
        return sectionRef;
    }

    public void setSectionRef(String sectionRef) {
        this.sectionRef = sectionRef;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EntryRubricsAdapter{" +
                "sectionRef='" + sectionRef + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
