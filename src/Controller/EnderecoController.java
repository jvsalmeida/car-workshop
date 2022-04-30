package Controller;

import java.io.Serializable;
import java.util.ArrayList;

import Model.Cidade;
import Model.Endereco;

public class EnderecoController implements Serializable{
	private static final long serialVersionUID = 326321221750438171L;
	
	public static ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

    public Endereco criar(String rua, int numero, String bairro, Cidade cidade) {
        Endereco end = new Endereco(rua, numero, bairro, cidade);

        enderecos.add(end);

        return end;
    }

    public void deletar(Endereco endereco) {
        enderecos.remove(endereco);
    }
}