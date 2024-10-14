package org.example.Model;

import org.example.DAO.FuncionarioBancoDados;

import java.util.ArrayList;

public class FuncionarioModel {
    private int id;
    private String nome;
    private String matricula;
    private String setor;

    public FuncionarioModel() {

    }

    public FuncionarioModel(String nome, String matricula, String setor) {
        this.nome = nome;
        this.matricula = matricula;
        this.setor = setor;
    }

    public FuncionarioModel(int id, String nome, String matricula, String setor) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.setor = setor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public void cadastrarFuncionarioDAO(FuncionarioModel funcionario){
        FuncionarioBancoDados novoFuncionario = new FuncionarioBancoDados();
        novoFuncionario.inserirFuncionarioBD(funcionario);
    }

    public ArrayList<FuncionarioModel> listarFuncionarios(){
        return new FuncionarioBancoDados().listarTodosFuncionarios();
    }

    public ArrayList<FuncionarioModel> buscarFuncionarios(String nome){
        return new FuncionarioBancoDados().buscarFuncionarios(nome);
    }

    public void alterarFuncionario(FuncionarioModel funcionarioAjuste){
        FuncionarioBancoDados ajusteDados = new FuncionarioBancoDados();
        ajusteDados.alterarCadastroFuncionario(funcionarioAjuste);
    }

}





















