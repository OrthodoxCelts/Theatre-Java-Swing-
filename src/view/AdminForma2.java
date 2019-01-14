package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Pozoriste;
import domen.Predstave;
import domen.Rezervacije;
import kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class AdminForma2 extends JFrame {

	private JPanel contentPane;
	private JTable table_Rezervacije;
	private JComboBox cbPozoriste;
	private DefaultTableModel dtm=new DefaultTableModel();
	private int ID_Rezervacije, brRezUl, brojRezervisanihUlaznica, cenaUlaznica, noviBrRasUl, ID_Pred;
	private String ImeGledalac, nazivPozoriste, ImePozorista, nazivPredstave, scenaIzvodjenja, vremeIzvodjenja, datumIzvodjenja;
	private JButton btnOsveziTabelu;
	private JButton btnPretragaGledalac;
	private JTextField tfPretraga;
	private ArrayList<Rezervacije>alSort=new ArrayList<>();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AdminForma2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1013, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnObrisiRezervaciju = new JButton("Obrisi rezervaciju");
		btnObrisiRezervaciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().obrisiRezervaciju(ID_Rezervacije);
				podesiIDPredstave(nazivPredstave);
				podesiRaspUlaznice(nazivPredstave);
				noviBrRasUl+=brojRezervisanihUlaznica;
				Kontroler.getInstanca().azurirajBrojUlaznica(ID_Pred, noviBrRasUl);
				osveziTabeluRezervacija();
				JOptionPane.showMessageDialog(null, "Uspesno ste Izbrisali rezervaciju");
			}
		});
		btnObrisiRezervaciju.setBounds(21, 297, 140, 23);
		contentPane.add(btnObrisiRezervaciju);
		
		cbPozoriste = new JComboBox();
		cbPozoriste.addItem("Rezervacije");
		cbPozoriste.addItem("-- -- -- --");
		cbPozoriste.addItem("Sortiraj - Broj Rez");
		cbPozoriste.addItem("-- -- -- --");
		cbPozoriste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nazivPozoriste = (String) cbPozoriste.getSelectedItem();
				
				for(Rezervacije rz:Kontroler.getInstanca().vratiRezervacije()) {
					if(nazivPozoriste.equals("Rezervacije")) {
						osveziTabeluRezervacija();
					}else if(nazivPozoriste.equals("Sortiraj - Broj Rez")) {
						Collections.sort(alSort, Predstave.StuNameComparator2);
						ucitajSortiranoPoBrojRez();
					}else if(nazivPozoriste.equals(rz.getImePozorista())) {
						osveziTabeluRezervacija(nazivPozoriste);
					}
				}
			}
		});
		cbPozoriste.setBounds(21, 11, 153, 20);
		contentPane.add(cbPozoriste);
		
		table_Rezervacije = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(table_Rezervacije);
		scrollPane.setBounds(21, 42, 966, 244);
		contentPane.add(scrollPane);
		
		table_Rezervacije.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
						int red=table_Rezervacije.getSelectedRow();
						
						String id_Rez, imePoz, id, naziv, scena, vreme, brojRezUl, datum, cena=null;
						
						id_Rez=(table_Rezervacije.getModel().getValueAt(red, 0).toString());
						ID_Rezervacije=Integer.parseInt(id_Rez);
						
						imePoz=(table_Rezervacije.getModel().getValueAt(red, 2).toString());
						ImePozorista=imePoz;
						
						naziv=(table_Rezervacije.getModel().getValueAt(red, 3).toString());
						nazivPredstave=naziv;
						
						scena=(table_Rezervacije.getModel().getValueAt(red, 6).toString());
						scenaIzvodjenja=scena;
						
						brojRezUl=(table_Rezervacije.getModel().getValueAt(red, 7).toString());
						brojRezervisanihUlaznica=Integer.valueOf(brojRezUl);
						
						cena=(table_Rezervacije.getModel().getValueAt(red, 8).toString());
						cenaUlaznica=Integer.valueOf(cena);
					
					}
				});
		
		scrollPane.setViewportView(table_Rezervacije);
		
		btnOsveziTabelu = new JButton("Osvezi tabelu Rezervacija");
		btnOsveziTabelu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabeluRezervacija();
			}
		});
		btnOsveziTabelu.setBounds(349, 324, 198, 23);
		contentPane.add(btnOsveziTabelu);
		
		
		JLabel lblRezervacije = new JLabel("Rezervacije svih korisnika");
		lblRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRezervacije.setBounds(371, 11, 231, 17);
		contentPane.add(lblRezervacije);
		
		btnPretragaGledalac = new JButton("Pretraga gledalac");
		btnPretragaGledalac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pretragaGledalac();
				obrisiPoljePretraga();
			}
		});
		btnPretragaGledalac.setBounds(834, 297, 153, 23);
		contentPane.add(btnPretragaGledalac);
		
		tfPretraga = new JTextField();
		tfPretraga.setBounds(654, 298, 170, 20);
		contentPane.add(tfPretraga);
		tfPretraga.setColumns(10);
		
		Object[]kolone=new Object[9];
		kolone[0]="ID Rez";
		kolone[1]="Korisnik";
		kolone[2]="Pozoriste";
		kolone[3]="Predstava";
		kolone[4]="Datum";
		kolone[5]="Vreme";
		kolone[6]="Scena";
		kolone[7]="Broj rez ulaznica";
		kolone[8]="Cena po kom";
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		dtm.addColumn(kolone[6]);
		dtm.addColumn(kolone[7]);
		dtm.addColumn(kolone[8]);
		
		osveziTabeluRezervacija();
		napuniCbPozoriste();
		alSort=Kontroler.getInstanca().vratiRezervacije();
	
	}
	protected void ucitajSortiranoPoBrojRez() {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[9];
		dtm.setRowCount(0);
		
		for(Rezervacije r:alSort) {
			
			redovi[0]=r.getID_Rez();
			redovi[1]=r.getImePrezime();
			redovi[2]=r.getImePozorista();
			redovi[3]=r.getNazivPredstave();
			redovi[4]=r.getDatumIzvodjenja();
			redovi[5]=r.getVremeIzvodjenja();
			redovi[6]=r.getScenaIzvodjenja();
			redovi[7]=r.getBrRezUl();
			redovi[8]=r.getCenaUlaznica();
			dtm.addRow(redovi);
		}
	}
	protected void obrisiPoljePretraga() {
		// TODO Auto-generated method stub
		tfPretraga.setText("");
	}
	protected void pretragaGledalac() {
		// TODO Auto-generated method stub
		String Gledalac=tfPretraga.getText();

		Object[]redovi=new Object[9];
		dtm.setRowCount(0);
		
		for(Rezervacije r:Kontroler.getInstanca().vratiRezervacije()) {
			if(r.getImePrezime().toLowerCase().contains(Gledalac.toLowerCase())) {
			
				redovi[0]=r.getID_Rez();
				redovi[1]=r.getImePrezime();
				redovi[2]=r.getImePozorista();
				redovi[3]=r.getNazivPredstave();
				redovi[4]=r.getDatumIzvodjenja();
				redovi[5]=r.getVremeIzvodjenja();
				redovi[6]=r.getScenaIzvodjenja();
				redovi[7]=r.getBrRezUl();
				redovi[8]=r.getCenaUlaznica();
				dtm.addRow(redovi);
			
			}
		}
		
	}	
	
	protected void osveziTabeluRezervacija(String nazivPozoriste) {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[9];
		dtm.setRowCount(0);
		
		for(Rezervacije r:Kontroler.getInstanca().vratiRezervacije(nazivPozoriste)) {
			
			redovi[0]=r.getID_Rez();
			redovi[1]=r.getImePrezime();
			redovi[2]=r.getImePozorista();
			redovi[3]=r.getNazivPredstave();
			redovi[4]=r.getDatumIzvodjenja();
			redovi[5]=r.getVremeIzvodjenja();
			redovi[6]=r.getScenaIzvodjenja();
			redovi[7]=r.getBrRezUl();
			redovi[8]=r.getCenaUlaznica();
			dtm.addRow(redovi);
		}
	}
	private void osveziTabeluRezervacija() {
		
		Object[]redovi=new Object[9];
		dtm.setRowCount(0);
		
		for(Rezervacije r:Kontroler.getInstanca().vratiRezervacije()) {
			
			redovi[0]=r.getID_Rez();
			redovi[1]=r.getImePrezime();
			redovi[2]=r.getImePozorista();
			redovi[3]=r.getNazivPredstave();
			redovi[4]=r.getDatumIzvodjenja();
			redovi[5]=r.getVremeIzvodjenja();
			redovi[6]=r.getScenaIzvodjenja();
			redovi[7]=r.getBrRezUl();
			redovi[8]=r.getCenaUlaznica();
			dtm.addRow(redovi);
		}
	}

	protected void podesiRaspUlaznice(String nazivPredstave) {
		// TODO Auto-generated method stub
		for (Predstave p:Kontroler.getInstanca().vratiRepertoar())
			if(p.getNazivPredstave().equals(nazivPredstave))
				noviBrRasUl=p.getBrojRaspolozivihUlaznica();
	}

	protected void podesiIDPredstave(String nazivPredstave) {
		// TODO Auto-generated method stub
		for (Predstave p:Kontroler.getInstanca().vratiRepertoar())
			if(p.getNazivPredstave().equals(nazivPredstave))
				ID_Pred=p.getId();
	}

	private void napuniCbPozoriste() {
		// TODO Auto-generated method stub
		
		for (Pozoriste p:Kontroler.getInstanca().vratiPozorista())
			
			cbPozoriste.addItem(p.getImePozorista());
	}
}
