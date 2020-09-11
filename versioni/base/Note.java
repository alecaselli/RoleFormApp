package com.example.roleapp.domain;

public class Note {
    private String note;

    public Note(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void aggiornaNote(String note){
        this.note = this.note + " " + note;
    }
}
