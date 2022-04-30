package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Categoria;
import View.UpdateException;

public class CategoriaController implements Serializable{
	private static final long serialVersionUID = 5819528046039094822L;
	
private Map<String, Categoria> categorias;
    
    public CategoriaController() {
    	categorias = new TreeMap<>(); 
    }

    public void criar(String categoria) throws UpdateException {
    	if (categorias.get(categoria) != null) {
            throw new UpdateException("Categoria já existe.");
        }

        Categoria newCategoria = new Categoria(categoria);

        if (categorias.put(categoria, newCategoria) != null) {
            throw new UpdateException("Categoria não existe.");
        }
        
        Controller.writeFile();
    }

    public Object[] pegar(Object id) {
        Categoria categoria = categorias.get((String) id);

        Object[] row = {categoria.getNome()};

        return row;
    }

    public DefaultComboBoxModel<Categoria> getDefaultComboBoxModel() {
        DefaultComboBoxModel<Categoria> box = new DefaultComboBoxModel<>();

        for (Categoria categoria : categorias.values()) {
            box.addElement(categoria);
        }

        return box;
    }

    public void deletar(Object id) {
    	categorias.remove((String) id);
        
        Controller.writeFile();
    }

    public DefaultTableModel getTableModel() {
        Object[] header = {"Categoria"};

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var i: categorias.entrySet()) {
            model.addRow(new Object[] {i.getValue().getNome()});
        }

        return model;
    }

}
