package Model;

public class Cliente extends Pessoa implements ICliente
{
	private static final long serialVersionUID = 8448359964418223072L;
	
	private boolean platinum;

    public Cliente(long cpf, String nome, long telefone, Endereco endereco)
    {
        super(cpf, nome, telefone, endereco);
        this.platinum = false;
    }

    @Override
    public boolean isPlatinum()
    {
        return platinum;
    }

    public void setPlatinum(boolean platinum)
    {
        this.platinum = platinum;
    }

    public StringBuilder listarCliente()
    {
        StringBuilder sb = new StringBuilder();
    
        sb.append(listarPessoa());
        sb.append("Platinum: " + isPlatinum());

        return sb;
    }
}
