package br.senai.jandira.sp.controller;

import br.senai.jandira.sp.model.Cliente;
import br.senai.jandira.sp.model.Conexao;
import br.senai.jandira.sp.model.Produtos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClienteController {

    Scanner scanner = new Scanner(System.in);
    Conexao conexao = new Conexao();
    Connection objConnection = conexao.getConnection();

    public boolean cadastrarCliente() throws SQLException {

        Statement statement = objConnection.createStatement();

        System.out.println("---------- Adicionar Produto -----------");
        System.out.print("ID do Cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        int telefone = scanner.nextInt();
        scanner.nextLine();
        System.out.print("CPF: ");
        float CPF = scanner.nextFloat();


        String queryAdicionar = "INSERT INTO cliente (idCliente, nome, numero, cpfCliente) values (" +
                idCliente + ",'" + nome +"'," + telefone + "," + CPF + ");";


        try {
            statement.executeUpdate(queryAdicionar);
            System.out.println("\n - Cliente Cadastrado - \n");
            return true;
        }catch (Exception erro){
            System.out.println(erro);
            return false;
        }
    }

    public void listarCliente() throws SQLException {

        //Responsavel por Executar a Query
        Statement statement = objConnection.createStatement();
        //Query (COMANDO PRO BANCO)
        String queryConsulta = "SELECT * FROM cliente";

        ResultSet resultSet = statement.executeQuery(queryConsulta);

        Cliente cliente = new Cliente();

        while (resultSet.next()){
            cliente.setIdCliente(resultSet.getInt("idCliente"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setNumero(resultSet.getInt("numero"));
            cliente.setCpfCliente(resultSet.getFloat("cpfCliente"));


            System.out.println("\n-  " + cliente.getNome() + "  -\n");
            System.out.println("ID do Cliente: " + cliente.getIdCliente());
            System.out.println("Nome do Produto: " + cliente.getNome());
            System.out.println("Preço do Produto: " + cliente.getNumero());
            System.out.println("Quantidade do Produto: " + cliente.getCpfCliente());
            System.out.println("\n -------------------------------\n");

        }
    }

}
