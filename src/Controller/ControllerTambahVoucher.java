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
import View.TambahVoucher;
import Admin.Voucher;


public class ControllerTambahVoucher implements ActionListener, Controller {

    private TambahVoucher view;
    private ModelAdmin model;
    private Voucher data;

    public ControllerTambahVoucher() {
        view = new TambahVoucher();
        try {
            model = new ModelAdmin();
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.addActionListener(this);
        view.setVisible(true);
    }

    public ControllerTambahVoucher(Voucher v) {
        this.data = v;
        view = new TambahVoucher(v);
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
            this.data = new Voucher(
                    data.getId(),
                    view.getTxtKode(),
                    view.getjSpinner1(),
                    view.getjSpinner2()
            );
            model.Edit(data);
            msg.showMessage("Voucher berhasil diedit", "Success", 1);
            view.dispose();
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    public void tambah() {
        try {
            model.Tambah(new Voucher(
                    view.getTxtKode(),
                    view.getjSpinner1(),
                    view.getjSpinner2()
            ));
            msg.showMessage("Voucher berhasil ditambahkan", "Success", 1);
            view.resetView();
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }
}
