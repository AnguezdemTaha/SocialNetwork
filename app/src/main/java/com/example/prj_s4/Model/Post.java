package com.example.prj_s4.Model;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    private Contenu contenu;
    private Page page;
    private Date date;


    public Post() {
    }

    public Post(Contenu contenu, Page page,Date date) {
        this.contenu = contenu;
        this.page = page;
        this.date =date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public void setContenu(Contenu contenu) {
        this.contenu = contenu;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
