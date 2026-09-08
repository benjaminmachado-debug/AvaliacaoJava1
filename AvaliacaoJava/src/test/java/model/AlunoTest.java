package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes unitários da classe Aluno.
 *
 * como a classe Aluno eh um objeto de dados simples (armazena os atributos
 * informados no construtor e os expõe por meio de getters), os testes
 * verificam se os valores sao corretamente atribuídos e recuperados.
 */
class AlunoTest {

    @Test
    @DisplayName("Deve armazenar e retornar o nome informado no construtor")
    void deveRetornarNomeInformado() {
        Aluno aluno = new Aluno("Maria", 8, 9, 90);
        assertEquals("Maria", aluno.getNome());
    }

    @Test
    @DisplayName("Deve armazenar e retornar a nota1 informada no construtor")
    void deveRetornarNota1Informada() {
        Aluno aluno = new Aluno("Maria", 8, 9, 90);
        assertEquals(8, aluno.getNota1());
    }

    @Test
    @DisplayName("Deve armazenar e retornar a nota2 informada no construtor")
    void deveRetornarNota2Informada() {
        Aluno aluno = new Aluno("Maria", 8, 9, 90);
        assertEquals(9, aluno.getNota2());
    }

    @Test
    @DisplayName("Deve armazenar e retornar a frequência informada no construtor")
    void deveRetornarFrequenciaInformada() {
        Aluno aluno = new Aluno("Maria", 8, 9, 90);
        assertEquals(90, aluno.getFrequencia());
    }
}
