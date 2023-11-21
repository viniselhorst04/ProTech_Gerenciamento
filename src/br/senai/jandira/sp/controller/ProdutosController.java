package br.senai.jandira.sp.controller;

import br.senai.jandira.sp.model.Conexao;
import br.senai.jandira.sp.model.Produtos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProdutosController {


    Conexao conexao = new Conexao();
    Connection objConnection = conexao.getConnection();

    public boolean cadastrarProduto() throws SQLException {

        Statement statement = objConnection.createStatement();

        Scanner scanner = new Scanner(System.in);
        System.out.println("---------- Adicionar Produto -----------");
        System.out.print("ID do Produto: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();
        System.out.print("Preço do Produto: ");
        double precoProduto = scanner.nextDouble();
        System.out.print("Quantidade do Produto: ");
        int QtdProduto = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Fornecedor: ");
        String fornecedor = scanner.nextLine();
        System.out.print("Caracteristicas: ");
        String caracteristicas = scanner.nextLine();

        String queryAdicionar = "INSERT INTO produtos (idProduto, nomeProduto, preco, quantidade, fornecedor, caracteristicasEspeciais) values (" +
                idProduto + ",'" + nomeProduto +"','" + precoProduto + "','" + QtdProduto + "'," + fornecedor + "," + caracteristicas + ");";

        System.out.println("\n - Produto Cadastrado - \n");


        try {
            statement.executeUpdate(queryAdicionar);
            return true;
        }catch (Exception erro){
            System.out.println(erro);
            return false;
        }

    }

    public void listarProdutos() throws SQLException {

        //Responsavel por Executar a Query
        Statement statement = objConnection.createStatement();
        //Query (COMANDO PRO BANCO)
        String queryConsulta = "SELECT * FROM produtos";

        ResultSet resultSet = statement.executeQuery(queryConsulta);

        Produtos produtos = new Produtos();

        while (resultSet.next()){
            produtos.setNome(resultSet.getString("nomeProduto"));
            produtos.setIdProduto(resultSet.getInt("idProduto"));
            produtos.setPreco(resultSet.getDouble("preco"));
            produtos.setQuantidade(resultSet.getInt("quantidade"));
            produtos.setFornecedor(resultSet.getString("fornecedor"));
            produtos.setCaracteristicas(resultSet.getString("caracteristicasEspeciais"));


            System.out.println("\n-  " + produtos.getNome() + "  -\n");
            System.out.println("ID do Produto: " + produtos.getIdProduto());
            System.out.println("Nome do Produto: " + produtos.getNome());
            System.out.println("Preço do Produto: " + produtos.getPreco());
            System.out.println("Quantidade do Produto: " + produtos.getQuantidade());
            System.out.println("Fornecedor: " + produtos.getFornecedor());
            System.out.println("\n -------------------------------\n");

        }
    }

}
