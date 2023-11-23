package br.senai.jandira.sp.controller;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void executarMenu() throws SQLException {

        ClienteController clienteController = new ClienteController();
        ProdutosController produtosController = new ProdutosController();
        VendaController vendaController = new VendaController();

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar){

            System.out.println(" ------------------------------- ");
            System.out.println(" -             MENU            - ");
            System.out.println(" -------------------------------\n ");
            System.out.println(" 1- Cadastrar Produtos");
            System.out.println(" 2- Listar Produtos");
            System.out.println(" 3- Deletar Produtos");
            System.out.println(" 4- Realizar Venda");
            System.out.println(" 5- Cadastrar Cliente");
            System.out.println(" 6- Listar Cliente");
            System.out.println(" 7- Historico do Cliente");
            System.out.println(" 8- Sair");
            System.out.println(" \n------------------------------- ");

            System.out.print("Escolha uma opção:");
            int escolhaUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaUsuario){

                case 1:

                    produtosController.cadastrarProduto();

                    break;

                case 2:

                    produtosController.listarProdutos();

                    break;

                case 3:
                    System.out.println("Qual ID do produto que deseja excluir?");
                    int idDigitado = scanner.nextInt();
                    produtosController.deletarProdutos(idDigitado);

                    break;

                case 4:

                    System.out.println("Qual seu ID ?");
                    int idCliente = scanner.nextInt();

                    System.out.println("Qual o ID do Produto que deseja comprar?");
                    int idProduto = scanner.nextInt();

                    vendaController.realizarVenda(idCliente,idProduto);

                    break;

                case 5:

                    clienteController.cadastrarCliente();

                    break;

                case 6:

                    clienteController.listarCliente();

                    break;

                case 7:



                    break;

                case 8:
                    continuar = false;
                    break;

            }

        }

    }

}
