package br.senai.jandira.sp.controller;

import br.senai.jandira.sp.model.Conexao;
import br.senai.jandira.sp.model.Produtos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProdutosController {


    Scanner scanner = new Scanner(System.in);
    Conexao conexao = new Conexao();
    Connection objConnection = conexao.getConnection();

    public boolean cadastrarProduto() throws SQLException {

        Statement statement = objConnection.createStatement();

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
                idProduto + ",'" + nomeProduto +"','" + precoProduto + "','" + QtdProduto + "','" + fornecedor + "','" + caracteristicas + "');";




        try {
            statement.executeUpdate(queryAdicionar);
            System.out.println("\n - Produto Cadastrado - \n");
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

    public boolean deletarProdutos(int idProduto) throws SQLException {

        Statement statement = objConnection.createStatement();

        String queryBusca = "SELECT quantidade FROM produtos WHERE idProduto =" + idProduto;
        ResultSet resultSet = statement.executeQuery(queryBusca);

        Produtos produtos = new Produtos();

        while (resultSet.next()){
            produtos.setQuantidade(resultSet.getInt("quantidade"));
        }

        System.out.println("\n O produto com o Código: " +idProduto+ " tem " + produtos.getQuantidade()+" quantidades em estoque");
        System.out.println("\n  Quantas unidades do produto você deseja excluir?");
        int quantidadeDesejada = scanner.nextInt();


        if (quantidadeDesejada >= produtos.getQuantidade()){
            String queryDelete = "DELETE FROM produtos WHERE idProduto = " + idProduto;
            statement.executeUpdate(queryDelete);
            System.out.println("Acabou todo estoque do Produto, e ele foi excluido do banco!");
            try {
                statement.executeUpdate(queryDelete);
                return true;
            }catch (Exception erro){
                System.out.println(erro);
                return false;
            }
        }else{
            int novaQuantidade = produtos.getQuantidade() - quantidadeDesejada;

            String queryUpdate = "UPDATE produtos SET quantidade = " + novaQuantidade+ " WHERE idProduto = " + idProduto;
            int resultSetUpdate = statement.executeUpdate(queryUpdate);
            System.out.println("Quantidade Atualizada!");

            try {
                statement.executeUpdate(queryUpdate);
                return true;
            }catch (Exception erro){
                System.out.println(erro);
                return false;
            }

        }

    }

}
