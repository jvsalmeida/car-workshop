package View;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OrdemServicoView extends ComUpdate{
	private static final long serialVersionUID = -6980144930881711002L;
	
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JTextField veiculoField;
    private JTextField kmField;
    private JTextField descricaoField;
    private JTextField consultorField;

	@Override
	public DefaultTableModel getTableModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTable() {
		lblNewLabel = new JLabel("Ve\u00EDculo:");
        lblNewLabel.setBounds(10, 291, 78, 14);
        add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Km Atual:");
        lblNewLabel_1.setBounds(10, 316, 78, 14);
        add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o:");
        lblNewLabel_2.setBounds(10, 341, 78, 14);
        add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("Consultor:");
        lblNewLabel_3.setBounds(10, 366, 78, 14);
        add(lblNewLabel_3);
        
        veiculoField = new JTextField();
        veiculoField.setBounds(117, 291, 160, 20);
        add(veiculoField);
        veiculoField.setColumns(10);
        
        kmField = new JTextField();
        kmField.setBounds(117, 313, 160, 20);
        add(kmField);
        kmField.setColumns(10);
        
        descricaoField = new JTextField();
        descricaoField.setBounds(117, 338, 160, 20);
        add(descricaoField);
        descricaoField.setColumns(10);
        
        consultorField = new JTextField();
        consultorField.setBounds(117, 363, 160, 20);
        add(consultorField);
        consultorField.setColumns(10);
	}

	@Override
	public void setRowAction(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarAction(Object id) throws InvalidFormException, UpdateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarAction(Object id) throws UpdateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearTable() {
		// TODO Auto-generated method stub
		
	}

}
