package Model;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;

public class OrdemServico implements Serializable
{
	private static final long serialVersionUID = -1876906908126902949L;
	
	private static int        contadorNumero = 1;
    private final  int        numero;
    private final  ICliente   cliente;
    private final  IVeiculo   veiculo;
    private final  Date       data;
    private int               kmAtual;
    private String            descricao;
    private IFuncionario      consultor;
    private ArrayList<ItemOS> itens;

    public OrdemServico(IVeiculo veiculo, int kmAtual)
    {
        this.numero = contadorNumero++;
        this.veiculo = veiculo;
        this.kmAtual = kmAtual;
        this.data = new Date();
        this.cliente = veiculo.getProprietario();
        this.itens = new ArrayList<ItemOS>();
    }


    public int getNumero()
    {
        return numero;
    }
    
    public Date getData()
    {
        return data;
    }


    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public int getKmAtual()
    {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual)
    {
        this.kmAtual = kmAtual;
    }

    public IFuncionario getConsultor()
    {
        return consultor;
    }

    public void setConsultor(IFuncionario consultor)
    {
        this.consultor = consultor;
    }

    public IVeiculo getVeiculo()
    {
        return veiculo;
    }

    public ICliente getCliente()
    {
        return cliente;
    }

    public void addItem(IItem item, int qtde)
    {   
        itens.add(new ItemOS(item, qtde, item.getPreco()));
    }

    public void addItem(IItem item, int qtde, double preco)
    {   
        itens.add(new ItemOS(item, qtde, preco));
    }

    public void removeItem(ItemOS item)
    {   
        itens.remove(itens.indexOf(item));
    }

    public double getTotalServicos()
    {
        double total = 0;
        for(ItemOS i : itens)
        {
            if(i.getItem().getTipo() == ETipoItem.SERVICO)
            {
                total += i.getTotalItem();
            }
        }

        return total;
    }

    public double getTotalPecas()
    {
        double total = 0;
        for(ItemOS i : itens)
        {
            if(i.getItem().getTipo() == ETipoItem.PECA)
            {
                total += i.getTotalItem();
            }
        }

        return total;
    }

    public double getTotalOS()
    {
        double total = 0;
        for(ItemOS i : itens)
        {
            total += i.getTotalItem();
        }

        return total;
    }

    public StringBuilder listarOS()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Ordem de Servico: " + getNumero() + "\n");
        sb.append("Data            : " + getData() + "\n");
        sb.append("kmAtual         : " + getKmAtual() + "\n");
        sb.append("Descricao       : " + getDescricao() + "\n");

        sb.append("Consultor       : " + "\n" + getConsultor().listarFuncionario() + "\n");
        sb.append("Cliente         : " + "\n" + getCliente().listarCliente() + "\n");
        sb.append("Veiculo         : " + "\n" + getVeiculo().listarVeiculo() + "\n");
        sb.append("Item            : " + "\n"+ getItem() + "\n");

        return sb;
    }


    public StringBuilder getItem()
    {
        StringBuilder sb = new StringBuilder();
        double totalPecas = 0;
        double maoDeObra = 30;
        double desconto = 0;

        if (cliente.isPlatinum())
        {
            desconto = maoDeObra;
        }

        for (ItemOS item: itens)
        {
            double total = item.getPreco() * item.getQtde();

            sb.append("\n" + "----------------------------------------" + "\n");
            sb.append("Codigo:      " + item.getItem().getCodigo() + "\n");
            sb.append("Descricao:   " + item.getItem().getDescricao() + "\n");
            sb.append("Quantidade:  " + item.getQtde() + "\n");
            sb.append("Preco total: " + total + "\n");

            if (cliente.isPlatinum() && (item.getItem().getTipo() == ETipoItem.SERVICO))
                desconto += total;
            
            totalPecas += total;
        }

        sb.append("\n" + "- - - - - - - - - - - - - - - - - - - - " + "\n");
        sb.append("Valor dos Servicos: " + maoDeObra + "\n");
        sb.append("Desconto platinum:  " + desconto + "\n");
        sb.append("Valor das Pecas:    " + totalPecas + "\n");
        sb.append("Valor Total:        " + (totalPecas + maoDeObra - desconto) + "\n");

        return sb;
    }
}
