package com.incubator.task.DAO;

import java.sql.*;
import java.util.ArrayList;

public class BankJdbc {

    //Параметры для подключения к базе данных
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bank?useUnicode=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    Connection connection = null;
    Statement statement = null;

    //Вход(XD) в базу данных
    private void creatConnect() {

        System.out.println("Creating database connection...");
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Executing statement...");
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Выход(XD) из базы данных
    private void closeConnect() {
        System.out.println("Closing connection and releasing resources...");

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Поиск по ID
    public String searchUserById(int userid) throws SQLException {
        //Коннектимся к базе
        creatConnect();

        //Находим юзера по ID и получаем данные о нём из базы
        String sql;
        sql = "SELECT * FROM user WHERE userid = " + userid;

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");

        String userInfo = "";

        //Обрабатываем данные из базы
        while (resultSet.next()) {

            int id = resultSet.getInt("userid");
            String name = resultSet.getString("name");
            String surName = resultSet.getString("sureName");

            userInfo = " ID: " + id + ". Name: " + name + ". Surname: " + surName + ".";

        }
        //Выходим и базы
        resultSet.close();
        closeConnect();

        return userInfo;
    }

    //Метод получения всех аккаунтов из базы
    public ArrayList searchAllAccounts() throws SQLException {
        //Коннектимся к базе
        creatConnect();
        
        //Получаем данные из базы
        String sql;
        sql = "SELECT * FROM account ";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");

        ArrayList<ArrayList> accountList = new ArrayList<ArrayList>();

        //Обрабатываем данные из базы и запихаваем их в массив
        while (resultSet.next()) {

            int id = resultSet.getInt("accountid");
            int account = resultSet.getInt("account");
            int userId = resultSet.getInt("userId");

            ArrayList array = new ArrayList();

            array.add(id);
            array.add(account);
            array.add(userId);

            accountList.add(array);

        }

        //Выходим и базы
        resultSet.close();
        closeConnect();

        return accountList;
    }
}
