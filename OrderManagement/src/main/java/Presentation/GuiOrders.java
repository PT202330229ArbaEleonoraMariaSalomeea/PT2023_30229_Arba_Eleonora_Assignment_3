package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DAO.BillDAO;
import Model.Bill;
import Model.Clients;
import Model.Orders;
import Model.Products;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GuiOrders {
    private JTable table1;
    private JTable table2;
    private JButton findAllClientsButton;
    private JButton findAllProductsButton;
    private JScrollPane tableClients;
    private JScrollPane tableProducts;
    private JTable TableOrders;
    private JButton findAllOrdersButton;
    private JTextField IdOrder;
    private JTextField IdClient;
    private JTextField IdProduct;
    private JTextField Price;
    private JTextField Quantity;
    private JButton insertOrderButton;
    private JTextField UpdateField;
    private JTextField UpdateValue;
    private JButton updateOrderButton;
    private JTextField DeleteField;
    private JTextField DeleteValue;
    private JButton deleteOrderButton;
    private JPanel PanelOrders;
    private JButton backButton;


    public GuiOrders() {

        MainInterface.changePanel(PanelOrders, "Orders");
        OrderBLL orderBLL = new OrderBLL();
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL= new ProductBLL();
        BillDAO billDAO=new BillDAO();
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBLL.deleteOrder(DeleteField.getText(), DeleteValue.getText());
            }
        });
        findAllOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Orders>  listOrders = orderBLL.findAllOrders();
                orderBLL.fillTable(TableOrders, listOrders);
            }
        });
        findAllClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Clients>  listCliets = clientBLL.findAllClients();
                clientBLL.fillTable(table1, listCliets);
            }
        });
        findAllProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Products>  listCliets = productBLL.findAllCProducts();
                productBLL.fillTable(table2, listCliets);
            }
        });
        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> list = new ArrayList<>();
                if(IdOrder.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(IdOrder.getText());
                }

                if(IdClient.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(IdClient.getText());
                }

                if(IdProduct.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(IdProduct.getText());
                }

                if(Price.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(Price.getText());
                }

                if(Quantity.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(Quantity.getText());
                }
                clientBLL.update(list,UpdateField.getText(),UpdateValue.getText());
//

            }
        });
        insertOrderButton.addComponentListener(new ComponentAdapter() {
        });
        insertOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Products p= null;
                p=productBLL.findProducttById(Integer.parseInt(IdProduct.getText()));
                Clients c=null;
                c=clientBLL.findClientById(Integer.parseInt(IdClient.getText()));

                if(p!=null && c!=null){
                    if(p.getStock()>=Integer.parseInt(Quantity.getText())){
                        Orders orderToInsert = new Orders(Integer.parseInt(IdOrder.getText()), Integer.parseInt(IdClient.getText()), Integer.parseInt(IdProduct.getText()),
                                 (Integer.parseInt(Quantity.getText())*p.getPrice()),  Integer.parseInt(Quantity.getText()));

                                billDAO.insertLog(Integer.parseInt(IdOrder.getText()), (Integer.parseInt(Quantity.getText())*p.getPrice()));
                                 orderBLL.insertOrders(orderToInsert);

                                     if(p.getStock()==Integer.parseInt(Quantity.getText())){
                                        productBLL.deleteProduct(IdProduct.getText(),"idProducts");
                                     }

                                     else {
                                        int newStockValue=p.getStock()-Integer.parseInt(Quantity.getText());
                                        ArrayList<String> stringProd = new ArrayList<>();
                                        stringProd.add("empty");
                                        stringProd.add("empty");
                                        stringProd.add("empty");
                                        stringProd.add(String.valueOf(newStockValue));
                                       productBLL.updateProduct(stringProd,"idProducts",IdProduct.getText());
                                }

                    }
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstGui back = new FirstGui();
            }
        });
    }

}
