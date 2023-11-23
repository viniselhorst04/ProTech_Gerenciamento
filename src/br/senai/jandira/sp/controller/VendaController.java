package br.senai.jandira.sp.controller;

import br.senai.jandira.sp.model.Cliente;
import br.senai.jandira.sp.model.Conexao;
import br.senai.jandira.sp.model.Produtos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class VendaController {
    Scanner scanner = new Scanner(System.in);
    Conexao conexao = new Conexao();
    Connection objConnection = conexao.getConnection();
    public boolean realizarVenda(int idCliente, int idProduto) throws SQLException {

        Statement statement = objConnection.createStatement();

        String queryBuscaProduto = "SELECT * FROM produtos WHERE idProduto = "+ idProduto;
        ResultSet resultSetProdutos = statement.executeQuery(queryBuscaProduto);

        Produtos produtos = new Produtos();

        while (resultSetProdutos.next()){
            produtos.setNome(resultSetProdutos.getString("nomeProduto"));
            produtos.setPreco(resultSetProdutos.getDouble("preco"));

        }

        resultSetProdutos.close();

        String queryBuscaCliente = "SELECT nome FROM cliente WHERE idCliente = "+ idCliente;
        ResultSet resultSetCliente = statement.executeQuery(queryBuscaCliente);

        Cliente cliente = new Cliente();

        while (resultSetCliente.next()){
            cliente.setNome(resultSetCliente.getString("nome"));
        }

        String queryVenda = "INSERT INTO vendas (idProduto_FK, idCliente_FK, nomeProduto, nomeCliente, precoProduto) values ("
               + idProduto + "," + idCliente + ",'" + produtos.getNome() + "','" + cliente.getNome() + "'," + produtos.getPreco()+ ")";

        try {
            statement.executeUpdate(queryVenda);
            System.out.println("\n - Produto Cadastrado - \n");
            return true;
        }catch (Exception erro){
            System.out.println(erro);
            return false;
        }

    }

}
