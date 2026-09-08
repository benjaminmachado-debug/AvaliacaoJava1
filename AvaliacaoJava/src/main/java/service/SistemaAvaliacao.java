package service;

/**
 *
 * @author Ester
 */

import java.util.ArrayList;
import model.Aluno;

public class SistemaAvaliacao {
    private ArrayList<Aluno> alunos;

    public SistemaAvaliacao() {
        alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public ArrayList<Aluno> listarAlunos() {
        return alunos;
    }

    public int quantidadeAlunos() {
        return alunos.size();
    }
}
