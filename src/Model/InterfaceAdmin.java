/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Admin.Sparepart;
import Admin.Voucher;
import Manusia.Manusia;
import java.sql.SQLException;


public interface InterfaceAdmin {

    public void loadData() throws SQLException;

    public void Tambah(Sparepart s) throws SQLException;

    public void Tambah(Voucher v) throws SQLException;

    public void Tambah(Manusia m) throws SQLException;

    public void Edit(Sparepart s) throws SQLException;

    public void Edit(Voucher v) throws SQLException;

    public void Edit(Manusia m) throws SQLException;

}
