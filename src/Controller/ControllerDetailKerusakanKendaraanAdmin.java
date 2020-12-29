/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import Kendaraan.Kendaraan;
import Manusia.Manusia;
import Model.ModelKendaraan;
import View.DetailKerusakanKendaraanAdmin;
import helper.Currency;
import java.util.ArrayList;


public class ControllerDetailKerusakanKendaraanAdmin extends MouseAdapter implements ActionListener, Controller {

    private DetailKerusakanKendaraanAdmin view;
    private ModelKendaraan model;
    private ModelAdmin modelAdmin;
    private Kendaraan k;
    private ArrayList<Manusia> listMontir = new ArrayList<>();

    private Currency c = new Currency();

    public ControllerDetailKerusakanKendaraanAdmin(Kendaraan k) {
        this.k = k;
        view = new DetailKerusakanKendaraanAdmin();

        try {
            modelAdmin = new ModelAdmin();
            model = new ModelKendaraan();
            model.getDaftarKerusakan(k.getId());
            loadListMOntir();
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.addMouseAdapter(this);
        view.addActionListener(this);
        view.setVisible(true);
        view.setDaftarKerusakan(setDaftarKerusakan());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnUpdateStatus())) {
            updateStatus();
        }
    }

    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getListKerusakan())) {
            int i = view.getSelectedKerusakan();
            if (i >= 0) {
                view.setTxtDetail(model.daftarKerusakan().get(i).toString());
            }
        }
    }

    private void loadListMOntir() {
        try {
            listMontir = modelAdmin.getMontirByIDKendaraan(k.getId());
            DefaultTableModel tbl = (DefaultTableModel) view.getTblMontir().getModel();
            tbl.setRowCount(0);
            for (int i = 0; i < listMontir.size(); i++) {
                tbl.insertRow(tbl.getRowCount(), listMontir.get(i).getData());
            }
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 2);
        }
    }

    private void updateStatus() {
        try {
            modelAdmin.updateStatusKendaraan(k.getId(), view.getCbStatus());
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 2);
        }
    }

    private String[] setDaftarKerusakan() {

        try {
            view.setDetailKendaraan(modelAdmin.getNamaByIDKendaraan(k.getIdpemilik()), k.getNomor_kendaraan(), k.getNama(), k.getJenis(), k.getKeterangan(), k.getMasuk(), k.getKeluar(), k.getStatus());
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 2);
        }

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
