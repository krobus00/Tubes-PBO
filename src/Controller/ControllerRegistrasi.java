/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Manusia.Auth;
import Model.ModelManusia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import View.register;

public class ControllerRegistrasi implements ActionListener, Controller {

    private register view;
    private ModelManusia model = new ModelManusia();
//    private Auth auth = new Auth();

    public ControllerRegistrasi() {
        view = new View.register();
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.btnRegis())) {
            btnRegisActionPerformed();
        } else if (source.equals(view.btnLogin())) {
            new ControllerLogin();
            view.dispose();
        }
    }

    public void btnRegisActionPerformed() {
        String nama = view.getFName();
        String username = view.getUsername();
        String password = view.getPassword();
        String alamat = view.getAlamat();

        if (nama.isEmpty() || username.isEmpty() || password.isEmpty() || alamat.isEmpty()) {
            msg.showMessage("Data tidak boleh kosong!", "Error", 2);
        } else {
            try {
                if (model.checkUsername(model.getUser().getUsername())) {
                    msg.showMessage("Username sudah digunakan", "Error", 2);
                } else {
                    model.auth(nama, username, password, alamat);
                    msg.showMessage("Registrasi berhasil", "Success", 1);
                }
            } catch (SQLException e) {
                msg.showMessage(e.getMessage(), "Error", 0);
            }
        }
    }
}
