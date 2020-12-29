/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelKendaraan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import Kendaraan.Kerusakan;
import Kendaraan.Kendaraan;
import Admin.Sparepart;
import Manusia.Manusia;
import Manusia.Montir;
import helper.Currency;
import View.DetailKerusakanKendaraanMontir;


public class ControllerDetailKerusakanKendaraanMontir extends MouseAdapter implements ActionListener, Controller {

    private DetailKerusakanKendaraanMontir view;
    private Montir model;
    private Manusia u;
    private ModelKendaraan modelKendaraan;
    private Kendaraan k;
    
    private ArrayList<Sparepart> listSparepart = new ArrayList<>();
    private int selectedItem = -1;

    public ControllerDetailKerusakanKendaraanMontir(Manusia u, Kendaraan k) {
        this.k = k;
        this.u = u;
        this.view = new DetailKerusakanKendaraanMontir();
        try {
            this.model = new Montir(u);
            view.setDetailKendaraan(model.getNamaByIDKendaraan(k.getIdpemilik()), k.getNomor_kendaraan(), k.getNama(), k.getJenis(), k.getKeterangan(), k.getMasuk(), k.getKeluar(), k.getStatus());
            modelKendaraan = new ModelKendaraan();
            modelKendaraan.getDaftarKerusakan(k.getId());
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }

        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setDaftarKerusakan(setDaftarKerusakan());
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnCari())) {
            cariSparepart(view.getTxtCariNama());
        } else if (source.equals(view.getBtnTambah())) {
            Tambah();
        } else if (source.equals(view.getBtnUpdateStatusKerusakan())) {
            updateStatusKerusakan();
        } else if (source.equals(view.getBtnUpdateStatusKendaraan())) {
            updateStatusKendaraan();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getTblSparepart())) {
            this.selectedItem = view.getTblSparepart().rowAtPoint(me.getPoint());
        } else if (source.equals(view.getListKerusakan())) {
            this.selectedItem = view.getSelectedKerusakan();
            if (selectedItem >= 0) {
                view.setTxtDetail(modelKendaraan.daftarKerusakan().get(selectedItem).toString());
                view.getCbStatus().setSelectedItem(modelKendaraan.daftarKerusakan().get(selectedItem).getStatus());
            }
        }
    }

    private void cariSparepart(String s) {
        try {
            this.listSparepart = model.getListSparepart(s);
            DefaultTableModel tbl = (DefaultTableModel) view.getTblSparepart().getModel();
            tbl.setRowCount(0);
            for (int i = 0; i < this.listSparepart.size(); i++) {
                tbl.insertRow(tbl.getRowCount(), this.listSparepart.get(i).getData());
            }
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    private void Tambah() {
        Kerusakan kerusakanBaru = new Kerusakan(
                k.getId(),
                view.getTxtBagian(),
                view.getTxtKeteranganKerusakan(),
                "Dikerjakan",
                this.listSparepart.get(selectedItem).getHarga()
        );

        try {
            model.TambahData(u, listSparepart.get(selectedItem).getId(), kerusakanBaru);
            modelKendaraan.getDaftarKerusakan(k.getId());
            view.setDaftarKerusakan(setDaftarKerusakan());
            msg.showMessage("Berhasil menambahkan kerusakan", "Success", 1);
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }

    }

    private void updateStatusKerusakan() {
        try {
            if (selectedItem != -1) {
                model.updateStatusKerusakan(modelKendaraan.daftarKerusakan().get(selectedItem).getId(), (String) view.getCbStatus().getSelectedItem());
                modelKendaraan.getDaftarKerusakan(k.getId());
                view.setTxtDetail(modelKendaraan.daftarKerusakan().get(selectedItem).toString());
                msg.showMessage("Berhasil diperbaharui", "Berhasil", 1);
            } else {
                msg.showMessage("Pilih kerusakan dulu!", "Warning", 2);
            }
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    private void updateStatusKendaraan() {
        try {
            model.updateStatusKendaraan(k.getId(), (String) view.getCbStatusKendaraan().getSelectedItem());
            msg.showMessage("Berhasil diperbaharui", "Berhasil", 1);
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    private String[] setDaftarKerusakan() {

        double totalharga = 0;
        String[] listKerusakan = new String[modelKendaraan.daftarKerusakan().size()];
        for (int i = 0; i < listKerusakan.length; i++) {
            listKerusakan[i] = modelKendaraan.daftarKerusakan().get(i).getBagian();
            totalharga += modelKendaraan.daftarKerusakan().get(i).getBiaya();
        }
        Currency convert = new Currency();
        view.setTotalHarga(convert.formatRp((int) totalharga));

        return listKerusakan;
    }
}
