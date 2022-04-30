package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public abstract class SemUpdate extends JPanel
{
    private static final long serialVersionUID = 4828543939584285181L;

    private JButton btnAdicionar;
    private JButton btnDeletar;
    private JTable table;
    private JPanel panel;

    private DefaultTableModel model;
    private Object id;

    public SemUpdate() {
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

        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(377, 257, 97, 23);
        btnDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deletarAction(id);
                    JOptionPane.showMessageDialog(panel, "Sucesso!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (UpdateException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                table.setModel(getTableModel());
                clearTable();
            }
        });

        this.setLayout(null);
        this.add(btnAdicionar);
        this.add(btnDeletar);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(panel);
        
        setTable();
        
        setVisible(true);
    }


    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public abstract DefaultTableModel getTableModel();
    public abstract void setTable();
    public abstract void setRowAction(Object id);
    public abstract void adicionarAction() throws InvalidFormException, UpdateException;
    public abstract void deletarAction(Object id) throws UpdateException;
    public abstract void clearTable();
}