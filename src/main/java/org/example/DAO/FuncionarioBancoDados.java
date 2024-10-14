package org.example.DAO;

import org.example.Model.FuncionarioModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioBancoDados {

    public void inserirFuncionarioBD(FuncionarioModel funcionario) {

        String sql = "INSERT INTO funcionarios (nome, matricula, setor) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            // Estabelecer a conexão com o banco de dados
            connection = new ConexaoDB().getConnection();
            stmt = connection.prepareStatement(sql);

            // Setar os valores nos parâmetros da query
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getSetor());

            // Executar o comando SQL
            int rowsAffected = stmt.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Dados inseridos no Banco de Dados com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum dado foi inserido.");
            }

        } catch (SQLException e) {
            // Tratamento de exceções
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar o Banco de Dados!");
        } finally {
            // Fechar a declaração PreparedStatement
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao fechar o stmt!");
            }

            // Fechar a conexão
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexão!");
            }
        }
    }

    public ArrayList<FuncionarioModel> listarTodosFuncionarios(){

        Connection conn = null;
        PreparedStatement stmt = null;
        FuncionarioModel funcionario = null;
        ArrayList<FuncionarioModel> listaFuncionarios = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM funcionarios";

        try{
            conn= new ConexaoDB().getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if(rs != null){
                listaFuncionarios = new ArrayList<>();
                while (rs.next()){
                    funcionario = new FuncionarioModel();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setMatricula(rs.getString("Matricula"));
                    funcionario.setNome(rs.getString("Nome"));
                    funcionario.setSetor(rs.getString("Setor"));
                    listaFuncionarios.add(funcionario);
                }
            }
            System.out.println("Dados coletados com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao selecionar dados do banco!");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao fechar o stmt!");
            }

            // Fechar a conexão
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexão!");
            }
        }
        return listaFuncionarios;
    }

    public ArrayList<FuncionarioModel> buscarFuncionarios(String nome){

        Connection conn = null;
        PreparedStatement stmt = null;
        FuncionarioModel funcionario = null;
        ArrayList<FuncionarioModel> listaFuncionarios = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM funcionarios WHERE nome LIKE ? ORDER BY nome";

        try {
            conn = new ConexaoDB().getConnection();
            stmt = conn.prepareStatement(sql);

            // Define o valor do parâmetro para a busca com LIKE
            stmt.setString(1, "%" + nome + "%");

            rs = stmt.executeQuery();

            // Se houver resultados, inicializa a lista e adiciona os funcionários
            if (rs != null) {
                listaFuncionarios = new ArrayList<>();
                while (rs.next()) {
                    funcionario = new FuncionarioModel();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setMatricula(rs.getString("matricula")); // Certifica-te de que a coluna se chama "matricula"
                    funcionario.setNome(rs.getString("nome"));           // Certifica-te de que a coluna se chama "nome"
                    funcionario.setSetor(rs.getString("setor"));         // Certifica-te de que a coluna se chama "setor"
                    listaFuncionarios.add(funcionario);
                }
            }
            System.out.println("Dados coletados com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao selecionar dados do banco!");
        } finally {
            // Fechar o ResultSet
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao fechar o ResultSet!");
            }

            // Fechar o PreparedStatement
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao fechar o stmt!");
            }

            // Fechar a conexão
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexão!");
            }
        }
        return listaFuncionarios;
    }

    public void alterarCadastroFuncionario(FuncionarioModel funcionarioAjuste){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "UPDATE funcionarios SET nome=?, matricula=?, setor=? where id=?";
        try {
            conn = new ConexaoDB().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,funcionarioAjuste.getNome());
            stmt.setString(2,funcionarioAjuste.getMatricula());
            stmt.setString(3,funcionarioAjuste.getSetor());
            stmt.setInt(4, funcionarioAjuste.getId());
            stmt.execute();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Fechar o ResultSet
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao fechar o ResultSet!");
            }

            // Fechar o PreparedStatement
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao fechar o stmt!");
            }

            // Fechar a conexão
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexão!");
            }
        }
    }

}






















