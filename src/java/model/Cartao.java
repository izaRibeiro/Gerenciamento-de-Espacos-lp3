package model;


import dao.CartaoDAO;

import java.sql.SQLException;
import java.util.List;

public class Cartao{
    private Long id;
    private String bandeira;
    private String validade;
    private Long numeroCartao;
    private String codigoSeguranca;
    private  Long idCliente;
    private Cliente cliente;

    public Cartao(Long id, String bandeira, String validade, Long numeroCartao, String codigoSeguranca, Long idCliente) {
        this.id = id;
        this.bandeira = bandeira;
        this.validade = validade;
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.idCliente = idCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Cartao setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public Cartao setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public Cartao(Long id, String bandeira, String validade, Long numeroCartao, String codigoSeguranca) {
       this.id = id;
        this.bandeira = bandeira;
        this.validade = validade;
        this.numeroCartao = numeroCartao;
       this.codigoSeguranca = codigoSeguranca;
    }

    public Long getId() {
        return id;
    }

    public Cartao setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBandeira() {
        return bandeira;
    }

    public Cartao setBandeira(String bandeira) {
        this.bandeira = bandeira;
        return this;
    }

    public String getValidade() {
        return validade;
    }

    public Cartao setValidade(String validade) {
        this.validade = validade;
        return this;
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public Cartao setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public void setIdCliente(long cliente) {

    }

    public void gravar() throws SQLException, ClassNotFoundException {
        CartaoDAO.gravar(this);
    }
    public void alterar() throws SQLException, ClassNotFoundException {
        CartaoDAO.alterar(this);
    }
    public void excluir() throws SQLException, ClassNotFoundException {
        CartaoDAO.excluir(this);
    }
    public static Cartao obterCartao(Long id) throws SQLException, ClassNotFoundException {
        return  CartaoDAO.obterCartao((long)id);
    }
    public static List<Cartao> obterTodosCartoes() throws  SQLException, ClassNotFoundException {
        return  CartaoDAO.obterTodosCartoes();
    }
}