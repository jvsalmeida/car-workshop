package View;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Controller.ItemController;
import Model.ETipoItem;


public class ItemView extends ComUpdate{
	private static final long serialVersionUID = -6127814155704601686L;
	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox<ETipoItem> tipoField;
	private JTextField descricaoField;
	private JTextField precoField;
	
	private ETipoItem tipo;
	private String descricao;
	private String preco;
	
	
	private boolean isNumeric(String str)
    {
    	return str != null && str.matches("[0-9.]+");
    }
	
	private boolean getForm() {
        tipo = (ETipoItem) tipoField.getSelectedItem();
        descricao = descricaoField.getText();
        preco = precoField.getText();

        if (tipo == null  || descricao.length() <= 0 || !isNumeric(preco)) {
            return false;
        }

        return true;
    }
    
	
	@Override
	public DefaultTableModel getTableModel() {
		ItemController itemController = Controller.getItemController();
        return itemController.getTableModel();
	}
	@Override
	public void setTable() {
		lblNewLabel = new JLabel("Tipo:");
        lblNewLabel.setBounds(10, 302, 46, 14);
        add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o:");
        lblNewLabel_1.setBounds(10, 340, 60, 14);
        add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("Pre\u00E7o:");
        lblNewLabel_2.setBounds(10, 378, 46, 14);
        add(lblNewLabel_2);
        
        tipoField = new JComboBox<ETipoItem>(ETipoItem.values());
        tipoField.setBounds(117, 299, 164, 20);
        add(tipoField);
        
        descricaoField = new JTextField();
        descricaoField.setBounds(117, 337, 164, 20);
        add(descricaoField);
        descricaoField.setColumns(10);
        
        precoField = new JTextField();
        precoField.setBounds(117, 375, 164, 20);
        add(precoField);
        precoField.setColumns(10);
		
	}
	@Override
	public void setRowAction(Object id) {
		ItemController itemController = Controller.getItemController();
		Object[] newRow = itemController.pegar(id);

        tipoField.setSelectedItem(newRow[0]);
        descricaoField.setText(newRow[1].toString());
        precoField.setText(newRow[2].toString());
	}
	@Override
	public void adicionarAction() throws InvalidFormException, UpdateException {
		ItemController itemController = Controller.getItemController();
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        itemController.criar(tipo, descricao, Double.parseDouble(preco));
	}
	@Override
	public void atualizarAction(Object id) throws InvalidFormException, UpdateException {
		if (!getForm()) {
            throw new InvalidFormException("Error - parametros");
        }

        ItemController itemController = Controller.getItemController();

        itemController.atualizar(id, Double.parseDouble(preco));
	}
	@Override
	public void deletarAction(Object id) throws UpdateException {
		ItemController itemController = Controller.getItemController();
		itemController.deletar(id);
	}
	@Override
	public void clearTable() {
	    descricaoField.setText("");
	    precoField.setText("");
	}

}
