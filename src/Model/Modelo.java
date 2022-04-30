package Model;

import java.io.Serializable;

public class Modelo implements Serializable
{
	private static final long serialVersionUID = 4540217893142348270L;
	
	private final String nome;

    public Modelo(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }
}
