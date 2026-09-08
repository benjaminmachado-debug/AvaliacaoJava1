/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ester
 */

public class Aluno {

    private String nome;
    private double nota1;
    private double nota2;
    private double frequencia;

    public Aluno(String nome, double nota1,
                 double nota2, double frequencia) {

        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.frequencia = frequencia;
    }

    public String getNome() {
        return nome;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getFrequencia() {
        return frequencia;
    }

}
