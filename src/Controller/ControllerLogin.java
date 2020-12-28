/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Manusia.Auth;
import View.login;
import Manusia.Manusia;
import Model.ModelManusia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class ControllerLogin implements ActionListener, Controller {

    private login view;
    private ModelManusia model = new ModelManusia();

    public ControllerLogin() {
        view = new View.login();
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.btnLogin())) {
            btnLoginActionPerformed();
        } else if (source.equals(view.btnRegis())) {
            new ControllerRegistrasi();
            view.dispose();
        }
    }

    public void btnLoginActionPerformed() {
        String username = view.getUsername();
        String password = view.getPassword();
        if (username.isEmpty() || password.isEmpty()) {
            msg.showMessage("Username atau Password Kosong", "Error", 2);
        } else {
            try {
                if (!model.checkUsername(username)) {
                    msg.showMessage("Username tidak ditemukan", "Error", 2);
                } else {
                    model.auth(username, password);
                    if (model.getUser().getNama() == null) {
                        msg.showMessage("Password salah!", "Error", 2);
                    } else {
                        switch (model.getUser().getLevel()) {
                            case "admin":
                                new ControllerAdmin(model.getUser());
                                break;
                            case "pelanggan":
                                new ControllerPelanggan(model.getUser());
                                break;
                            case "montir":
                                new ControllerMontir(model.getUser());
                                break;
                            default:
                                break;
                        }
                        view.dispose();
                    }
                }
            } catch (SQLException e) {
                msg.showMessage(e.getMessage(), "Error", 0);
            }
        }
    }

}
