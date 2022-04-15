package edu.auth.csd.datalab.db.utils.interfaces;

public interface MyDatabase {

    public String getData(String key);
    public void putData(String key, String value);
    public void close();

}
