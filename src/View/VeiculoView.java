package View;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ClienteController;
import Controller.Controller;
import Controller.ModeloController;
import Controller.VeiculoController;
import Model.ICliente;
import Model.Modelo;

public class VeiculoView extends ComUpdate{
	private static final long serialVersionUID = -1937288703608538083L;

    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JComboBox<ICliente> proprietarioField;
    private JComboBox<Modelo> modeloField;
    private JTextField chassiField;
    private JTextField anoField;
    private JTextField corField;
    private JTextField placaField;
    
	private Modelo modelo;
    private String chassi;
    private String ano;
    private String cor;
    private String placa;
    private ICliente proprietario;
	
    private boolean getForm() {
        proprietario = (ICliente) proprietarioField.getSelectedItem();
        placa = placaField.getText();
        cor = corField.getText();
        ano = anoField.getText();
        chassi = chassiField.getText();
        modelo = (Modelo) modeloField.getSelectedItem();

        if (modeloField.getSelectedItem() == null || chassi.length() <= 0|| ano.length() <= 0 
        		|| cor.length() <= 0 || placa.length() <= 0 || proprietarioField.getSelectedItem() == null) {
            return false;
        }

        return true;
    }
    
	@Override
	public DefaultTableModel getTableModel() {
		VeiculoController veiculoController = Controller.getVeiculoController();
        return veiculoController.getTableModel();
	}

	@Override
	public void setTable() {
		lblNewLabel = new JLabel("Propriet\u00E1rio:");
        lblNewLabel.setBounds(10, 310, 97, 14);
        add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Modelo:");
        lblNewLabel_1.setBounds(10, 345, 60, 14);
        add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("Chassi:");
        lblNewLabel_2.setBounds(10, 379, 60, 14);
        add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("Ano:");
        lblNewLabel_3.setBounds(10, 414, 46, 14);
        add(lblNewLabel_3);
        
        lblNewLabel_4 = new JLabel("Cor:");
        lblNewLabel_4.setBounds(10, 446, 46, 14);
        add(lblNewLabel_4);
        
        lblNewLabel_5 = new JLabel("Placa:");
        lblNewLabel_5.setBounds(10, 479, 60, 14);
        add(lblNewLabel_5);
        
        
        ClienteController clienteController = Controller.getClienteController();
        
        proprietarioField = new JComboBox<ICliente>(clienteController.getDefaultComboBoxModel());
        proprietarioField.setBounds(117, 307, 145, 20);
        add(proprietarioField);
        
        
        ModeloController modeloController = Controller.getModeloController();
        
        modeloField = new JComboBox<Modelo>(modeloController.getDefaultComboBoxModel());
        modeloField.setBounds(117, 342, 145, 20);
        add(modeloField);
        
        chassiField = new JTextField();
        chassiField.setBounds(117, 376, 145, 20);
        add(chassiField);
        chassiField.setColumns(10);
        
        anoField = new JTextField();
        anoField.setBounds(117, 411, 145, 20);
        add(anoField);
        anoField.setColumns(10);
        
        corField = new JTextField();
        corField.setBounds(117, 443, 145, 20);
        add(corField);
        corField.setColumns(10);
        
        placaField = new JTextField();
        placaField.setBounds(117, 476, 145, 20);
        add(placaField);
        placaField.setColumns(10);
		
	}

	@Override
	public void setRowAction(Object id) {
		VeiculoController veiculoController = Controller.getVeiculoController();
		Object[] newRow = veiculoController.pegar(id);

        proprietarioField.setSelectedItem(newRow[0]);
        modeloField.setSelectedItem(newRow[1]);
        chassiField.setText(newRow[2].toString());
        anoField.setText(newRow[3].toString());
        corField.setText(newRow[4].toString());
        placaField.setText(newRow[5].toString());
	}

	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		VeiculoController veiculoController = Controller.getVeiculoController();
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        veiculoController.criar(modelo, chassi, Integer.parseInt(ano), cor, placa, proprietario);
	}

	@Override
	public void atualizarAction(Object id) throws InvalidFormException, UpdateException {
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        VeiculoController veiculoController = Controller.getVeiculoController();

        veiculoController.atualizar(id, cor, placa, proprietario);
	}

	@Override
	public void deletarAction(Object id) throws UpdateException {
		VeiculoController veiculoController = Controller.getVeiculoController();
		veiculoController.deletar(id);
	}

	@Override
	public void clearTable() {
	    chassiField.setText("");
	    anoField.setText("");
	    corField.setText("");
	    placaField.setText("");
	}

}
