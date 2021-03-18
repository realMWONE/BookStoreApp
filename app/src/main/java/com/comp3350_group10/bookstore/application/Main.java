package com.comp3350_group10.bookstore.application;

public class Main {
    private static String dbName="WS";

    public static void setDBPath(final String name) {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPath() {
        return dbName;
    }

}
