package Controller;

import View.TelaPrincipalView;

import javax.swing.*;

public class TelaPrincipalController extends JFrame {
    public static void main(String[] args) {
        TelaPrincipalView tela = new TelaPrincipalView();

        tela.setTitle("Tela Principal");
        tela.setSize(800, 600);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);

        tela.setVisible(true);
    }
}
