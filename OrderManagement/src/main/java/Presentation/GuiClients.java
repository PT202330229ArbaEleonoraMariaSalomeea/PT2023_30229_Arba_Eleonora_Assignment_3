package Presentation;

import BusinessLogic.ClientBLL;
import Model.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GuiClients {

    private JTextField Age1;
    private JTextField Name;
    private JTextField Email;
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
    private JTextField Id;
    private JTable table1;
    private JTextField Address;
    private JPanel Age;
    private JButton backButton;
    private JTextField Update;
    private JTextField UpdateVal;

    public GuiClients() {


        MainInterface.changePanel(PanelProducts, "Products");
        ClientBLL clientBLL = new ClientBLL();


        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clientBLL.delete(DelFieldVal.getText(), DelValVal.getText());

            }
        });


        insertProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients clienToInsert = new Clients(Integer.parseInt(Id.getText()), Integer.parseInt(Age1.getText()),Name.getText(),Email.getText(),Address.getText());
                clientBLL.insert(clienToInsert);
            }
        });


        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> list = new ArrayList<>();
                if(Id.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(Id.getText());
                }

                if(Age1.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(Age1.getText());
                }

                if(Name.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(Name.getText());
                }

                if(Email.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(Email.getText());
                }

                if(Address.getText().isEmpty()){
                    list.add("empty");
                }
                else{
                    list.add(Address.getText());
                }
                clientBLL.update(list,Update.getText(),UpdateVal.getText());
//
                }

        });



        findAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Clients>  listCliets = clientBLL.findAllClients();
            clientBLL.fillTable(table1, listCliets);

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
