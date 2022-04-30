package View;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CidadeController;
import Controller.Controller;
import Controller.FuncionarioController;
import Model.Cidade;

public class FuncionarioView extends ComUpdate{
	private static final long serialVersionUID = -5997933644304442297L;
	
	private String cpf;
	private String nome;
	private String tel;
	private String email;
	private String rua;
	private String num;
	private String bairro;
	private Cidade cidade;
	
	private JTextField cpfField;
    private JTextField nomeField;
    private JTextField telField;
    private JTextField emailField;
    private JTextField ruaField;
    private JTextField numField;
    private JTextField bairroField;
    private JComboBox<Cidade> cidadeBox;
	
	
	@Override
	public DefaultTableModel getTableModel() {
		FuncionarioController funcionarioController = Controller.getFuncionarioController();
        return funcionarioController.getTableModel();
	}
	
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

        if (!isNumeric(cpf) || nome.length() <= 0 || !isNumeric(tel) || rua.length() <= 0|| !isNumeric(num)
        		|| cidadeBox.getSelectedItem() == null || bairro.length() <= 0) {
            return false;
        }

        return true;
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
        
        CidadeController cidadeController = Controller.getCidadeController();

        JLabel lblNewLabel_8 = new JLabel("Cidade:");
        lblNewLabel_8.setBounds(391, 473, 54, 14);
        add(lblNewLabel_8);
        
        cidadeBox = new JComboBox<Cidade>(cidadeController.getDefaultComboBoxModel());
        cidadeBox.setBounds(353, 488, 121, 23);
        add(cidadeBox);
	}

	@Override
	public void setRowAction(Object id) {
		FuncionarioController funcionarioController = Controller.getFuncionarioController();
		Object[] newRow = funcionarioController.pegar(id);

        cpfField.setText(newRow[0].toString());
        nomeField.setText(newRow[1].toString());
        telField.setText(newRow[2].toString());
        emailField.setText(newRow[3].toString());
        ruaField.setText(newRow[4].toString());
        numField.setText(newRow[5].toString());
        bairroField.setText(newRow[6].toString());
        cidadeBox.setSelectedItem(newRow[7]);
	}

	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		FuncionarioController funcionarioController = Controller.getFuncionarioController();
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        funcionarioController.criar(Long.parseLong(cpf), nome, Long.parseLong(tel), email, rua,
        		Integer.parseInt(num), bairro, cidade);
	}

	@Override
	public void atualizarAction(Object id) throws InvalidFormException, UpdateException {
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        FuncionarioController funcionarioController = Controller.getFuncionarioController();

        funcionarioController.atualizar(id, nome, Long.parseLong(tel), email, rua,
        		Integer.parseInt(num), bairro, cidade);
		
	}

	@Override
	public void deletarAction(Object id) throws UpdateException {
		FuncionarioController funcionarioController = Controller.getFuncionarioController();
		funcionarioController.deletar(id);
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
	    cidadeBox.setSelectedItem("");
	}

}
