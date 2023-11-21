package br.senai.jandira.sp;

import br.senai.jandira.sp.controller.Menu;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.executarMenu();
    }

}
