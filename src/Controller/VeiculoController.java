package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.ICliente;
import Model.Modelo;
import Model.Veiculo;
import View.UpdateException;

public class VeiculoController implements Serializable
{
	private static final long serialVersionUID = 4148665041529563631L;
	
	private Map<String, Veiculo> veiculos;
    
    public VeiculoController()
    {
    	veiculos = new TreeMap<>(); 
    }
    
    public DefaultComboBoxModel<Veiculo> getDefaultComboBoxModel() {
        DefaultComboBoxModel<Veiculo> box = new DefaultComboBoxModel<>();

        for (Veiculo i : veiculos.values()) {
            box.addElement(i);
        }

        return box;
    }
    
    public void criar(Modelo modelo, String chassi, int ano, String cor, String placa,
    		ICliente proprietario) throws UpdateException
    {
    	if (veiculos.get(chassi) != null) {
            throw new UpdateException("Veiculo ja existe");
        }
        Veiculo veiculo = new Veiculo(proprietario, modelo, chassi, ano, cor, placa);

        if (veiculos.put(chassi, veiculo) != null) {
            throw new UpdateException("Erro na criaçao do Veiculo");
        }
        
        Controller.writeFile();
    }
    
    public void atualizar(Object id, String cor, String placa, ICliente proprietario) throws UpdateException {
        Veiculo veiculo = (Veiculo) veiculos.get(id);

        if (veiculo == null) {
            throw new UpdateException("Veiculo não existe");
        }

        veiculo.setCor(cor);
        veiculo.setPlaca(placa);
        veiculo.setProprietario(proprietario);

        Controller.writeFile();
    }
    
    public Object[] pegar(Object id) {
        Veiculo veiculo = veiculos.get((String) id);

        Object[] row = {veiculo.getProprietario(), veiculo.getModelo(), veiculo.getChassi(),
                veiculo.getAno(), veiculo.getCor(), veiculo.getPlaca()};
        return row;
    }
    
    public void deletar(Object id) throws UpdateException {
		Veiculo veiculo = veiculos.get((String) id);
		
		if (veiculo == null) {
		    throw new UpdateException("Veiculo não existe");
		}		
		veiculos.remove((String) id);
		        
		Controller.writeFile();
    }
    
    public DefaultTableModel getTableModel() {
    	Object[] header = {"Chassi", "Modelo", "Proprietário", "Ano", "Cor", "Placa"};

		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		for (var i : veiculos.entrySet()) {
		    Object[] row = { i.getValue().getChassi(), i.getValue().getModelo(), i.getValue().getProprietario(),
		            i.getValue().getAno(), i.getValue().getCor(),
		            i.getValue().getPlaca()};
		
		    model.addRow(row);
		}
		
		return model;
    }
    
    

}
