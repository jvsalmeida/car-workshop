package View;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import Controller.CidadeController;
import Controller.ClienteController;
import Controller.Controller;
import Model.Cidade;

public class ClienteView extends ComUpdate{
	public ClienteView() {
	}
	private static final long serialVersionUID = 9207183155982874943L;
	
	private String cpf;
	private String nome;
	private String tel;
	private String email;
	private String rua;
	private String num;
	private String bairro;
	private boolean sim;
	private Cidade cidade;
	
	private JTextField cpfField;
    private JTextField nomeField;
    private JTextField telField;
    private JTextField emailField;
    private JTextField ruaField;
    private JTextField numField;
    private JTextField bairroField;
    private JToggleButton tglbtnSim;
    private JComboBox<Cidade> cidadeBox;
    
    private boolean isNumeric(String str)
    {
    	return str != null && str.matches("[0-9.]+");
    }
    
    private boolean getForm() {
        cpf = cpfField.getText();
        nome = nomeField.getText();
        tel = telField.getText();
        email = emailField.getText();
        rua = ruaField.getText();
        num = numField.getText();
        bairro = bairroField.getText();
        cidade = (Cidade)cidadeBox.getSelectedItem();
        sim = tglbtnSim.isSelected();

        if (!isNumeric(cpf) || nome.length() <= 0 || !isNumeric(tel) || rua.length() <= 0|| !isNumeric(num)
        		|| cidadeBox.getSelectedItem() == null || bairro.length() <= 0) {
            return false;
        }

        return true;
    }
	
	@Override
	public DefaultTableModel getTableModel() {
		ClienteController clienteController = Controller.getClienteController();
        return clienteController.getTableModel();
	}

	@Override
	public void setTable() {
		JLabel lblNewLabel = new JLabel("CPF:");
        lblNewLabel.setBounds(20, 291, 46, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nome:");
        lblNewLabel_1.setBounds(20, 328, 46, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Telefone:");
        lblNewLabel_2.setBounds(20, 368, 54, 14);
        
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Email:");
        lblNewLabel_3.setBounds(20, 405, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Rua:");
        lblNewLabel_4.setBounds(20, 447, 46, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("N\u00FAmero:");
        lblNewLabel_5.setBounds(20, 489, 54, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Bairro:");
        lblNewLabel_6.setBounds(20, 524, 46, 14);
        add(lblNewLabel_6);
        
        cpfField = new JTextField();
        cpfField.setBounds(117, 291, 173, 20);
        add(cpfField);
        cpfField.setColumns(10);
        
        nomeField = new JTextField();
        nomeField.setBounds(117, 325, 173, 20);
        add(nomeField);
        nomeField.setColumns(10);
        
        telField = new JTextField();
        telField.setBounds(117, 365, 173, 20);
        add(telField);
        telField.setColumns(10);
        
        emailField = new JTextField();
        emailField.setBounds(117, 402, 173, 20);
        add(emailField);
        emailField.setColumns(10);
        
        ruaField = new JTextField();
        ruaField.setBounds(117, 444, 173, 20);
        add(ruaField);
        ruaField.setColumns(10);
        
        numField = new JTextField();
        numField.setBounds(117, 486, 173, 20);
        add(numField);
        numField.setColumns(10);
        
        bairroField = new JTextField();
        bairroField.setBounds(117, 521, 173, 20);
        add(bairroField);
        bairroField.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Platinum:");
        lblNewLabel_7.setBounds(391, 413, 54, 14);
        add(lblNewLabel_7);
        
        CidadeController cidadeController = Controller.getCidadeController();

        JLabel lblNewLabel_8 = new JLabel("Cidade:");
        lblNewLabel_8.setBounds(391, 473, 54, 14);
        add(lblNewLabel_8);
        
        cidadeBox = new JComboBox<Cidade>(cidadeController.getDefaultComboBoxModel());
        cidadeBox.setBounds(353, 488, 121, 23);
        add(cidadeBox);
        
        tglbtnSim = new JToggleButton("Sim");
        tglbtnSim.setBounds(353, 438, 121, 23);
        add(tglbtnSim);
	}

	@Override
	public void setRowAction(Object id) {
		ClienteController clienteController = Controller.getClienteController();
		Object[] newRow = clienteController.pegar(id);

        cpfField.setText(newRow[0].toString());
        nomeField.setText(newRow[1].toString());
        telField.setText(newRow[2].toString());
        emailField.setText(newRow[3].toString());
        ruaField.setText(newRow[4].toString());
        numField.setText(newRow[5].toString());
        bairroField.setText(newRow[6].toString());
        tglbtnSim.setSelected((boolean) newRow[7]);
	}

	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		ClienteController clienteController = Controller.getClienteController();
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        clienteController.criar(Long.parseLong(cpf), nome, Long.parseLong(tel), email, rua,
        		Integer.parseInt(num), bairro, cidade, sim);
	}

	@Override
	public void atualizarAction(Object id) throws InvalidFormException, UpdateException {
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        ClienteController clienteController = Controller.getClienteController();

        clienteController.atualizar(id, nome, Long.parseLong(tel), email, rua,
        		Integer.parseInt(num), bairro, cidade, sim);
	}

	@Override
	public void deletarAction(Object id) throws UpdateException {
		ClienteController clienteController = Controller.getClienteController();
		clienteController.deletar(id);
	}

	@Override
	public void clearTable() {
		cpfField.setText("");
	    nomeField.setText("");
	    telField.setText("");
	    emailField.setText("");
	    ruaField.setText("");
	    numField.setText("");
	    bairroField.setText("");
	    tglbtnSim.setSelected(false);
	}

}
