package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Veiculo implements IVeiculo, Serializable
{
	private static final long serialVersionUID = 8284309653489255630L;
	
	private final Modelo modelo;
    private final String chassi;
    private final int ano;
    private String cor;
    private String placa;
    private ICliente proprietario;
    private ArrayList<OrdemServico> servicos;

    public Veiculo(ICliente proprietario, Modelo modelo, String chassi, int ano, String cor)
    {
        this(proprietario, modelo, chassi, ano, cor, null);
    }

    public Veiculo(ICliente proprietario, Modelo modelo, String chassi, int ano, String cor, String placa)
    {
        this.proprietario = proprietario;
        this.modelo = modelo;
        this.chassi = chassi;
        this.ano = ano;
        this.cor = cor;
        this.servicos = new ArrayList<OrdemServico>();
        this.placa = placa;
    }

    @Override
    public String getChassi()
    {
        return chassi;
    }

    @Override
    public int getAno()
    {
        return ano;
    }

    @Override
    public Modelo getModelo()
    {
        return modelo;
    }

    @Override
    public String getCor()
    {
        return cor;
    }

    @Override
    public String getPlaca()
    {
        return placa;
    }

    public void setCor(String cor)
    {
        this.cor = cor;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public ICliente getProprietario()
    {
        return proprietario;
    }

    public void setProprietario(ICliente proprietario)
    {
        this.proprietario = proprietario;
    }

    public StringBuilder listarServicos()
    {   
        StringBuilder sb = new StringBuilder();

        sb.append("Veiculo         :" + getPlaca() + "\n");
        for (OrdemServico servico: servicos)
        {
            sb.append(servico.listarOS());
        }

        return sb;
    }

    public void setServico(OrdemServico servico)
    {
        servicos.add(servico);
    }

    public StringBuilder listarVeiculo()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Modelo       :" + getModelo() + "\n");
        sb.append("Ano          :" + getAno() + "\n");
        sb.append("Cor          :" + getCor() + "\n");
        sb.append("Placa        :" + getPlaca() + "\n");
        sb.append("Chassi       :" + getChassi() + "\n");
        sb.append("Proprietário :" + getProprietario());

        return sb;
    }
}
