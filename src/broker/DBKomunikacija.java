package broker;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import domen.Gledalac;
import domen.Pozoriste;
import domen.Predstave;
import domen.Rezervacije;
import domen.Ulaznice;

public class DBKomunikacija {
	
	public static DBKomunikacija broker;
	private Connection con;
	
	public static DBKomunikacija getBroker() {
		
		if(broker==null) {
			broker=new DBKomunikacija();
		}
		return broker;
	}
	
	private DBKomunikacija() {
		
		ucitajDriver();
	}

	private void ucitajDriver() {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void otvoriKonekciju() {
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/theatre", "root", "");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void zatvoriKonekciju() {
		
		try {
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean proveriAdminUserDB(String user) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		boolean pomocna=false;
		String userAdminDB=null;
		
		String sql="SELECT * FROM admin WHERE userAdmin='"+user+ "'";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()==false) {
				userAdminDB="";
			}else {
				userAdminDB=rs.getString("UserAdmin");
			}
			
			if(userAdminDB.isEmpty() || userAdminDB==null) {
				
				pomocna=false;
			}else {
				
				pomocna=true;
			}
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(userAdminDB==null) {
				pomocna=false;
			}
		}
		
		
		return pomocna;
	}

	public boolean proveriAdminPassDB(String pass) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		boolean pomocna=false;
		String passAdminDB=null;
		
