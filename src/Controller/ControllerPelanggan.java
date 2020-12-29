/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import database.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Manusia.Manusia;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import Kendaraan.Kendaraan;
import Model.ModelManusia;
import View.PelangganDashboard;


public class ControllerPelanggan extends MouseAdapter implements ActionListener, Controller {

    private PelangganDashboard view;
    private ModelManusia model;
    private Manusia u;
    private ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

    public ControllerPelanggan(Manusia u) {
        view = new View.PelangganDashboard();
        this.u = u;
        model = new ModelManusia();
        model.setPelanggan(u);
        view.setLblNama("Hi, " + u.getNama());
        view.addActionListener(this);
        view.addMouseAdapter(this);
        try {
            model.getPelanggan().loadKendaraan();
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
        this.loadKendaraan();
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
        } else if (source.equals(view.btnTambah())) {
            tambahKendaraan();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getTblKendaraan())) {
            if (me.getClickCount() == 2) {
                int i = view.getTblKendaraan().rowAtPoint(me.getPoint());
                if (i >= 0) {
                    new ControllerDetailKerusakanKendaraanPelanggan(model.getPelanggan().daftarKendaraan().get(i));
                }
            }
        }
    }

    public void tambahKendaraan() {
        Kendaraan newKendaraan = new Kendaraan(view.getNomor(), view.getNama(), view.getJenis(), view.getKeterangan(), u.getId());
        try {
            model.getPelanggan().TambahKendaraan(newKendaraan);
            msg.showMessage("Kendaraan berhasil ditambahkan \nsilahkan menunggu proses pengerjaan", "Success", 1);
            view.resetView();
            model.getPelanggan().loadKendaraan();
            loadKendaraan();
        } catch (SQLException e) {
            msg.showMessage(e.getMessage(), "Error", 0);
        }
    }

    public void loadKendaraan() {

        DefaultTableModel tbl = (DefaultTableModel) view.getTblKendaraan().getModel();
        for (int i = 0; i < model.getPelanggan().daftarKendaraan().size(); i++) {
            tbl.insertRow(tbl.getRowCount(), model.getPelanggan().daftarKendaraan().get(i).getData());
        }
    }

}
