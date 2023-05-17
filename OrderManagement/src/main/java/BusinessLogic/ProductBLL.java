package BusinessLogic;

import DAO.ProductDAO;
import Model.Clients;
import Model.Products;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    public Products findProducttById(int id) {
        Products product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    public List<Products> findAllCProducts() {
        List<Products> product = productDAO.findAll();
        if (product == null) {
            throw new NoSuchElementException("Table Products is empty.");
        }
        return product;
    }

    public void insertProduct(Products product) {
        Products st = productDAO.insert(product);
    }
    public Products deleteProduct(String id, String field) {
        return productDAO.delete(id, field);
    }

    public Products updateProduct (  ArrayList<String> values, String whereClause, String value){
        return productDAO.update( values, whereClause, value);
    }
    public  <T> void fillTable(JTable table1, List<Products> list){
        productDAO.fillTable(table1, list);

    }
}
