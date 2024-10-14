package org.example.Controller;

import org.example.Model.FuncionarioModel;

import javax.swing.*;
import java.util.ArrayList;

public class FuncionarioController {
    public void cadastrarContatoController(String nome, String matricula, String setor){

        if ((nome != null && nome.length()>0) && (matricula != null && matricula.length()>0) && (setor != null && setor.length()>0)){

            FuncionarioModel novoFuncionario = new FuncionarioModel(nome,matricula, setor);
            JOptionPane.showMessageDialog(null,"Dados registrados com sucesso!");
            novoFuncionario.cadastrarFuncionarioDAO(novoFuncionario);


        }else {
            JOptionPane.showMessageDialog(null,"Digite os dados corretamente!");
        }

    }

    public ArrayList<FuncionarioModel> listarContatosModel(){
        FuncionarioModel funcionarios = new FuncionarioModel();
        return funcionarios.listarFuncionarios();
    }

    public ArrayList<FuncionarioModel> buscarContatosController(String nome){
        FuncionarioModel funcionarios = new FuncionarioModel();
        return funcionarios.buscarFuncionarios(nome);
    }

    public void alterarFuncionario(String id, String nome, String matricula, String setor){
        if ((id != null && id.length()>0) && (nome != null && nome.length()>0) && (matricula != null && matricula.length()>0) && (setor != null && setor.length()>0)){
            int idNumero = Integer.parseInt(id);
            FuncionarioModel ajusteFuncionario = new FuncionarioModel(idNumero, nome, matricula, setor);
            ajusteFuncionario.alterarFuncionario(ajusteFuncionario);
            JOptionPane.showMessageDialog(null,"Ajuste Realizado!");
        }else{
            JOptionPane.showMessageDialog(null,"Informações incorretas!");
        }
    }

}
