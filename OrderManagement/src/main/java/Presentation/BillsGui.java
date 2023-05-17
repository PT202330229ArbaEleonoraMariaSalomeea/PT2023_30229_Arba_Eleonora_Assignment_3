package Presentation;

import DAO.BillDAO;
import Model.Bill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BillsGui {
    private JButton findAllBillsButton;
    private JTable table1;
    private JButton backButton;
    private JPanel Bills;

    public BillsGui() {
        MainInterface.changePanel(Bills, "Logs");
    BillDAO billDAO = new BillDAO();
    findAllBillsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Bill> billsList = billDAO.findAll();

            billDAO.displayAllBillsInTable(table1,billsList);

        }
    });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstGui back = new FirstGui();
            }});
    }
}
