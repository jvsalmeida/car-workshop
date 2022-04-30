package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Categoria implements Serializable
{
	private static final long serialVersionUID = -1710786071201273194L;
	
	private final String nome;
    private ArrayList<IItem> itens;

    public Categoria(String nome)
    {
        this.nome = nome;
        this.itens = new ArrayList<IItem>();
    }

    public String getNome()
    {
        return nome;
    }

    public void addItem(IItem item)
    {
        itens.add(item);
    }

    public void removeItem(IItem item)
    {
        itens.remove(itens.indexOf(item));
    }

    public StringBuilder listarItem()
    {
        StringBuilder sb = new StringBuilder();
        for(IItem item : itens)
        {
            sb.append("\n- - - - - - - - - - - - - - - - - \n");
            sb.append(" Codigo:    " + item.getCodigo() + "\n");
            sb.append(" Descricao: " + item.getDescricao() + "\n");
            sb.append(" Preco:     " + item.getPreco() + "\n");
            sb.append(" Tipo:      " + item.getTipo());
        }

        return sb;
    }
}
