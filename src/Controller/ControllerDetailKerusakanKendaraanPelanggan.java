/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import Kendaraan.Kendaraan;
import Model.ModelKendaraan;
import View.DetailKerusakanKendaraanPelanggan;
import helper.Currency;

public class ControllerDetailKerusakanKendaraanPelanggan extends MouseAdapter implements Controller {

    private DetailKerusakanKendaraanPelanggan view;
    private ModelKendaraan model;
    private Kendaraan k;
    private Currency c = new Currency();

    public ControllerDetailKerusakanKendaraanPelanggan(Kendaraan k) {
        this.k = k;
        view = new DetailKerusakanKendaraanPelanggan();
        try {
            model = new ModelKendaraan();
            model.getDaftarKerusakan(k.getId());
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.addMouseAdapter(this);
        view.setVisible(true);
        view.setDaftarKerusakan(setDaftarKerusakan());
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getListKerusakan())) {
            int i = view.getSelectedKerusakan();
            if (i >= 0) {
                view.setTxtDetail(model.daftarKerusakan().get(i).toString());
            }
        }
    }

    private String[] setDaftarKerusakan() {
        view.setDetailKendaraan(k.getNomor_kendaraan(), k.getNama(), k.getJenis(), k.getKeterangan(), k.getMasuk(), k.getKeluar(), k.getStatus());
        double totalharga = 0;
        String[] daftarKerusakan = new String[model.daftarKerusakan().size()];
        for (int i = 0; i < daftarKerusakan.length; i++) {
            daftarKerusakan[i] = model.daftarKerusakan().get(i).getBagian();
            totalharga += model.daftarKerusakan().get(i).getBiaya();
        }
        view.setTotalHarga(c.formatRp((int) totalharga));

        return daftarKerusakan;
    }
}
