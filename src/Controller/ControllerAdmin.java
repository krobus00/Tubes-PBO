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
import javax.swing.table.DefaultTableModel;
import Manusia.Manusia;
import View.AdminDashboard;

public class ControllerAdmin extends MouseAdapter implements ActionListener, Controller {

    private AdminDashboard view;
    private ModelAdmin model;
    private Manusia u;
    private int selectedItem = -1;

    public ControllerAdmin(Manusia u) {
        view = new AdminDashboard();
        this.u = u;
        try {
            model = new ModelAdmin();
        } catch (SQLException e) {
            msg.showMessage(e.getMessage(), "Error", 0);
        }
        view.setNama(u.getNama());
        view.addActionListener(this);
        view.addMouseAdapter(this);
        refreshData();
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
        } else if (source.equals(view.getBtnRefresh())) {
            this.refreshData();
        } else if (source.equals(view.getBtnTambahSparepart())) {
            new ControllerTambahSparepart();
        } else if (source.equals(view.getBtnHapusSparepart())) {
            HapusSparepart();
        } else if (source.equals(view.getBtnEditSparepart())) {
            EditSparepart();
        } else if (source.equals(view.getBtnTambahVoucher())) {
            new ControllerTambahVoucher();
        } else if (source.equals(view.getBtnEditVoucher())) {
            EditVoucher();
        } else if (source.equals(view.getBtnHapusVoucher())) {
            HapusVoucher();
        } else if (source.equals(view.getBtnTambahMontir())) {
            new ControllerTambahMontir();
        } else if (source.equals(view.getBtnEditMontir())) {
            EditMontir();
        }else if (source.equals(view.getBtnHapusMontir())){
            HapusMontir();
        } else if (source.equals(view.getBtnCariKendaraan())) {
            CariKendaraan(view.getTxtCariKendaraan());
        } else if (source.equals(view.getBtnCariMontir())) {
            CariMontir(view.getTxtCariMontir());
        } else if (source.equals(view.getBtnCariSparepart())) {
            CariSparepart(view.getTxtCariSparepart());
        } else if (source.equals(view.getBtnCariVoucher())) {
            CariVoucher(view.getTxtCariVoucher());
        } else if (source.equals(view.getBtnCariKendaraan1())) {
            CariKendaraanPembayaran(view.getTxtCariKendaraan1());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getTblKendaraan())) {
            this.selectedItem = view.getTblKendaraan().rowAtPoint(me.getPoint());
        } else if (source.equals(view.getTblSparepart())) {
            this.selectedItem = view.getTblSparepart().rowAtPoint(me.getPoint());
        } else if (source.equals(view.getTblVoucher())) {
            this.selectedItem = view.getTblVoucher().rowAtPoint(me.getPoint());
        } else if (source.equals(view.getTblMontir())) {
            this.selectedItem = view.getTblMontir().rowAtPoint(me.getPoint());
        } else if (source.equals(view.getTblPembayaranKendaraan())) {
            this.selectedItem = view.getTblPembayaranKendaraan().rowAtPoint(me.getPoint());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object source = me.getSource();
        if (me.getClickCount() == 2) {
            if (source.equals(view.getTblKendaraan())) {
                int i = view.getTblKendaraan().rowAtPoint(me.getPoint());
                if (i >= 0) {
                    new ControllerDetailKerusakanKendaraanAdmin(model.daftarKendaraan().get(i));
                }
            } else if (source.equals(view.getTblPembayaranKendaraan())) {
                int i = view.getTblPembayaranKendaraan().rowAtPoint(me.getPoint());
                if (i >= 0) {
                    new ControllerPembayaranKendaraan(model.daftarPembayaranKendaraan().get(i));
                }
            }
        }
    }

    public void refreshData() {
        try {
            view.ResetView();
            model.loadData();
            this.loadKendaraan();
            this.loadMontir();
            this.loadSparepart();
            this.loadVoucher();
            this.loadPembayranKendaraan();
        } catch (SQLException ex) {
            msg.showMessage(ex.getMessage(), "Error", 0);
        }
    }

    public void CariKendaraanPembayaran(String k) {
        try {
            model.cariKendaraanPembayaran(k);
            loadPembayranKendaraan();
        } catch (SQLException ex) {
            msg.showMessage("Gagal load kendaraan", "Error", 0);
        }
    }

    public void CariVoucher(String v) {
        try {
            model.cariVoucher(v);
            loadVoucher();
        } catch (SQLException ex) {
            msg.showMessage("Gagal load voucher", "Error", 0);
        }
    }

    public void CariSparepart(String s) {
        try {
            model.cariSparepart(s);
            loadSparepart();
        } catch (SQLException ex) {
            msg.showMessage("Gagal load sparepart", "Error", 0);
        }
    }

    public void CariMontir(String m) {
        try {
            model.cariMontir(m);
            loadMontir();
        } catch (SQLException ex) {
            msg.showMessage("Gagal load montir", "Error", 0);
        }
    }

    public void CariKendaraan(String k) {
        try {
            model.cariKendaraan(k);
            loadKendaraan();
        } catch (SQLException ex) {
            msg.showMessage("Gagal load kendaraan", "Error", 0);
        }
    }

    public void loadPembayranKendaraan() {
        DefaultTableModel tbl = (DefaultTableModel) view.getTblPembayaranKendaraan().getModel();
        tbl.setRowCount(0);
        for (int i = 0; i < model.daftarPembayaranKendaraan().size(); i++) {
            tbl.insertRow(tbl.getRowCount(), model.daftarPembayaranKendaraan().get(i).getData());
        }
    }

    public void loadKendaraan() {

        DefaultTableModel tbl = (DefaultTableModel) view.getTblKendaraan().getModel();
        tbl.setRowCount(0);
        for (int i = 0; i < model.daftarKendaraan().size(); i++) {
            tbl.insertRow(tbl.getRowCount(), model.daftarKendaraan().get(i).getData());
        }
    }

    public void loadMontir() {
        DefaultTableModel tbl = (DefaultTableModel) view.getTblMontir().getModel();
        tbl.setRowCount(0);
        for (int i = 0; i < model.daftarMontir().size(); i++) {
            tbl.insertRow(tbl.getRowCount(), model.daftarMontir().get(i).getData());
        }
    }

    public void loadSparepart() {
        DefaultTableModel tbl = (DefaultTableModel) view.getTblSparepart().getModel();
        tbl.setRowCount(0);
        for (int i = 0; i < model.daftarSparepart().size(); i++) {
            tbl.insertRow(tbl.getRowCount(), model.daftarSparepart().get(i).getData());
        }
    }

    public void loadVoucher() {
        DefaultTableModel tbl = (DefaultTableModel) view.getTblVoucher().getModel();
        tbl.setRowCount(0);
        for (int i = 0; i < model.daftarVoucher().size(); i++) {
            tbl.insertRow(tbl.getRowCount(), model.daftarVoucher().get(i).getData());
        }
    }

    public void HapusSparepart() {
        if (selectedItem >= 0) {
            try {
                model.HapusSparepart(model.daftarSparepart().get(selectedItem).getId());
                msg.showMessage("Sparepart berhasil dihapus", "Success", 1);
                refreshData();
                this.selectedItem = -1;
            } catch (Exception ex) {
                msg.showMessage(ex.getMessage(), "Error", 0);
            }
        } else {
            msg.showMessage("Pilih sparepart terlebih dahulu!", "Error", 0);
        }
    }

    public void EditSparepart() {
        if (selectedItem >= 0) {
            new ControllerTambahSparepart(model.daftarSparepart().get(selectedItem));
        } else {
            msg.showMessage("Pilih sparepart terlebih dahulu!", "Error", 0);
        }
    }

    public void EditVoucher() {
        if (selectedItem >= 0) {
            new ControllerTambahVoucher(model.daftarVoucher().get(selectedItem));
        } else {
            msg.showMessage("Pilih voucher terlebih dahulu!", "Error", 0);
        }
    }

    public void HapusVoucher() {
        if (selectedItem >= 0) {
            try {
                model.HapusVoucher(model.daftarVoucher().get(selectedItem).getId());
                msg.showMessage("Voucher berhasil dihapus", "Success", 1);
                refreshData();
                this.selectedItem = -1;
            } catch (Exception ex) {
                msg.showMessage(ex.getMessage(), "Error", 0);
            }

        } else {
            msg.showMessage("Pilih voucher terlebih dahulu!", "Error", 0);
        }
    }

    public void EditMontir() {
        if (selectedItem >= 0) {
            new ControllerTambahMontir(model.daftarMontir().get(selectedItem));
        } else {
            msg.showMessage("Pilih montir terlebih dahulu!", "Error", 0);
        }
    }
    public void HapusMontir(){
        if (selectedItem >= 0) {
            try {
                model.HapusMontir(model.daftarMontir().get(selectedItem).getId());
                msg.showMessage("Montir berhasil dihapus", "Success", 1);
                refreshData();
                this.selectedItem = -1;
            } catch (Exception ex) {
                msg.showMessage(ex.getMessage(), "Error", 0);
            }
        } else {
            msg.showMessage("Pilih montir terlebih dahulu!", "Error", 0);
        }
    }
}
