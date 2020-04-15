package IServices;

import Entities.Parents;
import javafx.collections.ObservableList;

import java.util.List;

public interface IserviceParent extends CrudGeneric<Parents> {

    public int delete(int id) ;
    public int nbrenfant(int id);

    public ObservableList<Parents> search(String search);

}
