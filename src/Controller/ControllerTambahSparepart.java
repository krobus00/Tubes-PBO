/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import Admin.Sparepart;
import View.TambahSparepart;


public class ControllerTambahSparepart implements ActionListener,Controller {

    private TambahSparepart view;
    private ModelAdmin model;
    private Sparepart data;

    public ControllerTambahSparepart() {

        view = new TambahSparepart();
        view.resetView();
        try {
            model = new ModelAdmin();
        } catch (SQLException ex) {
           msg.showMessage(ex.getMessage(), "Error", 0);
        }
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.addActionListener(this);
        view.setVisible(true);
    }

    public ControllerTambahSparepart(Sparepart s) {

        data = s;
        view = new TambahSparepart(s);
        try {
            model = new ModelAdmin();
        } catch (SQLException ex) {
           msg.showMessage(ex.getMessage(), "Error", 0);
        }
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnTambah())) {
            if (view.getBtnTambah().getText() == "Tambah") {
                tambah();
            } else {
                update();
            }

        }
    }

    public void update() {
        try {
            this.data = new Sparepart(
                    data.getId(),
                    view.getTxtNama(),
                    view.getTxtMerk(),
                    view.getTxtBagian(),
                    view.getSpnHarga(),
                    view.getSpnJumlah()
            );
            model.Edit(this.data);
           msg.showMessage("Sparepart berhasil diedit", "Success", 1);
            view.dispose();
        } catch (SQLException ex) {
           msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    public void tambah() {
        try {
            model.Tambah(new Sparepart(
                    view.getTxtNama(),
                    view.getTxtMerk(),
                    view.getTxtBagian(),
                    view.getSpnHarga(),
                    view.getSpnJumlah()
            ));
           msg.showMessage("Sparepart berhasil ditambahkan", "Success", 1);
            view.resetView();
        } catch (SQLException ex) {
           msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

}
