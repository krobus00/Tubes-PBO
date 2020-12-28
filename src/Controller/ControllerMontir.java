/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Manusia.Manusia;
import Model.ModelManusia;
import View.MontirDashboard;


public class ControllerMontir extends MouseAdapter implements ActionListener, Controller {

    private MontirDashboard view;
    private ModelManusia model;
    private int selectedItem = -1;

    public ControllerMontir(Manusia u) {
        view = new MontirDashboard();
        try {
            model = new ModelManusia();
            model.setMontir(u);
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
        view.setLblNama("Hi, " + u.getNama());
        view.addActionListener(this);
        view.addMouseAdapter(this);
        loadData();
        view.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnLogout())) {
            if (msg.showYesNoQuestion("Yakin akan keluar ? ")) {
                new ControllerLogin();
                view.dispose();
            }
        } else if (source.equals(view.getBtnRefresh())) {
            refreshData();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getTblDikerjakan())) {
            this.selectedItem = view.getTblDikerjakan().rowAtPoint(me.getPoint());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object source = me.getSource();
        if (me.getClickCount() == 2) {
            if (source.equals(view.getTblDikerjakan())) {
                int i = view.getTblDikerjakan().rowAtPoint(me.getPoint());
                if (i >= 0) {
                    new ControllerDetailKerusakanKendaraanMontir(model.getUser(), model.getMontir().getListDikerjakan().get(i));
                }
            } else if (source.equals(view.getTblKendaraan())) {
                int i = view.getTblKendaraan().rowAtPoint(me.getPoint());
                if (i >= 0) {
                    new ControllerDetailKerusakanKendaraanMontir(model.getUser(), model.getMontir().getListKendaraan().get(i));
                }
            }
        }
    }

    private void refreshData() {
        try {
            model.getMontir().LoadData(model.getUser().getId());
            model.getMontir().LoadData();
            loadData();
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    private void loadData() {
        DefaultTableModel tbl = (DefaultTableModel) view.getTblDikerjakan().getModel();
        tbl.setRowCount(0);
        for (int i = 0; i < model.getMontir().getListDikerjakan().size(); i++) {
            tbl.insertRow(tbl.getRowCount(), model.getMontir().getListDikerjakan().get(i).getData());
        }
        DefaultTableModel tbl1 = (DefaultTableModel) view.getTblKendaraan().getModel();
        tbl1.setRowCount(0);
        for (int i = 0; i < model.getMontir().getListKendaraan().size(); i++) {
            tbl1.insertRow(tbl1.getRowCount(), model.getMontir().getListKendaraan().get(i).getData());
        }
    }

}
