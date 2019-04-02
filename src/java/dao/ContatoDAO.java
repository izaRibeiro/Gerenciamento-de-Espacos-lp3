package dao;

import model.Contato;

import java.sql.*;
import java.util.ArrayList;

public class ContatoDAO {
    public static void gravar(Contato contato) throws SQLException, ClassNotFoundException{
        Connection conexao = null;
        PreparedStatement comando = null;
        try{
            conexao = BD.getConexao();
            String sql = "insert into contato (id, numero, cliente_id)"
                    + "values(?,?, ?)";
            comando = ((Connection) conexao).prepareStatement(sql);
            comando.setLong(1, contato.getId());
            comando.setString(2, contato.getNumero());

            if(contato.getIdCliente() == null){
                comando.setNull(3, Types.NULL);
            }
            else{
                comando.setLong(3, contato.getIdCliente());
            }

            comando.execute();
            BD.fecharConexao(conexao, comando);
        }catch(SQLException e){
            throw e;
        }

    }

    public static void alterar(Contato contato) throws SQLException, ClassNotFoundException{
        Connection conexao = null;
        PreparedStatement comando = null;
        try{
            conexao = BD.getConexao();
            String sql = "update contato set  numero = ?, cliente_id = ? where id = ?";
            
            comando = conexao.prepareStatement(sql);
            
            comando.setString(1, contato.getNumero());
             comando.setLong(2, contato.getIdCliente());
               
             comando.setLong(3, contato.getId());


                comando.execute();
        }catch(SQLException e){
            throw e;
        }
    }

    public static void excluir (Contato contato) throws SQLException, ClassNotFoundException{
        Connection conexao = null;
        PreparedStatement comando = null;

        try{
            conexao = BD.getConexao();
            String sql = "delete from contato where id = ?";
            comando = conexao.prepareStatement(sql);
            comando.setLong(1, contato.getId());
            comando.execute();
        }catch (SQLException e){
            throw e;
        }finally {
            BD.fecharConexao(conexao, comando);
        }
    }

    public static Contato obterContato(Long id) throws  ClassNotFoundException{
        Connection conexao = null;
        PreparedStatement comando = null;
        Contato contato = null;

        try{
            conexao = BD.getConexao();
            String sql = "select * from contato where id = ?";
            comando = conexao.prepareStatement(sql);
            comando.setLong(1, id);
            ResultSet rs = comando.executeQuery();
            rs.first();
            contato = new Contato (rs.getLong("id"),
                    rs.getString("numero"),
                    rs.getLong("cliente_id"));


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BD.fecharConexao(conexao, comando);
        }
        return contato;
    }


    public static ArrayList<Contato> obterTodosContatos() throws  ClassNotFoundException{
        Connection conexao = null;
        Statement comando = null;
        ArrayList<Contato> contatos = new ArrayList<>();
        Contato contato = null;
        try{
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            String sql = "select * from contato";
            ResultSet rs = comando.executeQuery(sql);
            while(rs.next()){
                contato = new Contato(rs.getLong("id"),
                        rs.getString("numero"));

                contato.setId(rs.getLong("id"));
                contatos.add(contato);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            BD.fecharConexao(conexao, comando);
            return contatos;
        }
    }

}
