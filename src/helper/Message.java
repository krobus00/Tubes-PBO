/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import javax.swing.JOptionPane;


public class Message {

    public Message() {
    }

    public void showMessage(String message, String title, int type) {
        // 0 error
        // 1 info
        // 2 warning
        JOptionPane.showMessageDialog(null, message, title, type);
        // jika error maka program akan berhenti
        if(type == 0){
            System.exit(0);
        }
    }

    public boolean showYesNoQuestion(String message) {
        int i = JOptionPane.showConfirmDialog(null, message, "Message",
                JOptionPane.YES_NO_OPTION);
        return i == 0;
    }
}
