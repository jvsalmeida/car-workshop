package Controller;

import java.io.Serializable;

import Persistence.Persistence;

public class Controller implements Serializable {
	private static final long serialVersionUID = -2434545947803260121L;
	
	private static Controller instance;
	
	private CidadeController cidadeController;
	private CategoriaController categoriaController;
	private ClienteController clienteController;
	private EnderecoController enderecoController;
	private FuncionarioController funcionarioController;
	private ItemController itemController;
	private ModeloController modeloController;
	private OrdemServicoController ordemServicoController;
	private VeiculoController veiculoController;
	
	private Controller()
	{
		cidadeController = new CidadeController();
		categoriaController = new CategoriaController();
		clienteController = new ClienteController();
		funcionarioController = new FuncionarioController();
		enderecoController = new EnderecoController();
		itemController = new ItemController();
		modeloController = new ModeloController();
		ordemServicoController = new OrdemServicoController();
		veiculoController = new VeiculoController();
	}
	
	public static CidadeController getCidadeController()
	{
		return instance.cidadeController;
	}
	
	public static CategoriaController getCategoriaController()
	{
		return instance.categoriaController;
	}
	
	public static ClienteController getClienteController()
	{
		return instance.clienteController;
	}
	
	public static EnderecoController getEnderecoController()
	{
		return instance.enderecoController;
	}
	
	public static FuncionarioController getFuncionarioController()
	{
		return instance.funcionarioController;
	}
	
	public static ItemController getItemController()
	{
		return instance.itemController;
	}
	
	public static ModeloController getModeloController()
	{
		return instance.modeloController;
	}
	
	public static OrdemServicoController getOrdemServicoController()
	{
		return instance.ordemServicoController;
	}
	
	public static VeiculoController getVeiculoController()
	{
		return instance.veiculoController;
	}
	
	public static Controller getInstance()
	{
		return instance;
	}
	
	public static void readFile() {
        instance = Persistence.readFile();

        if (instance == null)
        {
            instance = new Controller();
        }
    }

    public static void writeFile() {
        Persistence.writeFile(instance);
    }
}
