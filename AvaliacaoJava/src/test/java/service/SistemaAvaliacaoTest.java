package service;

import model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * testes unitários da classe SistemaAvaliacao.
 *
 * verifica o comportamento de cadastro e consulta de alunos armazenados
 * na lista interna da classe.
 */
class SistemaAvaliacaoTest {

    private SistemaAvaliacao sistema;

    @BeforeEach
    void setUp() {
        sistema = new SistemaAvaliacao();
    }

    @Test
    @DisplayName("Deve iniciar sem nenhum aluno cadastrado")
    void deveIniciarSemAlunos() {
        assertEquals(0, sistema.quantidadeAlunos());
        assertTrue(sistema.listarAlunos().isEmpty());
    }

    @Test
    @DisplayName("Deve adicionar um aluno à lista")
    void deveAdicionarAluno() {
        Aluno aluno = new Aluno("João", 7, 8, 80);

        sistema.adicionarAluno(aluno);

        assertEquals(1, sistema.quantidadeAlunos());
        assertTrue(sistema.listarAlunos().contains(aluno));
    }

    @Test
    @DisplayName("Deve contabilizar corretamente múltiplos alunos cadastrados")
    void deveContarMultiplosAlunos() {
        sistema.adicionarAluno(new Aluno("João", 7, 8, 80));
        sistema.adicionarAluno(new Aluno("Maria", 5, 6, 60));
        sistema.adicionarAluno(new Aluno("Pedro", 9, 10, 95));

        assertEquals(3, sistema.quantidadeAlunos());
    }

    @Test
    @DisplayName("Deve retornar a lista de alunos na ordem em que foram cadastrados")
    void deveListarAlunosNaOrdemDeCadastro() {
        Aluno joao = new Aluno("João", 7, 8, 80);
        Aluno maria = new Aluno("Maria", 5, 6, 60);

        sistema.adicionarAluno(joao);
        sistema.adicionarAluno(maria);

        ArrayList<Aluno> alunos = sistema.listarAlunos();
        assertEquals(joao, alunos.get(0));
        assertEquals(maria, alunos.get(1));
    }
}
