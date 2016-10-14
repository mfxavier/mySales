package br.com.andarella.dao;

import br.com.domain.Filiais;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FiliaisDAO {
    public List<Filiais> getListarFiliais();
}