		String sql="SELECT * FROM admin WHERE PassAdmin='"+pass+"'";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()==false) {
				passAdminDB="";
			}else {
				passAdminDB=rs.getString("PassAdmin");
			}
			
			if(passAdminDB.isEmpty() || passAdminDB==null) {
				
				pomocna=false;
			}else {
				
				pomocna=true;
			}
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(passAdminDB==null) {
				pomocna=false;
			}
		}
		
		
		return pomocna;
	}
	
	public boolean proveriUserDB(String user) {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		Statement st=null;
		boolean pomocna=false;
		String userDB=null;
		
		String sql="SELECT * FROM gledalac WHERE User='"+user+ "'";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()==false) {
				userDB="";
			}else {
				userDB=rs.getString("User");
			}
			
			if(userDB.isEmpty() || userDB==null) {
				
				pomocna=false;
			}else {
				
				pomocna=true;
			}
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(userDB==null) {
				pomocna=false;
			}
		}
		
		
		return pomocna;
	}
	
	public boolean proveriPassDB(String pass) {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		Statement st=null;
		boolean pomocna=false;
		String passDB=null;
		
		String sql="SELECT * FROM gledalac WHERE Pass='"+pass+"'";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()==false) {
				passDB="";
			}else {
				passDB=rs.getString("Pass");
			}
			
			if(passDB.isEmpty() || passDB==null) {
				
				pomocna=false;
			}else {
				
				pomocna=true;
			}
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(passDB==null) {
				pomocna=false;
			}
		}
		
		
		return pomocna;
	}


	public ArrayList<Predstave> vratiRepertoar() {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Predstave>alRepertoar=new ArrayList<>();
		String sql="SELECT * FROM predstava " 
					+ "INNER JOIN pozoriste ON predstava.ID_Pozorista=pozoriste.ID_Pozorista " 
					+ "INNER JOIN ulaznice ON predstava.ID_Predstava=ulaznice.ID_Predstava " 
					+ "WHERE predstava.DatumIzvodjenja>=CURRENT_DATE ";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Predstave pr=new Predstave();
				pr.setImePozorista(rs.getString("ImePozorista"));
				pr.setId(rs.getInt("ID_Predstava"));
				pr.setNazivPredstave(rs.getString("NazivPredstave"));
				pr.setScenaIzvodjenja(rs.getString("ScenaIzvodjenja"));
				pr.setVremeIzvodjenja(rs.getString("VremeIzvodjenja"));
				pr.setBrojRaspolozivihUlaznica(rs.getInt("BrojRaspolozivihUlaznica"));
				pr.setDatumIzvodjenja(rs.getString("DatumIzvodjenja"));
				pr.setCenaUlaznica(rs.getInt("CenaUlaznica"));
				
				alRepertoar.add(pr);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return alRepertoar;
	}
	


	public ArrayList<Ulaznice> vratiBrojUlaznica() {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Ulaznice>al=new ArrayList<>();
		String sql="SELECT * FROM ulaznice";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Ulaznice ul=new Ulaznice();
				ul.setBrojRaspolozivihUlaznica(rs.getInt("BrojRaspolozivihUlaznica"));
				
				al.add(ul);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return al;
	}


	
	public void izvrsiRezervaciju(int ID_Pred, int ID_Gledalac, int brRezUl) {
		// TODO Auto-generated method stub
		
		String sql="INSERT INTO rezervacije(ID_Predstava, ID_Gledalac, BrojRezervisanihUlaznica)" + " VALUES(?,?,?) ";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, ID_Pred);
			ps.setInt(2, ID_Gledalac);
			ps.setInt(3, brRezUl);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Gledalac> vratiGledaoce() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Gledalac>alGledaoci=new ArrayList<>();
		String sql="SELECT * FROM gledalac ";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Gledalac gl=new Gledalac();
				gl.setId_Gledalac(rs.getInt("ID_Gledalac"));
				gl.setImePrezime(rs.getString("ImePrezime"));
				gl.setUser(rs.getString("User"));
				gl.setPass(rs.getString("Pass"));
				
				alGledaoci.add(gl);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return alGledaoci;
	}


	public Gledalac ucitajGledaoca(String user) {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Statement st = null;
		Gledalac gl=new Gledalac();
		String sql = "SELECT * FROM gledalac WHERE User='" + user + "'";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				
				gl.setId_Gledalac(rs.getInt("ID_Gledalac"));
				gl.setImePrezime(rs.getString("ImePrezime"));
				gl.setUser(rs.getString("User"));
				gl.setPass(rs.getString("Pass"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gl;
	}

	public void upisiPozoriste(String naziv) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO pozoriste (ImePozorista)" + " VALUES (?)";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, naziv);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Pozoriste> vratiPozorista() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Pozoriste>alPozorista=new ArrayList<>();
		
		String sql = "SELECT * FROM pozoriste";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Pozoriste p=new Pozoriste();
				p.setId_Poz(rs.getInt("ID_Pozorista"));
				p.setImePozorista(rs.getString("ImePozorista"));
				alPozorista.add(p);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alPozorista;
	}

	public void upisiPredstavu(int iD_Poz, String nazivPred, String scena, String vremeIzv, String datumIzv) {
		// TODO Auto-generated method stub
		
		String sqlPred="INSERT INTO predstava (ID_Pozorista, NazivPredstave, ScenaIzvodjenja, VremeIzvodjenja, DatumIzvodjenja)" + " VALUES(?,?,?,?,?) ";
		
		try {
			PreparedStatement ps=con.prepareStatement(sqlPred);
			ps.setInt(1, iD_Poz);
			ps.setString(2, nazivPred);
			ps.setString(3, scena);
			ps.setString(4, vremeIzv);
			ps.setString(5, datumIzv);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void upisiPodUlaznica(int iD_Pred, int brojUl, int cenaUl) {
		// TODO Auto-generated method stub
		
		String sqlUlaz="INSERT INTO ulaznice (ID_Predstava, BrojRaspolozivihUlaznica, CenaUlaznica)" + " VALUES(?,?,?) ";
		
		try {
			PreparedStatement ps= con.prepareStatement(sqlUlaz);
			ps.setInt(1, iD_Pred);
			ps.setInt(2, brojUl);
			ps.setInt(3, cenaUl);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void zavrsiRegistraciju(String imePrezime, String user, String pass) {
		// TODO Auto-generated method stub
		
		String sql="INSERT INTO gledalac (ImePrezime, User, Pass)" + " VALUES(?,?,?) ";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, imePrezime);
			ps.setString(2, user);
			ps.setString(3, pass);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public void azurirajUlaznice(int ID_Pred, int brojRaspolozivihUlaznica) {
	// TODO Auto-generated method stub
	
		ResultSet rs=null;
		Statement st=null;
		
		String sql= "UPDATE ulaznice SET BrojRaspolozivihUlaznica=" + brojRaspolozivihUlaznica + " WHERE ID_Predstava=" + ID_Pred;
		
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public ArrayList<Predstave> vratiPredstave() {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Predstave>alPredstave=new ArrayList<>();
		
		String sql = "SELECT * FROM predstava";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Predstave pr=new Predstave();
				pr.setId(rs.getInt("ID_Predstava"));
				pr.setNazivPredstave(rs.getString("NazivPredstave"));
				alPredstave.add(pr);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alPredstave;
	
	}

	public Gledalac ucitajGledaoca(int iD_Gled) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		Gledalac gl=new Gledalac();
		String sql = "SELECT * FROM gledalac WHERE ID_Gledalac='" + iD_Gled + "'";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				
				gl.setId_Gledalac(rs.getInt("ID_Gledalac"));
				gl.setImePrezime(rs.getString("ImePrezime"));
				gl.setUser(rs.getString("User"));
				gl.setPass(rs.getString("Pass"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gl;
	}
	
	public ArrayList<Rezervacije> vratiRezervacije() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Rezervacije>alRezervacije=new ArrayList<>();
		String sql="SELECT * FROM predstava " 
					+ "INNER JOIN pozoriste ON predstava.ID_Pozorista=pozoriste.ID_Pozorista " 
					+ "INNER JOIN ulaznice ON predstava.ID_Predstava=ulaznice.ID_Predstava " 
					+ "INNER JOIN rezervacije ON predstava.ID_Predstava=rezervacije.ID_Predstava "
					+ "INNER JOIN gledalac ON rezervacije.ID_Gledalac=gledalac.ID_Gledalac "
					+ "WHERE predstava.DatumIzvodjenja>=CURRENT_DATE ";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Rezervacije r=new Rezervacije();
				r.setID_Rez(rs.getInt("ID_Rezervacije"));
				r.setID_Gled(rs.getInt("ID_Gledalac"));
				r.setImePrezime(rs.getString("ImePrezime"));
				r.setImePozorista(rs.getString("ImePozorista"));
				r.setId(rs.getInt("ID_Predstava"));
				r.setNazivPredstave(rs.getString("NazivPredstave"));
				r.setScenaIzvodjenja(rs.getString("ScenaIzvodjenja"));
				r.setVremeIzvodjenja(rs.getString("VremeIzvodjenja"));
				r.setBrRezUl(rs.getInt("BrojRezervisanihUlaznica"));
				r.setDatumIzvodjenja(rs.getString("DatumIzvodjenja"));
				r.setCenaUlaznica(rs.getInt("CenaUlaznica"));
				
				alRezervacije.add(r);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return alRezervacije;
	}

	public ArrayList<Rezervacije> vratiRezervacijeGledalac(int ID_Gledalac) {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Rezervacije>alRezervacijeGledalac=new ArrayList<>();
		String sql="SELECT * FROM predstava " 
					+ "INNER JOIN pozoriste ON predstava.ID_Pozorista=pozoriste.ID_Pozorista " 
					+ "INNER JOIN ulaznice ON predstava.ID_Predstava=ulaznice.ID_Predstava " 
					+ "INNER JOIN rezervacije ON predstava.ID_Predstava=rezervacije.ID_Predstava "
					+ "INNER JOIN gledalac ON rezervacije.ID_Gledalac=gledalac.ID_Gledalac "
					+ "WHERE rezervacije.ID_Gledalac='" + ID_Gledalac + "' AND predstava.DatumIzvodjenja>=CURRENT_DATE ";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Rezervacije r=new Rezervacije();
				r.setID_Rez(rs.getInt("ID_Rezervacije"));
				r.setID_Gled(rs.getInt("ID_Gledalac"));
				r.setImePozorista(rs.getString("ImePozorista"));
				r.setId(rs.getInt("ID_Predstava"));
				r.setNazivPredstave(rs.getString("NazivPredstave"));
				r.setScenaIzvodjenja(rs.getString("ScenaIzvodjenja"));
				r.setVremeIzvodjenja(rs.getString("VremeIzvodjenja"));
				r.setBrRezUl(rs.getInt("BrojRezervisanihUlaznica"));
				r.setDatumIzvodjenja(rs.getString("DatumIzvodjenja"));
				r.setCenaUlaznica(rs.getInt("CenaUlaznica"));
				
				alRezervacijeGledalac.add(r);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return alRezervacijeGledalac;
		
	}

	public void obrisiRezervaciju(int iD_Rezervacije) {
		// TODO Auto-generated method stub
		
		String sql="DELETE FROM rezervacije WHERE ID_Rezervacije=" + iD_Rezervacije + " ";
		
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void izmeniRezervaciju(int iD_Rezervacije, int noviBroj) {
		// TODO Auto-generated method stub
		
		String izmena="UPDATE rezervacije SET BrojRezervisanihUlaznica='" + noviBroj + "'WHERE ID_Rezervacije='" + iD_Rezervacije + "'";
		
			try {
				PreparedStatement ps = con.prepareStatement(izmena);
				
				ps.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void azurirajBrojUlaznica(int iD_Pred, int noviBrRasUl) {
		// TODO Auto-generated method stub
		
		String izmena="UPDATE ulaznice SET BrojRaspolozivihUlaznica='" + noviBrRasUl + "'WHERE ID_Predstava='" + iD_Pred + "'";
		
			try {
				PreparedStatement ps = con.prepareStatement(izmena);
				
				ps.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public ArrayList<Predstave> vratiRepertoar(String nazivPozoriste) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Predstave>alRepertoar=new ArrayList<>();
		String sql="SELECT * FROM predstava " 
					+ "INNER JOIN pozoriste ON predstava.ID_Pozorista=pozoriste.ID_Pozorista " 
					+ "INNER JOIN ulaznice ON predstava.ID_Predstava=ulaznice.ID_Predstava " 
					+ "WHERE pozoriste.ImePozorista='" + nazivPozoriste + "' AND predstava.DatumIzvodjenja>=CURRENT_DATE ";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Predstave pr=new Predstave();
				pr.setImePozorista(rs.getString("ImePozorista"));
				pr.setId(rs.getInt("ID_Predstava"));
				pr.setNazivPredstave(rs.getString("NazivPredstave"));
				pr.setScenaIzvodjenja(rs.getString("ScenaIzvodjenja"));
				pr.setVremeIzvodjenja(rs.getString("VremeIzvodjenja"));
				pr.setBrojRaspolozivihUlaznica(rs.getInt("BrojRaspolozivihUlaznica"));
				pr.setDatumIzvodjenja(rs.getString("DatumIzvodjenja"));
				pr.setCenaUlaznica(rs.getInt("CenaUlaznica"));
				
				alRepertoar.add(pr);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return alRepertoar;
	}

	public ArrayList<Rezervacije> vratiRezervacijeGledalac(int iD_Gledalac, String nazivPozoriste) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Rezervacije>alRezervacijeGledalac=new ArrayList<>();
		String sql="SELECT * FROM predstava " 
					+ "INNER JOIN pozoriste ON predstava.ID_Pozorista=pozoriste.ID_Pozorista " 
					+ "INNER JOIN ulaznice ON predstava.ID_Predstava=ulaznice.ID_Predstava " 
					+ "INNER JOIN rezervacije ON predstava.ID_Predstava=rezervacije.ID_Predstava "
					+ "INNER JOIN gledalac ON rezervacije.ID_Gledalac=gledalac.ID_Gledalac "
					+ "WHERE rezervacije.ID_Gledalac='" + iD_Gledalac + "' AND pozoriste.ImePozorista='" + nazivPozoriste + "' AND predstava.DatumIzvodjenja>=CURRENT_DATE ";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Rezervacije r=new Rezervacije();
				r.setID_Rez(rs.getInt("ID_Rezervacije"));
				r.setID_Gled(rs.getInt("ID_Gledalac"));
				r.setImePozorista(rs.getString("ImePozorista"));
				r.setId(rs.getInt("ID_Predstava"));
				r.setNazivPredstave(rs.getString("NazivPredstave"));
				r.setScenaIzvodjenja(rs.getString("ScenaIzvodjenja"));
				r.setVremeIzvodjenja(rs.getString("VremeIzvodjenja"));
				r.setBrRezUl(rs.getInt("BrojRezervisanihUlaznica"));
				r.setDatumIzvodjenja(rs.getString("DatumIzvodjenja"));
				r.setCenaUlaznica(rs.getInt("CenaUlaznica"));
				
				alRezervacijeGledalac.add(r);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return alRezervacijeGledalac;
	}

	public ArrayList<Rezervacije> vratiRezervacije(String nazivPozoriste) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Statement st=null;
		ArrayList<Rezervacije>alRezervacije=new ArrayList<>();
		String sql="SELECT * FROM predstava " 
					+ "INNER JOIN pozoriste ON predstava.ID_Pozorista=pozoriste.ID_Pozorista " 
					+ "INNER JOIN ulaznice ON predstava.ID_Predstava=ulaznice.ID_Predstava " 
					+ "INNER JOIN rezervacije ON predstava.ID_Predstava=rezervacije.ID_Predstava "
					+ "INNER JOIN gledalac ON rezervacije.ID_Gledalac=gledalac.ID_Gledalac "
					+ "WHERE pozoriste.ImePozorista='" + nazivPozoriste + "' AND predstava.DatumIzvodjenja>=CURRENT_DATE ";
		
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				Rezervacije r=new Rezervacije();
				r.setID_Rez(rs.getInt("ID_Rezervacije"));
				r.setID_Gled(rs.getInt("ID_Gledalac"));
				r.setImePrezime(rs.getString("ImePrezime"));
				r.setImePozorista(rs.getString("ImePozorista"));
				r.setId(rs.getInt("ID_Predstava"));
				r.setNazivPredstave(rs.getString("NazivPredstave"));
				r.setScenaIzvodjenja(rs.getString("ScenaIzvodjenja"));
				r.setVremeIzvodjenja(rs.getString("VremeIzvodjenja"));
				r.setBrRezUl(rs.getInt("BrojRezervisanihUlaznica"));
				r.setDatumIzvodjenja(rs.getString("DatumIzvodjenja"));
				r.setCenaUlaznica(rs.getInt("CenaUlaznica"));
				
				alRezervacije.add(r);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return alRezervacije;
	}

	

	public void obrisiPozoriste(int iD_Pozorista) {
		// TODO Auto-generated method stub
		
		String sql="DELETE FROM pozoriste WHERE ID_Pozorista=" + iD_Pozorista + " ";
		
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void obrisiPredstavu(int iD_Predstave) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM predstava WHERE ID_Predstava=" + iD_Predstave + " ";
		
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void obrisiGledaoca(int iD_Gledaoca) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM gledalac WHERE ID_Gledalac=" + iD_Gledaoca + " ";
		
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
