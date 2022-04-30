package Model;

import java.io.Serializable;

public class Item implements IItem, Serializable
{
	private static final long serialVersionUID = -3124045934363757834L;
	
	private int contadorCodigo = 1;
    private final ETipoItem tipo;
    private final long codigo;
    private final String descricao;
    private double preco;

    public Item(ETipoItem tipo, String descricao)
    {
        this.tipo = tipo;
        this.descricao = descricao;
        this.codigo = contadorCodigo++;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    @Override
    public long getCodigo()
    {
        return codigo;
    }

    @Override
    public String getDescricao()
    {
        return descricao;
    }

    @Override
    public double getPreco()
    {
        return preco;
    }

    @Override
    public ETipoItem getTipo()
    {
        return tipo;
    }
}
