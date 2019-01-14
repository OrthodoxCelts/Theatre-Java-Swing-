package domen;

import java.util.Comparator;

public class Predstave {
	
	
	private int id, brojRaspolozivihUlaznica, cenaUlaznica;
	private String nazivPredstave, scenaIzvodjenja, vremeIzvodjenja, imePozorista, datumIzvodjenja;
	
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
	public String getImePozorista() {
		return imePozorista;
	}
	public void setImePozorista(String imePozorista) {
		this.imePozorista = imePozorista;
	}
	public String getDatumIzvodjenja() {
		return datumIzvodjenja;
	}
	public void setDatumIzvodjenja(String datumIzvodjenja) {
		this.datumIzvodjenja = datumIzvodjenja;
	}
	
	public static Comparator<Predstave> StuNameComparator = new Comparator<Predstave>() {

		public int compare(Predstave o1, Predstave o2) {
			int prvi = o1.getCenaUlaznica();
			int drugi = o2.getCenaUlaznica();
					
			if(prvi>drugi){
						   
				 return 1;
			}else{
						   
				 return -1;
		}
		
		}};
		
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
