package com.kanfa.news.app.mongoDB.entity;

import org.bson.Document;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/4/8 10:54
 */
public class DetailOfLog implements Serializable{
   private String field;
   private Object old;
   private Document document;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getOld() {
        return old;
    }

    public void setOld(Object old) {
        this.old = old;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
