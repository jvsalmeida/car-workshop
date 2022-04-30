package View;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CidadeController;
import Controller.Controller;

public class CidadeView extends SemUpdate
{
	private static final long serialVersionUID = 5926339939012935313L;
	
	private JTextField UFField;
    private JTextField CidadeField;
    private String cidade;
    private String UF;

    private boolean getForm() {
        cidade = CidadeField.getText();
        UF = UFField.getText();

        if (cidade.length() <= 0 || UF.length() <= 0) {
            return false;
        }

        return true;
    }


    @Override
    public DefaultTableModel getTableModel() {
    	CidadeController cidadeController = Controller.getCidadeController();
        return cidadeController.getTableModel();
    }


    @Override
    public void clearTable() {
        CidadeField.setText("");
        UFField.setText("");
    }


	@Override
    public void setTable() {
        JLabel lblNewLabel = new JLabel("cidade:");
        lblNewLabel.setBounds(10, 304, 97, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("UF:");
        lblNewLabel_1.setBounds(10, 345, 46, 14);
        add(lblNewLabel_1);
        
        UFField = new JTextField();
        UFField.setBounds(117, 342, 187, 20);
        add(UFField);
        UFField.setColumns(10);
        
        CidadeField = new JTextField();
        CidadeField.setBounds(117, 301, 187, 20);
        add(CidadeField);
        CidadeField.setColumns(10);
    }

	@Override
	public void setRowAction(Object id) {
		CidadeController cidadeController = Controller.getCidadeController();
		Object[] newRow = cidadeController.pegar(id);

        CidadeField.setText(newRow[0].toString());
        UFField.setText(newRow[1].toString());
		
	}

	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		CidadeController cidadeController = Controller.getCidadeController();
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        cidadeController.criar(cidade, UF);        	
	}

	@Override
	public void deletarAction(Object id) throws UpdateException {
		CidadeController cidadeController = Controller.getCidadeController();
		cidadeController.deletar(id);
	}
}
