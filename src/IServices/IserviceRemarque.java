package IServices;

import Entities.Remarque;

import java.util.List;

public interface IserviceRemarque extends CrudGeneric<Remarque> {

    public List<Remarque> Mesremarques(int id);
}
