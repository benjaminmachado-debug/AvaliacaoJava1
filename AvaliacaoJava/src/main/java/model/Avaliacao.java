package model;

public class Avaliacao {

    public boolean validarNota(double nota) {
        return nota >= 0 && nota <= 10;
    }

    public boolean validarFrequencia(double frequencia) {
        return frequencia >= 0 && frequencia <= 100;
    }

    public double calcularMedia(double nota1, double nota2) {
        return (nota1 + nota2) / 2;
    }

    public boolean possuiFrequenciaMinima(
            double frequencia) {
        return frequencia >= 75;
    }

    public String verificarSituacao(
            double media,
            double frequencia) {
        if (!possuiFrequenciaMinima(frequencia)) {
            return "REPROVADO POR FREQUÊNCIA";
        }

        if (media >= 7) {
            return "APROVADO";
        } else if (media >= 5) {
            return "RECUPERAÇÃO";
        } else {
            return "REPROVADO";
        }
    }
}
