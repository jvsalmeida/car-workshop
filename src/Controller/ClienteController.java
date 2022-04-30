package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Cidade;
import Model.Cliente;
import Model.Endereco;
import Model.ICliente;
import View.UpdateException;

public class ClienteController implements Serializable{
	private static final long serialVersionUID = -6813421863993669419L;
	
	private Map<Long, ICliente> clientes;
    
    public ClienteController() {
    	clientes = new TreeMap<>(); 
    }

    public void criar(long cpf, String nome, long telefone, String email,
            String rua, int numero, String bairro,
            Cidade cidade, boolean platinum)  throws UpdateException {
    	if (clientes.get(cpf) != null) {
            throw new UpdateException("Cliente ja existe");
        }

        EnderecoController enderecoController = Controller.getEnderecoController();

        Endereco clientEndereco = enderecoController.criar(rua, numero, bairro, cidade);
        Cliente cliente = new Cliente(cpf, nome, telefone, clientEndereco);

        if (email != null) {
        	cliente.setEmail(email);
        }

        if (platinum) {
        	cliente.setPlatinum(true);
        }

        if (clientes.put(cpf, cliente) != null) {
            throw new UpdateException("Erro na criaçao do Cliente");
        }
        
        Controller.writeFile();
    }
    
    public void atualizar(Object id, String name, long telefone, String email,
            String rua, int numero, String bairro,
            Cidade cidade, boolean platinum) throws UpdateException {
        Cliente cliente = (Cliente) clientes.get(id);

        if (cliente == null) {
            throw new UpdateException("Cliente não existe");
        }

        cliente.setNome(name);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.getEndereco().setLogradouro(rua);
        cliente.getEndereco().setNumero(numero);
        cliente.getEndereco().setBairro(bairro);
        cliente.getEndereco().setCidade(cidade);
        cliente.setPlatinum(platinum);

        Controller.writeFile();
    }

    public Object[] pegar(Object id) {
        ICliente cliente = clientes.get((Long) id);

        Object[] row = {cliente.getCpf(), cliente.getNome(), cliente.getTelefone(),
                cliente.getEmail() != null ? cliente.getEmail() : "",
                cliente.getEndereco().getLogradouro(), cliente.getEndereco().getNumero(), cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCidade(), cliente.isPlatinum()};
        return row;
    }

    public DefaultComboBoxModel<ICliente> getDefaultComboBoxModel() {
        DefaultComboBoxModel<ICliente> box = new DefaultComboBoxModel<>();

        for (ICliente i : clientes.values()) {
            box.addElement(i);
        }

        return box;
    }

    public void deletar(Object id) throws UpdateException {
		ICliente cliente = clientes.get((long) id);
		
		if (cliente == null) {
		    throw new UpdateException("Cliente não existe");
		}
		
		EnderecoController enderecoController = Controller.getEnderecoController();
		enderecoController.deletar(cliente.getEndereco());
		
		clientes.remove((Long) id);
		        
		Controller.writeFile();
    }

    public DefaultTableModel getTableModel() {
    	Object[] header = {"CPF", "Nome", "Telefone", "Email", "Rua", "Número",
    			"Bairro", "Cidade", "Platinum"};

		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		for (var i : clientes.entrySet()) {
		    Object[] row = { i.getValue().getCpf(), i.getValue().getNome(), i.getValue().getTelefone(),
		            i.getValue().getEmail(), i.getValue().isPlatinum(),
		            i.getValue().getEndereco().getLogradouro(), i.getValue().getEndereco().getNumero(),
		            i.getValue().getEndereco().getBairro(),
		            i.getValue().getEndereco().getCidade().getNome() };
		
		    model.addRow(row);
		}
		
		return model;
    }
}
