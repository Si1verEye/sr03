package sr03.utc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.internal.engine.valuehandling.OptionalValueUnwrapper;

@ManagedBean
@ViewScoped
public class voitureGest {
	
	private Client client;
	
	private List<modeleTemplate> modeleTemplate;
	private List<couleurVehiculeTemplate> couleurVehiculeTemplate;
	
	private int selectedModele;
	private int selectedMotor;
	private int selectedColor;
	
	private List<vehiculeTemplate> vehiculeTemplate;
	private List<motorisationTemplate> motorisationTemplate;
	private List<couleurJanteTemplate> couleurJanteTemplate;
	private List<couleurVehiculeTemplate> couleurVehiculeTemplate2;
	private List<modeleTemplate> modeleTemplate2;
	private List<finitionTemplate> finitionTemplate;
	private List<janteTemplate> janteTemplate;
	private List<prixTemplate> prixTemplate;
	//private List<optionvehiculeTemplate> optionVehiculeTemplate;
	
	
	public List<couleurJanteTemplate> getCouleurJanteTemplate() {
		return couleurJanteTemplate;
	}

	public void setCouleurJanteTemplate(List<couleurJanteTemplate> couleurJanteTemplate) {
		this.couleurJanteTemplate = couleurJanteTemplate;
	}

	public List<couleurVehiculeTemplate> getCouleurVehiculeTemplate2() {
		return couleurVehiculeTemplate2;
	}

	public void setCouleurVehiculeTemplate2(List<couleurVehiculeTemplate> couleurVehiculeTemplate2) {
		this.couleurVehiculeTemplate2 = couleurVehiculeTemplate2;
	}

	public List<modeleTemplate> getModeleTemplate2() {
		return modeleTemplate2;
	}

	public void setModeleTemplate2(List<modeleTemplate> modeleTemplate2) {
		this.modeleTemplate2 = modeleTemplate2;
	}

	public List<finitionTemplate> getFinitionTemplate() {
		return finitionTemplate;
	}

	public void setFinitionTemplate(List<finitionTemplate> finitionTemplate) {
		this.finitionTemplate = finitionTemplate;
	}

	public List<janteTemplate> getJanteTemplate() {
		return janteTemplate;
	}

	public void setJanteTemplate(List<janteTemplate> janteTemplate) {
		this.janteTemplate = janteTemplate;
	}

	public void setVehiculeTemplate(List<vehiculeTemplate> vehiculeTemplate) {
		this.vehiculeTemplate = vehiculeTemplate;
	}

	public void setMotorisationTemplate(List<motorisationTemplate> motorisationTemplate) {
		this.motorisationTemplate = motorisationTemplate;
	}

	@PostConstruct
	public void init()
	{		

		this.client = ClientBuilder.newClient();
		
		this.modeleTemplate = client.target("http://localhost:8080/SR03_REST/voiture/allMod")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<modeleTemplate>>() {});
		
