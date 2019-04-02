package dao;

import model.Pagamento;

import java.sql.*;
import java.util.ArrayList;

public class PagamentoDAO {

    public static void gravar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into pagamento(id, vencimento, numero_codigo_barras, valor_total, reserva_id) values(?,?,?,?,?)";
            comando = conexao.prepareStatement(sql);
            comando.setLong(1, pagamento.getId());
            comando.setString(2, pagamento.getVencimento());
            comando.setString(3, pagamento.getNumeroCodBarras());
            comando.setDouble(4, pagamento.getValorTotal());

            if (pagamento.getIdReserva() == null) {
                comando.setNull(5, Types.NULL);
            } else {
                comando.setLong(5, pagamento.getIdReserva());
            }

            comando.execute();
            BD.fecharConexao(conexao, comando);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BD.getConexao();
            String sql = "update pagamento set vencimento=?, numero_codigo_barras=?, valor_total=?, reserva_id=? where id=?";
            comando = conexao.prepareStatement(sql);

            comando.setString(1, pagamento.getVencimento());
            comando.setString(2, pagamento.getNumeroCodBarras());
            comando.setDouble(3, pagamento.getValorTotal());

            if (pagamento.getIdReserva() == null) {
                comando.setNull(4, Types.NULL);
            } else {
                comando.setLong(4, pagamento.getIdReserva());
            }

            comando.setLong(5, pagamento.getId());
            comando.execute();
            BD.fecharConexao(conexao, comando);

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BD.getConexao();
            String sql = "delete from pagamento where id=?";
            comando = conexao.prepareStatement(sql);
            comando.setLong(1, pagamento.getId());

            comando.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            BD.fecharConexao(conexao, comando);
        }
    }

    public static Pagamento obterPagamento(Long id) throws ClassNotFoundException {

        Connection conexao = null;
        PreparedStatement comando = null;
        Pagamento pagamento = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.prepareStatement("select * from pagamento where id=?");
            comando.setLong(1, id);
            ResultSet rs = comando.executeQuery();
            rs.first();

            pagamento = new Pagamento(rs.getLong("id"),
                    rs.getString("vencimento"),
                    rs.getString("numero_codigo_barras"),
                    rs.getDouble("valor_total"),
                    rs.getLong("reserva_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BD.fecharConexao(conexao, comando);

        }
        return pagamento;
    }

    public static ArrayList<Pagamento> obterTodosPagamentos() throws ClassNotFoundException {

        Connection conexao = null;
        Statement comando = null;

        ArrayList<Pagamento> lista = new ArrayList<>();
        Pagamento pagamento = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            String sql = "select * from pagamento";
            ResultSet rs = comando.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Pagamento(rs.getLong("id"),
                        rs.getString("vencimento"),
                        rs.getString("numero_codigo_barras"),
                        rs.getDouble("valor_total"),
                        rs.getLong("reserva_id")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BD.fecharConexao(conexao, comando);
            return lista;
        }
    }

}
