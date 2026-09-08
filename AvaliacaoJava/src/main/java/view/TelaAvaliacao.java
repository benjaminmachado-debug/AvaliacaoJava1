package view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Aluno;
import model.Avaliacao;
import service.SistemaAvaliacao;

/**
 *
 * @author Ester
 */

public class TelaAvaliacao extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private transient Avaliacao avaliacao;
    private transient SistemaAvaliacao sistema;

    public TelaAvaliacao() {
        initComponents();
        avaliacao = new Avaliacao();
        sistema = new SistemaAvaliacao();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblNota1 = new javax.swing.JLabel();
        lblNota2 = new javax.swing.JLabel();
        lblFrequencia = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtNota1 = new javax.swing.JTextField();
        txtNota2 = new javax.swing.JTextField();
        txtFrequencia = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        tabelaAlunos = new javax.swing.JTable();
        scrollTabela = new javax.swing.JScrollPane();

        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Avaliação");
        lblTitulo.setFont( new java.awt.Font("Segoe UI", 1, 20));
        lblTitulo.setText("SISTEMA DE AVALIAÇÃO");
        lblNome.setText("Nome:");
        lblNota1.setText("Nota 1:");
        lblNota2.setText("Nota 2:");
        lblFrequencia.setText("Frequência (%):");
        btnAdicionar.setText("ADICIONAR ALUNO");
        tabelaAlunos.setModel( new DefaultTableModel( new Object[][]{}, new String[]{ "Nome", "Nota 1", "Nota 2", "Frequência", "Média", "Situação"}));
        scrollTabela.setViewportView(tabelaAlunos);
        btnAdicionar.addActionListener(evt -> { adicionarAluno();});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.CENTER )
                .addComponent(lblTitulo)
                .addGroup(
                        layout.createSequentialGroup()
                        .addGap(20)
                        .addGroup(
                                layout.createParallelGroup() .addGroup(
                                        layout.createSequentialGroup()
                                        .addComponent( lblNome, 100, 100, 100 )
                                        .addComponent( txtNome, 250, 250, 250 )
                                        ).addGroup(
                                        layout.createSequentialGroup()
                                        .addComponent( lblNota1, 100, 100, 100 )
                                        .addComponent( txtNota1, 250, 250, 250 )
                                ).addGroup(
                                        layout.createSequentialGroup()
                                        .addComponent( lblNota2, 100, 100, 100)
                                        .addComponent( txtNota2, 250, 250, 250)

                                ).addGroup(
                                        layout.createSequentialGroup()
                                        .addComponent( lblFrequencia, 100, 100, 100 )
                                        .addComponent( txtFrequencia, 250, 250, 250 )
                                ).addComponent(
                                        btnAdicionar
                                ).addComponent( scrollTabela, 650, 650, Short.MAX_VALUE)
                        ).addGap(20)
                )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGap(20)
                .addComponent(lblTitulo)
                .addGap(30)
                .addGroup(
                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(lblNome)
                        .addComponent(txtNome)
                )
                .addGap(15)
                .addGroup(
                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(lblNota1)
                        .addComponent(txtNota1)
                )
                .addGap(15)
                .addGroup(
                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(lblNota2)
                        .addComponent(txtNota2)
                )
                .addGap(15)
                .addGroup(
                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(lblFrequencia)
                        .addComponent(txtFrequencia)
                )
                .addGap(20)
                .addComponent(btnAdicionar)
                .addGap(30)
                .addComponent(
                        scrollTabela,
                        250,
                        250,
                        Short.MAX_VALUE
                )
                .addGap(20)
        );
        pack();
    }

    private void adicionarAluno() {

        try {
            String nome = txtNome.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog( this, "Digite o nome do aluno." );
                return;
            }

            double nota1 = Double.parseDouble(
                    txtNota1.getText().trim().replace(",", ".")
            );
            double nota2 = Double.parseDouble(
                    txtNota2.getText().trim().replace(",", ".")
            );
            double frequencia = Double.parseDouble(
                    txtFrequencia.getText().trim().replace(",", ".")
            );

            if (!avaliacao.validarNota(nota1) || !avaliacao.validarNota(nota2)) {

                JOptionPane.showMessageDialog(
                        this,  "As notas devem estar entre 0 e 10."
                );
                return;
            }

            if (!avaliacao.validarFrequencia(frequencia)) {
                JOptionPane.showMessageDialog( this, "A frequência deve estar entre 0 e 100.");
                return;
            }

            Aluno aluno = new Aluno( nome, nota1, nota2, frequencia);
            sistema.adicionarAluno(aluno);
            double media = avaliacao.calcularMedia( nota1, nota2);
            String situacao = avaliacao.verificarSituacao( media, frequencia);
            DefaultTableModel modelo = (DefaultTableModel) tabelaAlunos.getModel();
            modelo.addRow(
                    new Object[]{ nome, nota1, nota2, frequencia + "%", media, situacao}
            );

            limparCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Digite valores numéricos válidos.");
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtFrequencia.setText("");
        txtNome.requestFocus();
    }

    private javax.swing.JButton btnAdicionar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNota1;
    private javax.swing.JLabel lblNota2;
    private javax.swing.JLabel lblFrequencia;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNota1;
    private javax.swing.JTextField txtNota2;
    private javax.swing.JTextField txtFrequencia;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tabelaAlunos;
}