		this.couleurVehiculeTemplate = client.target("http://localhost:8080/SR03_REST/voiture/allCV")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<couleurVehiculeTemplate>>() {});
			
	}
	
	public void resultatFormulaire() {
		this.client = ClientBuilder.newClient();
		
		this.vehiculeTemplate = new ArrayList<vehiculeTemplate>();
		this.motorisationTemplate = new ArrayList<motorisationTemplate>();
		this.couleurJanteTemplate = new ArrayList<couleurJanteTemplate>();
		this.couleurVehiculeTemplate2 = new ArrayList<couleurVehiculeTemplate>();
		this.finitionTemplate = new ArrayList<finitionTemplate>();
		this.janteTemplate = new ArrayList<janteTemplate>();
		this.modeleTemplate2 = new ArrayList<modeleTemplate>();
		this.prixTemplate = new ArrayList<prixTemplate>();
		
		this.vehiculeTemplate = client.target("http://localhost:8080/SR03_REST/voiture/tout")
				.queryParam("idMod",this.selectedModele)
				.queryParam("idColorV",this.selectedColor)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<vehiculeTemplate>>() {});
		
		
		List<motorisationTemplate> res;
		List<couleurJanteTemplate> res2;
		List<couleurVehiculeTemplate> res3;
		List<finitionTemplate> res4;
		List<janteTemplate> res5;
		List<modeleTemplate> res6;
		
		prixTemplate p = new prixTemplate();
		
		for(int i = 0; i < this.vehiculeTemplate.size();i++) {
			res = client.target("http://localhost:8080/SR03_REST/voiture/motorisationByID")
					.queryParam("id",this.vehiculeTemplate.get(i).getIdMotorisation())
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<motorisationTemplate>>() {});
			this.motorisationTemplate.add(res.get(0));
						
			res3 = client.target("http://localhost:8080/SR03_REST/voiture/couleurVehiculeByID")
					.queryParam("id",this.vehiculeTemplate.get(i).getIdCouleurVehicule())
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<couleurVehiculeTemplate>>() {});
			this.couleurVehiculeTemplate2.add(res3.get(0));
			
			res4 = client.target("http://localhost:8080/SR03_REST/voiture/finitionByID")
					.queryParam("id",this.vehiculeTemplate.get(i).getIdFinition())
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<finitionTemplate>>() {});
			this.finitionTemplate.add(res4.get(0));
			
			res5 = client.target("http://localhost:8080/SR03_REST/voiture/janteByID")
					.queryParam("id",this.vehiculeTemplate.get(i).getIdJante())
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<janteTemplate>>() {});
			this.janteTemplate.add(res5.get(0));
			
			res6 = client.target("http://localhost:8080/SR03_REST/voiture/modeleByID")
					.queryParam("id",this.vehiculeTemplate.get(i).getIdModele())
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<modeleTemplate>>() {});
			this.modeleTemplate2.add(res6.get(0));
			
			res2 = client.target("http://localhost:8080/SR03_REST/voiture/couleurJanteByID")
			.queryParam("id",res5.get(0).getIdCouleurJante())
			.request(MediaType.APPLICATION_JSON)
			.get(new GenericType<List<couleurJanteTemplate>>() {});
			this.couleurJanteTemplate.add(res2.get(0));
			
			p.setPrix(res6.get(0).getPrixInitial() +
					(res6.get(0).getPrixInitial() * res6.get(0).getCoefficient() - res6.get(0).getPrixInitial()) +
					(res6.get(0).getPrixInitial() * res.get(0).getCoefficient() - res6.get(0).getPrixInitial()) +
					(res6.get(0).getPrixInitial() * res2.get(0).getCoefficient() - res6.get(0).getPrixInitial()) +
					(res6.get(0).getPrixInitial() * res3.get(0).getCoefficient() - res6.get(0).getPrixInitial()) +
					(res6.get(0).getPrixInitial() * res4.get(0).getCoefficient() - res6.get(0).getPrixInitial()) +
					(res6.get(0).getPrixInitial() * res5.get(0).getCoefficient() - res6.get(0).getPrixInitial())
					);
			if(p.getPrix() < 0) {
				p.setPrix(res6.get(0).getPrixInitial());
			}
			
			this.prixTemplate.add(p);
			
		}
	}
	
	public List<motorisationTemplate> getMotorisationTemplate(){
		return this.motorisationTemplate;
	}
	
	public void setMotorTemplate(List<motorisationTemplate> motorisationTemplate) {
		this.motorisationTemplate = motorisationTemplate;
	}

	public List<vehiculeTemplate> getVehiculeTemplate() {
		return vehiculeTemplate;
	}

	public void setVoitureTemplate(List<vehiculeTemplate> vehiculeTemplate) {
		this.vehiculeTemplate = vehiculeTemplate;
	}

	public List<modeleTemplate> getModeleTemplate() {
		return modeleTemplate;
	}

	public void setModeleTemplate(List<modeleTemplate> modeleTemplate) {
		this.modeleTemplate = modeleTemplate;
	}

	public int getSelectedModele() {
		return selectedModele;
	}

	public void setSelectedModele(int selectedModele) {
		this.selectedModele = selectedModele;
	}

	public int getSelectedMotor() {
		return selectedMotor;
	}

	public void setSelectedMotor(int selectedMotor) {
		this.selectedMotor = selectedMotor;
	}

	public List<couleurVehiculeTemplate> getCouleurVehiculeTemplate() {
		return couleurVehiculeTemplate;
	}

	public void setCouleurVehiculeTemplate(List<couleurVehiculeTemplate> couleurVehiculeTemplate) {
		this.couleurVehiculeTemplate = couleurVehiculeTemplate;
	}

	public int getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(int selectedColor) {
		this.selectedColor = selectedColor;
	}

	public List<prixTemplate> getPrixTemplate() {
		return prixTemplate;
	}

	public void setPrixTemplate(List<prixTemplate> prixTemplate) {
		this.prixTemplate = prixTemplate;
	}

	

}
