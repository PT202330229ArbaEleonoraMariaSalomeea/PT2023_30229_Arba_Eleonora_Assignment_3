package Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstGui {
    private JButton ClientsTable;
    private JButton productsTable;
    private JButton ordersTable;
    private JPanel firstGuiJPanel;
    private JButton logsTableButton;


    public FirstGui() {


        MainInterface.changePanel(firstGuiJPanel, "FirstGuiPanel");

    ClientsTable.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GuiClients clientWindow = new GuiClients();
        }
    });
    productsTable.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GuiProduct orderWindow = new GuiProduct();
        }
    });
    ordersTable.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GuiOrders orderWindow = new GuiOrders();
        }
    });
        logsTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillsGui bills = new BillsGui();
            }
        });
    }
}
