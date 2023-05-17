package BusinessLogic;

import BusinessLogic.validators.Validator;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Clients;
import Model.Orders;
import Model.Products;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private List<Validator<Products>> validators;
    private OrderDAO orderDAO;

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    public Orders findOrdertById(int id) {
        Orders order = orderDAO.findById(id);
        if (order == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return order;
    }

    public List<Orders> findAllOrders() {
        List<Orders> orders = orderDAO.findAll();
        if (orders == null) {
            throw new NoSuchElementException("Table Orders is empty.");
        }
        return orders;
    }

    public void insertOrders(Orders order) {
        Orders st = orderDAO.insert(order);
    }
    public Orders deleteOrder(String id, String field) {
        return orderDAO.delete(id, field);
    }

    public Orders updateOrder (String table, ArrayList<String> values, String whereClause, String value){
        return orderDAO.update( values, whereClause, value);
    }

    public  <T> void fillTable(JTable table1, List<Orders> list){
        orderDAO.fillTable(table1, list);
    }

        public void insertOrder(Orders order) {
        Orders st = orderDAO.insert(order);
    }
}
