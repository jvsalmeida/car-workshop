package View;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Controller.ModeloController;

public class ModeloView extends SemUpdate{
	private static final long serialVersionUID = -5631853164105167466L;

    private JLabel lblModelo;
    private JTextField textField;
    
    private String modelo;
	
    private boolean getForm() {
        modelo = textField.getText();

        if (modelo.length() <= 0) {
            return false;
        }

        return true;
    }
    
	@Override
	public DefaultTableModel getTableModel() {
		ModeloController modeloController = Controller.getModeloController();
        return modeloController.getTableModel();
	}

	@Override
	public void setTable() {
		lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(10, 301, 66, 14);
        add(lblModelo);
        
        textField = new JTextField();
        textField.setBounds(120, 298, 139, 20);
        add(textField);
        textField.setColumns(10);
	}

	@Override
	public void setRowAction(Object id) {
		ModeloController modeloController = Controller.getModeloController();
		Object[] newRow = modeloController.pegar(id);

        textField.setText(newRow[0].toString());
	}

	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		ModeloController modeloController = Controller.getModeloController();
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        modeloController.criar(modelo);
	}

	@Override
	public void deletarAction(Object id) throws UpdateException {
		ModeloController modeloController = Controller.getModeloController();
		modeloController.deletar(id);
	}

	@Override
	public void clearTable() {
		textField.setText("");
	}

}
