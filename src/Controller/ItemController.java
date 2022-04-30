package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.ETipoItem;
import Model.IItem;
import Model.Item;
import View.UpdateException;

public class ItemController implements Serializable{
	private static final long serialVersionUID = -8791575043004148362L;
	
private Map<Long, IItem> itens;
    
    public ItemController() {
    	itens = new TreeMap<>(); 
    }

    public void criar(ETipoItem tipo, String descricao, double preco) throws UpdateException {
        Item newItem = new Item(tipo, descricao);

        if (preco == 0) {
        	newItem.setPreco(preco);
        }

        if (itens.put(newItem.getCodigo(), newItem) != null) {
            throw new UpdateException("Erro na criaçao do Item");
        }
        
        Controller.writeFile();
    }

    public Object[] pegar(Object id) {
        IItem item = itens.get((long) id);

        Object[] row = {item.getTipo(), item.getDescricao(), item.getPreco()};

        return row;
    }

    public DefaultComboBoxModel<IItem> getDefaultComboBoxModel() {
        DefaultComboBoxModel<IItem> box = new DefaultComboBoxModel<>();

        for (IItem i : itens.values()) {
            box.addElement(i);
        }

        return box;
    }
    
    public void atualizar(Object id, double preco) throws UpdateException {
        Item item = (Item) itens.get((long)id);

        if (item == null) {
            throw new UpdateException("Item não existe");
        }

        item.setPreco(preco);
     
        Controller.writeFile();
    }

    public void deletar(Object id) throws UpdateException {
    	IItem item = itens.get((long) id);
		
		if (item == null) {
		    throw new UpdateException("Item não existe");
		}
		
		itens.remove((Long) id);
		        
		Controller.writeFile();
    }

    public DefaultTableModel getTableModel() {
        Object[] header = {"Código", "Tipo", "Descricao", "Preco"};

        DefaultTableModel item = new DefaultTableModel(header, 0);

        for (var i: itens.entrySet()) {
        	Object[] row = {i.getValue().getCodigo(), i.getValue().getTipo(), i.getValue().getDescricao(),
        			i.getValue().getPreco()};
 
        	item.addRow(row);
        }
        
        return item;
    }

}
