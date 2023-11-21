package br.senai.jandira.sp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {


    private String user,password,banco,servidor;
    public Connection conexao;

    public Conexao(){
        this.servidor = "localhost";
        this.banco = "db_protech_java";
        this.user = "root";
        this.password = "bcd127";
    }

    public void ConnectDrive(){
        try {
            this.conexao = DriverManager.getConnection("jdbc:mysql://" + this.servidor + "/" + this.banco , this.user, this.password);
        }catch (SQLException erro){
            System.out.println(erro);
        }
    }

    public Connection getConnection(){
        ConnectDrive();
        return conexao;

    }

}
