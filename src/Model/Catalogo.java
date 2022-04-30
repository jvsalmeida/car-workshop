package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Catalogo implements Serializable
{
	private static final long serialVersionUID = 7540384470999651490L;
	
	private ArrayList<Categoria> categorias;

    public Catalogo()
    {
        this.categorias = new ArrayList<Categoria>();
    }

    public StringBuilder getCategorias()
    {
        StringBuilder sb = new StringBuilder();

        for(Categoria cat : categorias)
        {
            sb.append("\n- - - - - - - - - - - - - - - \n");
            sb.append("Categoria: " + cat.getNome() + "\n");
            //sb.append("Itens:     " + cat.getItem() + "\n");
        }

        return sb;
    }

    public void setCategorias(Categoria categoria)
    {
        categorias.add(categoria);
    }
}