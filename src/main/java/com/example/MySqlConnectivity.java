package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlConnectivity {
    public static void main(final String[] args) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            PreparedStatement ps = conn.prepareStatement("select * from testdb.user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);

                System.out.println(id + ", " + name);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
