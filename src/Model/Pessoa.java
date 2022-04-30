package Model;

import java.io.Serializable;
import java.util.regex.*;

public class Pessoa implements IPessoa, Serializable
{
	private static final long serialVersionUID = -2822341513225536810L;
	
	private final long cpf;
    private String nome;
    private long telefone;
    private String email;
    private Endereco endereco;

    public Pessoa(long cpf, String nome, long telefone, Endereco endereco)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    @Override
    public long getCpf()
    {
        return cpf;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public Endereco getEndereco()
    {
        return endereco;
    }

    @Override
    public String getNome()
    {
        return nome;
    }

    @Override
    public long getTelefone()
    {
        return telefone;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setTelefone(long telefone)
    {
        this.telefone = telefone;
    }

    public void setEmail(String email)
    {
        try
        {
            validarEmail(email);
            this.email = email;
        }
        catch(EmailException e)
        {
            System.out.println(e);
        }
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public StringBuilder listarPessoa()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("CPF      :" + getCpf() + "\n");
        sb.append("Nome     :" + getNome() + "\n");
        sb.append("Telefone :" + getTelefone() + "\n");
        sb.append("Email    :" + getEmail() + "\n");
        sb.append("Endereço :" + getEndereco() + "\n");
        
        return sb;
    }

    private void validarEmail(String email) throws EmailException
    {
        String regex = "[a-zA-Z][\\w-]{1,20}@\\w{2,20}\\.\\w{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(email);
        
        if(match.matches() == false)
        {
            throw new EmailException("Email Inválido!");
        }
    }
}
