package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Modelo;
import View.UpdateException;

public class ModeloController implements Serializable {
	private static final long serialVersionUID = 2069402500420623454L;
	
	private Map<String, Modelo> modelos;
    
    public ModeloController() {
    	modelos = new TreeMap<>(); 
    }

    public void criar(String modelo) throws UpdateException {
    	if (modelos.get(modelo) != null) {
            throw new UpdateException("Modelo já existe.");
        }

        Modelo newModelo = new Modelo(modelo);

        if (modelos.put(modelo, newModelo) != null) {
            throw new UpdateException("Modelo não existe.");
        }
        
        Controller.writeFile();
    }

    public Object[] pegar(Object id) {
        Modelo modelo = modelos.get((String) id);

        Object[] row = {modelo.getNome()};

        return row;
    }

    public DefaultComboBoxModel<Modelo> getDefaultComboBoxModel() {
        DefaultComboBoxModel<Modelo> box = new DefaultComboBoxModel<>();

        for (Modelo modelo : modelos.values()) {
            box.addElement(modelo);
        }

        return box;
    }

    public void deletar(Object id) {
    	modelos.remove((String) id);
        
        Controller.writeFile();
    }

    public DefaultTableModel getTableModel() {
        Object[] header = {"Modelo"};

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var i: modelos.entrySet()) {
            model.addRow(new Object[] {i.getValue().getNome()});
        }

        return model;
    }

}
