package domen;

import java.util.Comparator;

public class Rezervacije {
	
	private int ID_Rez, brRezUl, ID_Gled, ID_Pred, id, brojRaspolozivihUlaznica, cenaUlaznica, ID_Gledaoca;
	private String imePozorista, nazivPredstave, scenaIzvodjenja, vremeIzvodjenja, datumIzvodjenja, imePrezime;
	
	
	public int getID_Rez() {
		return ID_Rez;
	}
	public void setID_Rez(int iD_Rez) {
		ID_Rez = iD_Rez;
	}
	public int getBrRezUl() {
		return brRezUl;
	}
	public void setBrRezUl(int brRezUl) {
		this.brRezUl = brRezUl;
	}
	public int getID_Gled() {
		return ID_Gled;
	}
	public void setID_Gled(int iD_Gled) {
		ID_Gled = iD_Gled;
	}
	public int getID_Pred() {
		return ID_Pred;
	}
	public void setID_Pred(int iD_Pred) {
		ID_Pred = iD_Pred;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBrojRaspolozivihUlaznica() {
		return brojRaspolozivihUlaznica;
	}
	public void setBrojRaspolozivihUlaznica(int brojRaspolozivihUlaznica) {
		this.brojRaspolozivihUlaznica = brojRaspolozivihUlaznica;
	}
	public int getCenaUlaznica() {
		return cenaUlaznica;
	}
	public void setCenaUlaznica(int cenaUlaznica) {
		this.cenaUlaznica = cenaUlaznica;
	}
	public int getID_Gledaoca() {
		return ID_Gledaoca;
	}
	public void setID_Gledaoca(int iD_Gledaoca) {
		ID_Gledaoca = iD_Gledaoca;
	}
	public String getImePozorista() {
		return imePozorista;
	}
	public void setImePozorista(String imePozorista) {
		this.imePozorista = imePozorista;
	}
	public String getNazivPredstave() {
		return nazivPredstave;
	}
	public void setNazivPredstave(String nazivPredstave) {
		this.nazivPredstave = nazivPredstave;
	}
	public String getScenaIzvodjenja() {
		return scenaIzvodjenja;
	}
	public void setScenaIzvodjenja(String scenaIzvodjenja) {
		this.scenaIzvodjenja = scenaIzvodjenja;
	}
	public String getVremeIzvodjenja() {
		return vremeIzvodjenja;
	}
	public void setVremeIzvodjenja(String vremeIzvodjenja) {
		this.vremeIzvodjenja = vremeIzvodjenja;
	}
	public String getDatumIzvodjenja() {
		return datumIzvodjenja;
	}
	public void setDatumIzvodjenja(String datumIzvodjenja) {
		this.datumIzvodjenja = datumIzvodjenja;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	
	public static final Comparator<Rezervacije> StuNameComparator2 = new Comparator<Rezervacije>() {

		
		public int compare(Rezervacije r1, Rezervacije r2) {
			// TODO Auto-generated method stub
			int prvi = r1.getBrRezUl();
			int drugi = r2.getBrRezUl();
					
			if(prvi>drugi){
						   
				 return 1;
			}else{
						   
				 return -1;
		}
		}
		
	};

	

}
