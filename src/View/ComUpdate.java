package View;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public abstract class ComUpdate extends JPanel
{
	private static final long serialVersionUID = 8554520429284832025L;
	
    private JButton btnAtualizar;
    private JButton btnAdicionar;
    private JButton btnDeletar;
    private JTable table;
    private JPanel panel;

    private DefaultTableModel model;
    private Object id;
    
    public ComUpdate() {
        this.model = getTableModel();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 13, 464, 233);
        this.add(scrollPane);
        table = new JTable(this.model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
                	id = table.getValueAt(selectedRow, 0);
                    setRowAction(id);
                }
            }
        });
        scrollPane.setViewportView(table);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(10, 257, 97, 23);
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    adicionarAction();
                    JOptionPane.showMessageDialog(panel, "Sucesso!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    table.setModel(getTableModel());
                    clearTable();
                } catch (InvalidFormException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (UpdateException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(117, 257, 97, 23);
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    atualizarAction(id);
                    JOptionPane.showMessageDialog(panel, "Record Updated successfully.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    table.setModel(getTableModel());
                    clearTable();
                } catch (InvalidFormException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (UpdateException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(377, 257, 97, 23);
        btnDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deletarAction(id);
                    JOptionPane.showMessageDialog(panel, "Sucesso!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    table.setModel(getTableModel());
                    clearTable();
                } catch (UpdateException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.setLayout(null);
        this.add(btnAdicionar);
        this.add(btnAtualizar);
        this.add(btnDeletar);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(panel);

        setTable();
        setVisible(true);
    }

    protected void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public abstract DefaultTableModel getTableModel();
    public abstract void setTable();
    public abstract void setRowAction(Object id);
    public abstract void adicionarAction() throws InvalidFormException, UpdateException;
    public abstract void atualizarAction(Object id) throws InvalidFormException, UpdateException;
    public abstract void deletarAction(Object id) throws UpdateException;
    public abstract void clearTable();
}