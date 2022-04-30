package Model;

import java.io.Serializable;

public class ItemOS implements Serializable
{
	private static final long serialVersionUID = 8011223811982270841L;
	
	private int qtde;
    private double preco;
    private final IItem item;

    public ItemOS(IItem item, int qtde, double preco)
    {
        this.item = item;
        this.preco = preco;
        this.qtde = qtde;
    }
    
    public IItem getItem()
    {
        return item;
    }

    public int getQtde()
    {
        return qtde;
    }

    public void setQtde(int qtde)
    {
        this.qtde = qtde;
    }

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public double getTotalItem()
    {
        return getQtde() * getPreco();
    }

    public String listarItem()
    {
        return null;
    }
}
