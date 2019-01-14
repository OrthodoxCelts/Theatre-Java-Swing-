package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Gledalac;
import domen.Pozoriste;
import domen.Predstave;
import domen.Rezervacije;
import kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class KorisnikForma2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblVrednost;
	private JComboBox cbPozoriste;
	private ArrayList<Rezervacije>alRezervacijeGledalac=new ArrayList<>();
	private int ID_Rezervacije, brRezUlaznica, ID_Pred, noviBrRasUl, ID_Gledalac;
	private double ukupnaVrednost, Vrednost;
	private String ukupnaVrednostTxt, nazPred, nazivPozoriste;
	private Gledalac gledalac;
	private DefaultTableModel dtm=new DefaultTableModel();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public KorisnikForma2(int ID_Gled) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1053, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(36, 98, 958, 274);
		contentPane.add(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int red=table.getSelectedRow();
				
				String id_Rez, nazivPred, brRez=null;
				
				id_Rez=(table.getModel().getValueAt(red, 0).toString());
				ID_Rezervacije=Integer.parseInt(id_Rez);
				
				nazivPred=(table.getModel().getValueAt(red, 2).toString());
				nazPred=nazivPred;
				
				brRez=(table.getModel().getValueAt(red, 6).toString());
				brRezUlaznica=Integer.parseInt(brRez);
				
			}
		});
		
		
		scrollPane.setViewportView(table);
		
		JLabel lblUlogovaniSte = new JLabel("New label");
		lblUlogovaniSte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUlogovaniSte.setBounds(196, 70, 389, 14);
		contentPane.add(lblUlogovaniSte);
		gledalac=Kontroler.getInstanca().ucitajGledaoca(ID_Gled);
		lblUlogovaniSte.setText("Ulogovani ste kao korisnik " + gledalac.getImePrezime());
		
		JLabel lblPregledTekucihRezervacija = new JLabel("Pregled tekucih rezervacija");
		lblPregledTekucihRezervacija.setBounds(429, 32, 217, 14);
		lblPregledTekucihRezervacija.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblPregledTekucihRezervacija);
		
		JButton btnObrisiRezervaciju = new JButton("Obrisi rezervaciju");
		btnObrisiRezervaciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Kontroler.getInstanca().obrisiRezervaciju(ID_Rezervacije);
				podesiIDPredstave(nazPred);
				podesiRaspUlaznice(nazPred);
				noviBrRasUl+=brRezUlaznica;
				Kontroler.getInstanca().azurirajBrojUlaznica(ID_Pred, noviBrRasUl);
				osveziTabelu(Kontroler.getInstanca().vratiRezervacijeGledalac(ID_Gledalac));
				JOptionPane.showMessageDialog(null, "Izbrisali ste rezervaciju za predstavu " + nazPred);
			}
		});
		btnObrisiRezervaciju.setBounds(438, 398, 147, 23);
		contentPane.add(btnObrisiRezervaciju);
		
		lblVrednost = new JLabel("Vrednost");
		lblVrednost.setBounds(36, 402, 353, 14);
		contentPane.add(lblVrednost);
		
		JButton btnOsveziTabelu = new JButton("Osvezi tabelu");
		btnOsveziTabelu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabelu(Kontroler.getInstanca().vratiRezervacijeGledalac(ID_Gledalac));
			}
		});
		btnOsveziTabelu.setBounds(866, 398, 128, 23);
		contentPane.add(btnOsveziTabelu);
		
		cbPozoriste = new JComboBox();
		cbPozoriste.addItem("Rezervacije");
		cbPozoriste.addItem("-- -- --");
		cbPozoriste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nazivPozoriste = (String) cbPozoriste.getSelectedItem();
				
				for(Rezervacije rz:Kontroler.getInstanca().vratiRezervacijeGledalac(ID_Gledalac)) {
					if(nazivPozoriste.equals("Rezervacije")) {
						osveziTabelu(alRezervacijeGledalac);
					}else if(nazivPozoriste.equals(rz.getImePozorista())) {
						osveziTabelu(nazivPozoriste);
					}
				}
			}
		});
		cbPozoriste.setBounds(36, 67, 128, 20);
		contentPane.add(cbPozoriste);
		
		Object[]kolone=new Object[8];
		kolone[0]="ID Rez";
		kolone[1]="Pozoriste";
		kolone[2]="Predstava";
		kolone[3]="Datum";
		kolone[4]="Vreme";
		kolone[5]="Scena";
		kolone[6]="Broj rez ulaznica";
		kolone[7]="Cena po kom";
		
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		dtm.addColumn(kolone[6]);
		dtm.addColumn(kolone[7]);
		
		ID_Gledalac = gledalac.getId_Gledalac();
		alRezervacijeGledalac=Kontroler.getInstanca().vratiRezervacijeGledalac(ID_Gledalac);
		
		osveziTabelu(alRezervacijeGledalac);
		napuniCbPozoriste();
	}

	protected void osveziTabelu(String nazivPozoriste) {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Object[]redovi=new Object[8];
		
		for(Rezervacije r:Kontroler.getInstanca().vratiRezervacijeGledalac(ID_Gledalac, nazivPozoriste)) {
			
			redovi[0]=r.getID_Rez();
			redovi[1]=r.getImePozorista();
			redovi[2]=r.getNazivPredstave();
			redovi[3]=r.getDatumIzvodjenja();
			redovi[4]=r.getVremeIzvodjenja();
			redovi[5]=r.getScenaIzvodjenja();
			redovi[6]=r.getBrRezUl();
			redovi[7]=r.getCenaUlaznica();
			double Vrednost=r.getBrRezUl()*r.getCenaUlaznica();
			
			dtm.addRow(redovi);
		}
	}

	private void napuniCbPozoriste() {
		// TODO Auto-generated method stub
		for (Pozoriste p:Kontroler.getInstanca().vratiPozorista())
			
			cbPozoriste.addItem(p.getImePozorista());
	}

	private void osveziTabelu(ArrayList<Rezervacije> alRezervacijeGledalac) {
		// TODO Auto-generated method stub
		ukupnaVrednost=0;
		
		dtm.setRowCount(0);
		Object[]redovi=new Object[8];
		
		for(Rezervacije r:alRezervacijeGledalac) {
			
			
			redovi[0]=r.getID_Rez();
			redovi[1]=r.getImePozorista();
			redovi[2]=r.getNazivPredstave();
			redovi[3]=r.getDatumIzvodjenja();
			redovi[4]=r.getVremeIzvodjenja();
			redovi[5]=r.getScenaIzvodjenja();
			redovi[6]=r.getBrRezUl();
			redovi[7]=r.getCenaUlaznica();
			double Vrednost=r.getBrRezUl()*r.getCenaUlaznica();
			
			dtm.addRow(redovi);
			
			ukupnaVrednost+=Vrednost;
		}
		String ukupnaVrednostTxt=Double.toString(ukupnaVrednost);
		ukupnaVrednostTxt="Vrednost svih Vasih rezervacija je " + ukupnaVrednostTxt+ " dinara";
		lblVrednost.setText(ukupnaVrednostTxt);
	}

	protected void podesiIDPredstave(String nazPred) {
		// TODO Auto-generated method stub
		for (Predstave p:Kontroler.getInstanca().vratiRepertoar())
			if(p.getNazivPredstave().equals(nazPred))
				ID_Pred=p.getId();
	}
	
	protected void podesiRaspUlaznice(String nazPred) {
		// TODO Auto-generated method stub
		for (Predstave p:Kontroler.getInstanca().vratiRepertoar())
			if(p.getNazivPredstave().equals(nazPred))
				noviBrRasUl=p.getBrojRaspolozivihUlaznica();
	}	
}
