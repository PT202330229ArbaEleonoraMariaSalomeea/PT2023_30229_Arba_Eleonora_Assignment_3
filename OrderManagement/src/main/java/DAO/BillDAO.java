package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import BusinessLogic.ClientBLL;
import Connection.ConnectionFactory;
import Model.Bill;
import Model.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BillDAO {

        public void insertLog( int idOrder, int price) {

        Bill bill = new Bill(  idOrder, price);

        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            String Query = "INSERT INTO logs (idOrder, price) VALUES (?, ?)";


            PreparedStatement statement = connection.prepareStatement(Query);
            statement.setInt(1,bill.idOrder());
            statement.setInt(2, bill.price() );



            statement.executeUpdate();
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();

        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = "SELECT * FROM logs";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bill bill = new Bill((Integer) resultSet.getObject(1), resultSet.getByte(2));
                bills.add(bill);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return bills;
    }

    public void displayAllBillsInTable(JTable table,List<Bill> bills) {

        String[][] matrix = new String[bills.size()][2];

        for (int i = 0; i < bills.size(); i++) {
            matrix[i][0] = String.valueOf(bills.get(i).idOrder());
            matrix[i][1] = String.valueOf(bills.get(i).price());


        }
        table.setModel(new DefaultTableModel(
                matrix, new String[]{"idOrder", "Price"}));
    }
}


