package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Cidade;
import Model.Endereco;
import Model.Funcionario;
import Model.IFuncionario;
import View.UpdateException;

public class FuncionarioController implements Serializable{
	private static final long serialVersionUID = 6320264082369117025L;

	private Map<Long, IFuncionario> funcionarios;
    
    public FuncionarioController() {
    	funcionarios = new TreeMap<>(); 
    }

    public void criar(long cpf, String nome, long telefone, String email,
            String rua, int numero, String bairro,
            Cidade cidade)  throws UpdateException {
    	if (funcionarios.get(cpf) != null) {
            throw new UpdateException("Cliente ja existe");
        }

        EnderecoController enderecoController = Controller.getEnderecoController();

        Endereco funcionarioEndereco = enderecoController.criar(rua, numero, bairro, cidade);
        Funcionario funcionario = new Funcionario(cpf, nome, telefone, funcionarioEndereco);

        if (email != null) {
        	funcionario.setEmail(email);
        }

        if (funcionarios.put(cpf, funcionario) != null) {
            throw new UpdateException("Erro na criaçao do Cliente");
        }
        
        Controller.writeFile();
    }
    
    public void atualizar(Object id, String name, long telefone, String email,
            String rua, int numero, String bairro, Cidade cidade) throws UpdateException {
        Funcionario funcionario = (Funcionario) funcionarios.get(id);

        if (funcionario== null) {
            throw new UpdateException("Cliente não existe");
        }

        funcionario.setNome(name);
        funcionario.setTelefone(telefone);
        funcionario.setEmail(email);
        funcionario.getEndereco().setLogradouro(rua);
        funcionario.getEndereco().setNumero(numero);
        funcionario.getEndereco().setBairro(bairro);
        funcionario.getEndereco().setCidade(cidade);

        Controller.writeFile();
    }

    public Object[] pegar(Object id) {
        IFuncionario funcionario = funcionarios.get((Long) id);

        Object[] row = {funcionario.getCpf(), funcionario.getNome(), funcionario.getTelefone(),
        				funcionario.getEmail() != null ? funcionario.getEmail() : "",
        				funcionario.getEndereco().getLogradouro(), funcionario.getEndereco().getNumero(), funcionario.getEndereco().getBairro(),
        				funcionario.getEndereco().getCidade()};
        return row;
    }

    public DefaultComboBoxModel<IFuncionario> getDefaultComboBoxModel() {
        DefaultComboBoxModel<IFuncionario> box = new DefaultComboBoxModel<>();

        for (IFuncionario i : funcionarios.values()) {
            box.addElement(i);
        }

        return box;
    }

    public void deletar(Object id) throws UpdateException {
		IFuncionario funcionario = funcionarios.get((long) id);
		
		if (funcionario == null) {
		    throw new UpdateException("Cliente não existe");
		}
		
		EnderecoController enderecoController = Controller.getEnderecoController();
		enderecoController.deletar(funcionario.getEndereco());
		
		funcionarios.remove((Long) id);
		        
		Controller.writeFile();
    }

    public DefaultTableModel getTableModel() {
    	Object[] header = {"CPF", "Nome", "Telefone", "Email", "Rua", "Número",
    			"Bairro", "Cidade"};

		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		for (var i : funcionarios.entrySet()) {
		    Object[] row = {i.getValue().getCpf(), i.getValue().getNome(), i.getValue().getTelefone(),
		            i.getValue().getEmail(),
		            i.getValue().getEndereco().getLogradouro(), i.getValue().getEndereco().getNumero(),
		            i.getValue().getEndereco().getBairro(),
		            i.getValue().getEndereco().getCidade()};
		
		    model.addRow(row);
		}
		
		return model;
    }
}
