package View;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CategoriaController;
import Controller.Controller;

public class CategoriaView extends SemUpdate{
	private static final long serialVersionUID = 2660694046332354616L;
	
    private JLabel lblNewLabel;
    private JTextField nomeField;
    
    private String categoria;

	@Override
	public DefaultTableModel getTableModel() {
		CategoriaController categoriaController = Controller.getCategoriaController();
        return categoriaController.getTableModel();
	}
	
	private boolean getForm() {
        categoria = nomeField.getText();

        if (categoria.length() <= 0) {
            return false;
        }

        return true;
    }

	@Override
	public void setTable() {
		lblNewLabel = new JLabel("Nome:");
        lblNewLabel.setBounds(10, 301, 64, 14);
        add(lblNewLabel);
        
        nomeField = new JTextField();
        nomeField.setBounds(120, 298, 120, 20);
        add(nomeField);
        nomeField.setColumns(10);
	}

	@Override
	public void setRowAction(Object id) {
		CategoriaController categoriaController = Controller.getCategoriaController();
		Object[] newRow = categoriaController.pegar(id);

		nomeField.setText(newRow[0].toString());
	}

	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		CategoriaController categoriaController = Controller.getCategoriaController();
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        categoriaController.criar(categoria);
	}

	@Override
	public void deletarAction(Object id) throws UpdateException {
		CategoriaController categoriaController = Controller.getCategoriaController();
		categoriaController.deletar(id);
	}

	@Override
	public void clearTable() {
		nomeField.setText("");
	}

}
