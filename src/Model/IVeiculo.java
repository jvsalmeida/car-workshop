package Model;

public interface IVeiculo
{
    public Modelo getModelo();

    public String getChassi();

    public int getAno();

    public String getCor();

    public String getPlaca();

    public ICliente getProprietario();

    public StringBuilder listarVeiculo();
}
