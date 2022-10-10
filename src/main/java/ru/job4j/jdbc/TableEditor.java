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
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        try (var statement = connection.createStatement()) {
            statement.execute(String.format(
                    "create table %s (%s);", tableName, "id serial primary key"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (var statement = connection.createStatement()) {
            statement.execute(String.format(
                    "drop table %s;", tableName
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (var statement = connection.createStatement()) {
            statement.execute(String.format(
                    "alter table %s add column %s %s;", tableName, columnName, type
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (var statement = connection.createStatement()) {
            var dropColumn = statement.execute(String.format(
                    "alter table %s drop column %s;", tableName, columnName
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (var statement = connection.createStatement()) {
            statement.execute(String.format(
                    "alter table %s rename column %s to %s;", tableName, columnName, newColumnName
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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