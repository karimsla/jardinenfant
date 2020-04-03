package IServices;

import java.util.List;
import java.util.Optional;

import Entities.Reclamation;

public interface IserviceReclamation {
	
	
	
	public Optional<Reclamation>  findbyId(int id);
	
	public List<Reclamation> findAll();

	public List<Reclamation> findnew();
	
	public void regelrReclamation(Reclamation reclam);
	
}
