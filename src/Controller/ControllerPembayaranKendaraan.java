/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Admin.Pembayaran;
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
import Admin.Voucher;
import Model.ModelKendaraan;
import View.PembayaranKendaraan;
import helper.Currency;
import java.util.ArrayList;


public class ControllerPembayaranKendaraan extends MouseAdapter implements ActionListener, Controller {

    private PembayaranKendaraan view;
    private ModelKendaraan model;
    private ModelAdmin modelAdmin;
    private Kendaraan k;
    private Pembayaran p;
    private Currency c = new Currency();
    private ArrayList<Manusia> listMontir = new ArrayList<>();
    private double totalharga = 0;
    private int potongan = 0;
    private double hargaakhir = 0;

    public ControllerPembayaranKendaraan(Kendaraan k) {
        this.k = k;
        view = new PembayaranKendaraan();

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
        } else if (source.equals(view.getBtnGunakan())) {
            gunakanKodeVoucher();
        } else if(source.equals(view.getBtnBayar())){
            Bayar();
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
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    private void updateStatus() {
        try {
            modelAdmin.updateStatusKendaraan(k.getId(), view.getCbStatus());
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    private String[] setDaftarKerusakan() {

        try {
            view.setDetailKendaraan(modelAdmin.getNamaByIDKendaraan(k.getIdpemilik()), k.getNomor_kendaraan(), k.getNama(), k.getJenis(), k.getKeterangan(), k.getMasuk(), k.getKeluar(), k.getStatus());
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }

        totalharga = 0;
        String[] daftarKerusakan = new String[model.daftarKerusakan().size()];
        for (int i = 0; i < daftarKerusakan.length; i++) {
            daftarKerusakan[i] = model.daftarKerusakan().get(i).getBagian();
            totalharga += model.daftarKerusakan().get(i).getBiaya();
        }
        view.setPotongan(c.formatRp(0), c.formatRp((int) totalharga));
        view.setTotalHarga(c.formatRp((int) totalharga));

        return daftarKerusakan;
    }

    private void gunakanKodeVoucher() {
        try {
            Voucher dataVoucher = modelAdmin.checkVoucher(view.getTxtVoucher());
            p = new Pembayaran(k.getId(), (int) totalharga);
            p.setDiskon(dataVoucher.getPersen());
            view.setPotongan(c.formatRp(p.getTotalPotongan()), c.formatRp(p.getHargaAkhir()));
            view.getBtnGunakan().setEnabled(false);
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    private void Bayar() {
        try {
            modelAdmin.Pembayaran(p);
            msg.showMessage("Pembayaran berhasil", "Success", 1);
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }

    }
}
