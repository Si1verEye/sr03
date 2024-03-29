package SR03.fr;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Couleurjante;
import model.Couleurvehicule;
import model.Finition;
import model.Jante;
import model.Modele;
import model.Motorisation;
import model.Optionvehicule;
import model.Vehicule;


/**
 * Session Bean implementation class Request
 */
@Stateless
public class Request implements RequestLocal {

	@PersistenceContext(name = "SR03_JPA")
	EntityManager em;
	
    public Request() {
        // TODO Auto-generated constructor stub
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Vehicule> getTousVehicules()
    {
    	Query q = em.createQuery("select v from Vehicule v");
    		
    	return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Motorisation> getTousMotorisation()
    {
    	Query q = em.createQuery("select m from Motorisation m");
    		
    	return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Modele> getTousModeles()
    {
    	Query q = em.createQuery("select m from Modele m");
    		
    	return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Couleurvehicule> getTousCouleurVehicule()
    {
    	Query q = em.createQuery("select c from Couleurvehicule c");
    		
    	return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public Vehicule getVehicule(int id)
    {
    	Query q = em.createQuery("select v from Vehicule v "
    			+ "where v.idVehicule=:id");
    	q.setParameter("id", id);
    	
    	return (Vehicule) q.getSingleResult();
    }
	
    
    @SuppressWarnings("unchecked")
	public List<Couleurjante> getCouleurJante(int id)
    {
    	Query q = em.createQuery("select c from Couleurjante c "
    			+ "where c.idCouleurJante=:id");
    	q.setParameter("id", id);
    	
    	return (List<Couleurjante>) q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Couleurvehicule> getCouleurVehicule(int id)
    {
    	Query q = em.createQuery("select c from Couleurvehicule c "
    			+ "where c.idCouleurVehicule=:id");
    	q.setParameter("id", id);
    	
    	return (List<Couleurvehicule>) q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Finition> getFinition(int id)
    {
    	Query q = em.createQuery("select f from Finition f "
    			+ "where f.idFinition=:id");
    	q.setParameter("id", id);
    	
    	return (List<Finition>) q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Jante> getJante(int id)
    {
    	Query q = em.createQuery("select j from Jante j "
    			+ "where j.idJante=:id");
    	q.setParameter("id", id);
    	
    	return (List<Jante>) q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Modele> getModele(int id)
    {
    	Query q = em.createQuery("select m from Modele m "
    			+ "where m.idModele=:id");
    	q.setParameter("id", id);
    	
    	return (List<Modele>) q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Motorisation> getMotorisation(int id)
    {
    	Query q = em.createQuery("select m from Motorisation m "
    			+ "where m.idMotorisation=:id");
    	q.setParameter("id", id);
    	
    	return (List<Motorisation>) q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public Optionvehicule getOptionvehicule(int id)
    {
    	Query q = em.createQuery("select o from Optionvehicule o "
    			+ "where o.idOption=:id");
    	q.setParameter("id", id);
    	
    	return (Optionvehicule) q.getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<Optionvehicule> getToutesOptionvehicule(int id)
    {
    	Query q = em.createQuery("select o from Optionvehicule o join fetch Vehicule.OptVehicule h "
    			+ "where o.idOption=:h.idOption"
    			+ "where h.idVehicule=:id");
    	q.setParameter("id", id);
    	
    	return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Vehicule> getTousVehiculeoption(int id)
    {
    	Query q = em.createQuery("select v from Vehicule v join fetch v.OptVehicule h "
    			+ "where h.idOption=:id");
    	q.setParameter("id", id);
    	
    	return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Vehicule> getTousVehiculeModColor(int idMod, int idColorV)
    {
    	Query q = em.createQuery("select v from Vehicule v "
    			+ "where v.idModele=:idMod AND v.idCouleurVehicule=:idColorV");
    	q.setParameter("idMod", idMod);
    	q.setParameter("idColorV",idColorV);
    	
    	return q.getResultList();
    }
  
}
