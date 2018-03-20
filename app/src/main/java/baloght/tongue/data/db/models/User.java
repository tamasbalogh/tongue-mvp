package baloght.tongue.data.db.models;

import android.content.ContentValues;

import baloght.tongue.data.db.DBSchema;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class User {
    private int id;
    private String email, password, username;
    private String date;

    public User() {
    }

    public User(String email, String password, String username, String date) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ContentValues getValues(){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.TB_USER.EMAIL,this.email);
        cv.put(DBSchema.TB_USER.PASSWORD,this.password);
        cv.put(DBSchema.TB_USER.USERNAME,this.username);
        cv.put(DBSchema.TB_USER.DATE,this.date);
        return cv;
    }
}
