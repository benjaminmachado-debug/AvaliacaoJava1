package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 
class AvaliacaoTest {

    private Avaliacao avaliacao;

    @BeforeEach
    void setUp() {
        avaliacao = new Avaliacao();
    }

    // ---------------------------------------------------------------
    // classes: nota < 0 (inválida) | 0 <= nota <= 10 (válida) | nota > 10 (inválida)
    // ---------------------------------------------------------------

    @Test
    @DisplayName("Deve aceitar nota dentro da faixa válida (partição válida)")
    void deveAceitarNotaValida() {
        assertTrue(avaliacao.validarNota(7.5));
    }

    @Test
    @DisplayName("Deve recusar nota negativa (partição inválida inferior)")
    void deveRecusarNotaNegativa() {
        assertFalse(avaliacao.validarNota(-3));
    }

    @Test
    @DisplayName("Deve recusar nota acima de 10 (partição inválida superior)")
    void deveRecusarNotaAcimaDoMaximo() {
        assertFalse(avaliacao.validarNota(15));
    }

    // analise de Valor-Limite para nota: limites 0 e 10
    @ParameterizedTest(name = "nota={0} -> válida={1}")
    @DisplayName("Deve validar nota nos valores-limite (0 e 10)")
    @CsvSource({
            "-0.1, false",
            "0,    true",  
            "0.1,  true",  
            "9.9,  true",  
            "10,   true",  
            "10.1, false"  
    })
    void deveValidarNotaNosLimites(double nota, boolean esperado) {
        assertEquals(esperado, avaliacao.validarNota(nota));
    }

    // ---------------------------------------------------------------
    // Classes: frequencia < 0 (inválida) | 0 <= frequencia <= 100 (válida) | frequencia > 100 (inválida)
    // ---------------------------------------------------------------

    @Test
    @DisplayName("Deve aceitar frequência dentro da faixa válida (partição válida)")
    void deveAceitarFrequenciaValida() {
        assertTrue(avaliacao.validarFrequencia(80));
    }

    @Test
    @DisplayName("Deve recusar frequência negativa (partição inválida inferior)")
    void deveRecusarFrequenciaNegativa() {
        assertFalse(avaliacao.validarFrequencia(-1));
    }

    @Test
    @DisplayName("Deve recusar frequência acima de 100 (partição inválida superior)")
    void deveRecusarFrequenciaAcimaDoMaximo() {
        assertFalse(avaliacao.validarFrequencia(150));
    }

    // analise de Valor-Limite para frequência: limites 0 e 100
    @ParameterizedTest(name = "frequencia={0} -> válida={1}")
    @DisplayName("Deve validar frequência nos valores-limite (0 e 100)")
    @CsvSource({
            "-0.1, false",
            "0,    true",
            "0.1,  true",
            "99.9, true",
            "100,  true",
            "100.1, false"
    })
    void deveValidarFrequenciaNosLimites(double frequencia, boolean esperado) {
        assertEquals(esperado, avaliacao.validarFrequencia(frequencia));
    }

    // ---------------------------------------------------------------
    // calcularMedia(double, double) — Teste funcional (caixa-preta)
    // ---------------------------------------------------------------

    @Test
    @DisplayName("Deve calcular a média corretamente para notas distintas")
    void deveCalcularMediaDeNotasDistintas() {
        assertEquals(7.5, avaliacao.calcularMedia(7, 8));
    }

    @Test
    @DisplayName("Deve calcular a média corretamente quando ambas as notas são iguais")
    void deveCalcularMediaDeNotasIguais() {
        assertEquals(5.0, avaliacao.calcularMedia(5, 5));
    }

    @Test
    @DisplayName("Deve calcular a média zero quando as duas notas forem zero")
    void deveCalcularMediaZero() {
        assertEquals(0.0, avaliacao.calcularMedia(0, 0));
    }

    // ---------------------------------------------------------------
    // possuiFrequenciaMinima(double) — Análise de Valor-Limite (limite 75)
    // ---------------------------------------------------------------

    @ParameterizedTest(name = "frequencia={0} -> possuiMinima={1}")
    @DisplayName("Deve verificar a frequência mínima exigida (limite 75%)")
    @CsvSource({
            "74.9, false", // imediatamente antes do limite
            "75,   true",  // no limite
            "75.1, true"   // imediatamente depois do limite
    })
    void deveVerificarFrequenciaMinimaNoLimite(double frequencia, boolean esperado) {
        assertEquals(esperado, avaliacao.possuiFrequenciaMinima(frequencia));
    }

    // frequencia < 75      -> REPROVADO POR FREQUÊNCIA
    // media >= 7            -> APROVADO
    // 5 <= media < 7        -> RECUPERAÇÃO
    // media < 5              -> REPROVADO
    // ---------------------------------------------------------------

    @Test
    @DisplayName("Deve reprovar por frequência quando a frequência é insuficiente, mesmo com média alta")
    void deveReprovarPorFrequenciaMesmoComMediaAlta() {
        assertEquals("REPROVADO POR FREQUÊNCIA", avaliacao.verificarSituacao(9, 50));
    }

    @Test
    @DisplayName("Deve aprovar quando a média é maior ou igual a 7 e a frequência é suficiente")
    void deveAprovarComMediaAltaEFrequenciaSuficiente() {
        assertEquals("APROVADO", avaliacao.verificarSituacao(8, 90));
    }

    @Test
    @DisplayName("Deve colocar em recuperação quando a média está entre 5 e 6.9")
    void deveColocarEmRecuperacaoComMediaIntermediaria() {
        assertEquals("RECUPERAÇÃO", avaliacao.verificarSituacao(6, 90));
    }

    @Test
    @DisplayName("Deve reprovar quando a média é menor que 5, com frequência suficiente")
    void deveReprovarComMediaBaixa() {
        assertEquals("REPROVADO", avaliacao.verificarSituacao(3, 90));
    }

    // Valor-limite da situação: fronteiras de média em 5 e 7
    @ParameterizedTest(name = "media={0} -> situacao={1}")
    @DisplayName("Deve respeitar os valores-limite da média (5 e 7) na definição da situação")
    @CsvSource({
            "4.9, REPROVADO",
            "5,   RECUPERAÇÃO",
            "6.9, RECUPERAÇÃO",
            "7,   APROVADO"
    })
    void deveRespeitarLimitesDeMediaNaSituacao(double media, String situacaoEsperada) {
        assertEquals(situacaoEsperada, avaliacao.verificarSituacao(media, 100));
    }
}