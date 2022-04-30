package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Cidade;
import View.UpdateException;

public class CidadeController implements Serializable {

	private static final long serialVersionUID = -4958235871075045401L;
	
	private Map<String, Cidade> cidades;
    
    public CidadeController() {
    	cidades = new TreeMap<>(); 
    }

    public void criar(String cidade, String UF) throws UpdateException {
    	if (cidades.get(cidade) != null) {
            throw new UpdateException("Cidade já existe.");
        }

        Cidade newCity = new Cidade(cidade, UF);

        if (cidades.put(cidade, newCity) != null) {
            throw new UpdateException("Cidade não existe.");
        }
        
        Controller.writeFile();
    }

    public Object[] pegar(Object id) {
        Cidade cidade = cidades.get((String) id);

        Object[] row = {cidade.getNome(), cidade.getUF()};

        return row;
    }

    public DefaultComboBoxModel<Cidade> getDefaultComboBoxModel() {
        DefaultComboBoxModel<Cidade> box = new DefaultComboBoxModel<>();

        for (Cidade cidade : cidades.values()) {
            box.addElement(cidade);
        }

        return box;
    }

    public void deletar(Object id) {
    	cidades.remove((String) id);
        
        Controller.writeFile();
    }

    public DefaultTableModel getTableModel() {
        Object[] header = {"Cidade", "UF"};

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var i: cidades.entrySet()) {
            model.addRow(new Object[] {i.getValue().getNome(), i.getValue().getUF()});
        }

        return model;
    }
}