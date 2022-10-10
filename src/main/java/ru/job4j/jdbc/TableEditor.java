package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void createStatement(String request) {
        try (var statement = connection.createStatement()) {
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        createStatement(String.format("create table %s (%s);", tableName, "id serial primary key"));
    }

    public void dropTable(String tableName) {
        createStatement(String.format(
                "drop table %s;", tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) {
        createStatement(String.format(
                "alter table %s add column %s %s;", tableName, columnName, type
        ));
    }

    public void dropColumn(String tableName, String columnName) {
        createStatement(String.format(
                "alter table %s drop column %s;", tableName, columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        createStatement(String.format(
                "alter table %s rename column %s to %s;", tableName, columnName, newColumnName
        ));
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1;", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor te = new TableEditor(readFile())) {
            te.createTable("books");
            System.out.println(getTableScheme(te.connection, "books"));
            te.addColumn("books", "name", "varchar(255)");
            te.addColumn("books", "author", "varchar(255)");
            te.addColumn("books", "price", "float");
            te.addColumn("books", "shop", "varchar(255)");
            System.out.println(getTableScheme(te.connection, "books"));
            te.renameColumn("books", "name", "name_book");
            System.out.println(getTableScheme(te.connection, "books"));
            te.dropColumn("books", "shop");
            System.out.println(getTableScheme(te.connection, "books"));
            te.dropTable("books");
        }
    }

    private static Properties readFile() throws IOException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        return config;
    }
}