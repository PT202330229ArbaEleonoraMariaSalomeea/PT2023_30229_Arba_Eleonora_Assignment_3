package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Model.Clients;
import Model.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GuiProduct {

    private JTextField NameValIns;
    private JTextField PriceValIns;
    private JTextField StockValIns;
    private JButton insertProductButton;
    private JButton updateProductButton;
    private JButton deleteProductButton;
    private JTextField DelFieldVal;
    private JTextField DelValVal;
    private JTextField IdFindVal;
    private JButton findByIdButton;
    private JButton findAllButton;
    private JLabel DeleteValue;
    private JLabel PriceIns;
    private JLabel StockIns;
    private JLabel DelField;
    private JPanel PanelProducts;
    private JTextField ValidIns;
    private JButton backButton;
    private JTable table1;
    private JTextField Update;
    private JTextField UpdateVal;

    public GuiProduct() {


        MainInterface.changePanel(PanelProducts, "Products");
        ProductBLL productBLL = new ProductBLL();


        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                productBLL.deleteProduct(DelFieldVal.getText(), DelValVal.getText());

            }
        });


        insertProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Products productToInsert = new Products(Integer.parseInt(ValidIns.getText()), NameValIns.getText(), Integer.parseInt(PriceValIns.getText()),Integer.parseInt(StockValIns.getText()));
                productBLL.insertProduct(productToInsert);
            }
        });


        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    ArrayList<String> list = new ArrayList<>();
                    if(ValidIns.getText().isEmpty()){
                        list.add("empty");
                    }
                    else{
                        list.add(ValidIns.getText());
                    }

                if(NameValIns.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(NameValIns.getText());
                }

                if(PriceValIns.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(PriceValIns.getText());
                }

                if(StockValIns.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(StockValIns.getText());
                }


                    productBLL.updateProduct(list,Update.getText(),UpdateVal.getText());

            }
        });



        findAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Products>  listCliets = productBLL.findAllCProducts();
                productBLL.fillTable(table1, listCliets);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstGui fg = new FirstGui();
            }
        });
    }


}
