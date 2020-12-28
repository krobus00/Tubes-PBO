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
import javax.swing.JFrame;
import Manusia.Manusia;
import View.TambahMontir;


public class ControllerTambahMontir implements ActionListener,Controller {

    private TambahMontir view;
    private ModelAdmin model;
    private Manusia data;

    public ControllerTambahMontir() {

        view = new TambahMontir();
        try {
            model = new ModelAdmin();
        } catch (SQLException ex) {
           msg.showMessage(ex.getMessage(), "Error", 0);
        }
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.addActionListener(this);
        view.setVisible(true);
    }

    public ControllerTambahMontir(Manusia u) {

        data = u;
        view = new TambahMontir(u);
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
            this.data = new Manusia(
                    data.getId(),
                    view.getTxtNama(),
                    view.getTxtUsername(),
                    view.getTxtPassword(),
                    view.getTxtAlamat(),
                    "montir"
            );
            model.Edit(this.data);
           msg.showMessage("Montir berhasil diedit", "Success", 1);
            view.dispose();
        } catch (SQLException ex) {
           msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    public void tambah() {
        try {
//            Manusia u = new Manusia(name, username, password, alamat)
            model.Tambah(new Manusia(
                    view.getTxtNama(),
                    view.getTxtUsername(),
                    view.getTxtPassword(),
                    view.getTxtAlamat()
            ));
           msg.showMessage("Sparepart berhasil ditambahkan", "Success", 1);
            view.resetView();
        } catch (SQLException ex) {
           msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }
}
