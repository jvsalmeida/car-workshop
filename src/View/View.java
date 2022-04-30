package View;

import java.awt.event.*;

import javax.swing.*;

public class View
{
    private JFrame frame;

    public View() {
        frame = new JFrame("Jnaela Inicial");
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnNew = new JMenu("Janelas");
        menuBar.add(mnNew);

        JMenuItem mntmCategoria = new JMenuItem("Categoria");
        mntmCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Janela Categoria");
                frame.setBounds(100, 100, 500, 400);
                trocaContentPane(new CategoriaView());
            }
        });
        mnNew.add(mntmCategoria);

        JMenuItem mntmCidade = new JMenuItem("Cidade");
        mntmCidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Janela Cidade");
                frame.setBounds(100, 100, 500, 450);
                trocaContentPane(new CidadeView());
            }
        });
        mnNew.add(mntmCidade);

        JMenuItem mntmCliente = new JMenuItem("Cliente");
        mntmCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setTitle("Janela Cliente");
        		frame.setBounds(100, 100, 500, 650);
        		trocaContentPane(new ClienteView());
        	}
        });
        mnNew.add(mntmCliente);
        
        JMenuItem mntmFuncionario = new JMenuItem("Funcionario");
        mntmFuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Janela Funcionario");
                frame.setBounds(100, 100, 500, 650);
                trocaContentPane(new FuncionarioView());
            }
        });
        mnNew.add(mntmFuncionario);

        JMenuItem mntmNewItem = new JMenuItem("Item");
        mntmNewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Janela Item");
                frame.setBounds(100, 100, 500, 550);
                trocaContentPane(new ItemView());
            }
        });
        mnNew.add(mntmNewItem);

        JMenuItem mntmModelo = new JMenuItem("Modelo");
        mntmModelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Jnela Modelo");
                frame.setBounds(100, 100, 500, 450);
                trocaContentPane(new ModeloView());
            }
        });
        mnNew.add(mntmModelo);

        JMenuItem mntmServico = new JMenuItem("Servicos");
        mntmServico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Janela Servicos");
                frame.setBounds(100, 100, 500, 480);
                trocaContentPane(new OrdemServicoView());
            }
        });
        mnNew.add(mntmServico);

        JMenuItem mntmVeiculo = new JMenuItem("Veiculos");
        mntmVeiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Janela Veiculos");
                frame.setBounds(100, 100, 500, 600);
                trocaContentPane(new VeiculoView());
            }
        });
        mnNew.add(mntmVeiculo);

        JMenuItem mntnSair = new JMenuItem("Sair");
        mntnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnNew.add(mntnSair);

        frame.setVisible(true);
    }


    private void trocaContentPane(JPanel newPanel) {
        JPanel panel = (JPanel) frame.getContentPane();

        panel.removeAll();
        panel.add(newPanel);
        panel.revalidate();
        panel.repaint();
    }
}