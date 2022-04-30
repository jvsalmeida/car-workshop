package Model;

public class Funcionario extends Pessoa implements IFuncionario
{
	private static final long serialVersionUID = 4628614675324271373L;
	
	private final int matricula;
    private static int contadorMatricula = 1;

    public Funcionario(long cpf, String nome, long telefone, Endereco endereco)
    {
        super(cpf, nome, telefone, endereco);
        this.matricula = contadorMatricula++;
    }

    public StringBuilder listarFuncionario()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(listarPessoa());
        sb.append("Matrícula:     " + getMatricula());
        
        return sb;
    }

    @Override
    public int getMatricula()
    {
        return matricula;
    }
}
