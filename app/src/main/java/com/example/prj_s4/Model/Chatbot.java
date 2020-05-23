package com.example.prj_s4.Model;

public class Chatbot {
    private  String msg_contenu;

    public Chatbot() {
    }

    public Chatbot(String msg_contenu) {
        this.msg_contenu = msg_contenu;
    }

    public String getMsg_contenu() {
        return msg_contenu;
    }

    public void setMsg_contenu(String msg_contenu) {
        this.msg_contenu = msg_contenu;
    }
}
