package br.senai.jandira.sp.model;

public class Cliente {

    private int idCliente, numero;
    private String nome;
    private float cpfCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(float cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}
