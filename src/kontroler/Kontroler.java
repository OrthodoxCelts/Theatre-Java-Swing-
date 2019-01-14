package kontroler;

import java.util.ArrayList;

import javax.swing.plaf.SliderUI;

import broker.DBKomunikacija;
import domen.Gledalac;
import domen.Pozoriste;
import domen.Predstave;
import domen.Rezervacije;
import domen.Ulaznice;

public class Kontroler {

	private static Kontroler instanca;
	private ArrayList<Predstave> alRepertoar = new ArrayList<>();
	private ArrayList<Predstave> alRepertoar2 = new ArrayList<>();
	private ArrayList<Ulaznice> alUlaznice = new ArrayList<>();
	private ArrayList<Gledalac> alGledaoci = new ArrayList<>();
	private ArrayList<Gledalac> alGledaoci2 = new ArrayList<>();
	private ArrayList<Pozoriste> alImePoz = new ArrayList<>();
	private ArrayList<Predstave> alPred = new ArrayList<>();
	private ArrayList<Predstave> alNazivPred = new ArrayList<>();
	private ArrayList<Rezervacije> alRezervacije = new ArrayList<>();
	private ArrayList<Rezervacije> alRezervacijeGledalac = new ArrayList<>();
	private ArrayList<Rezervacije> alRezervacijeGledalac2 = new ArrayList<>();
	private ArrayList<Rezervacije> alRezervacije2 = new ArrayList<>();
	private Gledalac gledalac;

	public static Kontroler getInstanca() {

		if (instanca == null) {
			instanca = new Kontroler();
		}
		return instanca;
	}

	public boolean vratiAdminUserDB(String user) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		boolean userAdmin = DBKomunikacija.getBroker().proveriAdminUserDB(user);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return userAdmin;
	}

	public boolean vratiAdminPassDB(String pass) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		boolean passAdmin = DBKomunikacija.getBroker().proveriAdminPassDB(pass);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return passAdmin;
	}

	public boolean vratiUserDB(String user) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		boolean userGl = DBKomunikacija.getBroker().proveriUserDB(user);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return userGl;
	}

	public boolean vratiPassDB(String pass) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		boolean passGl = DBKomunikacija.getBroker().proveriPassDB(pass);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return passGl;
	}

	public ArrayList<Predstave> vratiRepertoar() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alRepertoar = DBKomunikacija.getBroker().vratiRepertoar();
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alRepertoar;
	}

	public void izvrsiRezervaciju(int ID_Pred, int ID_Gled, int brRezUl) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izvrsiRezervaciju(ID_Pred, ID_Gled, brRezUl);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public Gledalac ucitajGledaoca(String user) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		gledalac = DBKomunikacija.getBroker().ucitajGledaoca(user);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return gledalac;
	}

	public void promeniBrRaspUl(int ID_Pred, int brojRaspolozivihUlaznica) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().azurirajUlaznice(ID_Pred, brojRaspolozivihUlaznica);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void upisiPozoriste(String naziv) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().upisiPozoriste(naziv);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public ArrayList<Pozoriste> vratiPozorista() {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		alImePoz = DBKomunikacija.getBroker().vratiPozorista();
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alImePoz;
	}

	public void upisiPredstavu(int iD_Poz, String nazivPred, String scena, String vremeIzv, String datumIzv) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().upisiPredstavu(iD_Poz, nazivPred, scena, vremeIzv, datumIzv);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public void zavrsiRegistraciju(String imePrezime, String user, String pass) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().zavrsiRegistraciju(imePrezime, user, pass);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public void upisiPodUlaznica(int iD_Pred, int brojUl, int cenaUl) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().upisiPodUlaznica(iD_Pred, brojUl, cenaUl);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public ArrayList<Predstave> vratiPredstave() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alNazivPred = DBKomunikacija.getBroker().vratiPredstave();
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alNazivPred;
	}

	public Gledalac ucitajGledaoca(int iD_Gled) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		gledalac = DBKomunikacija.getBroker().ucitajGledaoca(iD_Gled);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return gledalac;

	}

	public ArrayList<Rezervacije> vratiRezervacijeGledalac(int ID_Gledalac) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alRezervacijeGledalac = DBKomunikacija.getBroker().vratiRezervacijeGledalac(ID_Gledalac);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alRezervacijeGledalac;

	}

	public ArrayList<Rezervacije> vratiRezervacije() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alRezervacije = DBKomunikacija.getBroker().vratiRezervacije();
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alRezervacije;

	}

	public void obrisiRezervaciju(int iD_Rezervacije) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().obrisiRezervaciju(iD_Rezervacije);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public void izmeniRezervaciju(int iD_Rezervacije, int noviBroj) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izmeniRezervaciju(iD_Rezervacije, noviBroj);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public void azurirajBrojUlaznica(int iD_Pred, int noviBrRasUl) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().azurirajBrojUlaznica(iD_Pred, noviBrRasUl);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public ArrayList<Predstave> vratiRepertoar(String nazivPozoriste) {
		// TODO Auto-generated method stub

		DBKomunikacija.getBroker().otvoriKonekciju();
		alRepertoar2 = DBKomunikacija.getBroker().vratiRepertoar(nazivPozoriste);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alRepertoar2;
	}

	public ArrayList<Rezervacije> vratiRezervacijeGledalac(int iD_Gledalac, String nazivPozoriste) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alRezervacijeGledalac2 = DBKomunikacija.getBroker().vratiRezervacijeGledalac(iD_Gledalac, nazivPozoriste);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alRezervacijeGledalac2;

	}

	public ArrayList<Rezervacije> vratiRezervacije(String nazivPozoriste) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alRezervacije2 = DBKomunikacija.getBroker().vratiRezervacije(nazivPozoriste);
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alRezervacije2;
	}

	public ArrayList<Gledalac> vratiGledaoce() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alGledaoci2 = DBKomunikacija.getBroker().vratiGledaoce();
		DBKomunikacija.getBroker().zatvoriKonekciju();

		return alGledaoci2;
	}

	public void obrisiPozoriste(int iD_Pozorista) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().obrisiPozoriste(iD_Pozorista);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void obrisiPredstavu(int iD_Predstave) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().obrisiPredstavu(iD_Predstave);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void obrisiGledaoca(int iD_Gledaoca) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().obrisiGledaoca(iD_Gledaoca);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

}
