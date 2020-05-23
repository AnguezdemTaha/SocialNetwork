package com.example.prj_s4.Model;

import android.media.Image;
import android.provider.MediaStore;

import java.util.ArrayList;

public class Contenu {
    private String text;
    private ArrayList<String> images;
    private ArrayList<String> vidos;
    private ArrayList<String> files;

    public Contenu() {
    }

    public Contenu(String text, ArrayList<String> images, ArrayList<String> vidos, ArrayList<String> files) {
        this.text = text;
        this.images = images;
        this.vidos = vidos;
        this.files = files;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getVidos() {
        return vidos;
    }

    public void setVidos(ArrayList<String> vidos) {
        this.vidos = vidos;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }
}
