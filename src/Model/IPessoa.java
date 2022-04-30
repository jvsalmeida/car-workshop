package Model;

public interface IPessoa
{
    public long getCpf();

    public String getNome();

    public String getEmail();

    public long getTelefone();

    public Endereco getEndereco();

    public StringBuilder listarPessoa();
}
