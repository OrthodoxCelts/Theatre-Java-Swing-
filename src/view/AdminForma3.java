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
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class AdminForma3 extends JFrame {

	private JPanel contentPane;
	private JTable table_Pozorista;
	private JTable table_Predstave;
	private JTable table_Gledaoci;
	private int ID_Pozorista, ID_Predstave, ID_Gledaoca;
	private String nazivPozoriste, nazivPredstave, imeGledaoca;
	private DefaultTableModel dtm1=new DefaultTableModel();
	private DefaultTableModel dtm2=new DefaultTableModel();
	private DefaultTableModel dtm3=new DefaultTableModel();
	private DefaultTableModel dtm4=new DefaultTableModel();
	private JTable table_Repertoar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminForma3 frame = new AdminForma3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminForma3() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 814, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_Pozorista = new JTable(dtm1);
		JScrollPane scrollPane = new JScrollPane(table_Pozorista);
		scrollPane.setBounds(10, 36, 170, 220);
		contentPane.add(scrollPane);
		table_Pozorista.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int red=table_Pozorista.getSelectedRow();
				
				String id_Poz, imePoz=null;
				
				id_Poz=(table_Pozorista.getModel().getValueAt(red, 0).toString());
				ID_Pozorista=Integer.parseInt(id_Poz);
				
				imePoz=(table_Pozorista.getModel().getValueAt(red, 1).toString());
				nazivPozoriste=imePoz;
			}
		});
		scrollPane.setViewportView(table_Pozorista);
		
		table_Predstave = new JTable(dtm2);
		JScrollPane scrollPane_1 = new JScrollPane(table_Predstave);
		scrollPane_1.setBounds(319, 36, 188, 220);
		contentPane.add(scrollPane_1);
		table_Predstave.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int red=table_Predstave.getSelectedRow();
				
				String id_Pred, imePred=null;
				
				id_Pred=(table_Predstave.getModel().getValueAt(red, 0).toString());
				ID_Predstave=Integer.parseInt(id_Pred);
				
				imePred=(table_Predstave.getModel().getValueAt(red, 1).toString());
				nazivPredstave=imePred;
			}
		});
		scrollPane_1.setViewportView(table_Predstave);
		
		table_Gledaoci = new JTable(dtm3);
		JScrollPane scrollPane_2 = new JScrollPane(table_Gledaoci);
		scrollPane_2.setBounds(530, 36, 198, 220);
		contentPane.add(scrollPane_2);
		table_Gledaoci.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int red=table_Gledaoci.getSelectedRow();
				
				String id_Gled, imeGled=null;
				
				id_Gled=(table_Gledaoci.getModel().getValueAt(red, 0).toString());
				ID_Gledaoca=Integer.parseInt(id_Gled);
				
				imeGled=(table_Gledaoci.getModel().getValueAt(red, 1).toString());
				imeGledaoca=imeGled;
			}
		});
		
		scrollPane_2.setViewportView(table_Gledaoci);
		
		JLabel lblPozorista = new JLabel("Pozorista");
		lblPozorista.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPozorista.setBounds(64, 11, 79, 14);
		contentPane.add(lblPozorista);
		
		JLabel lblPredstave = new JLabel("Predstave");
		lblPredstave.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPredstave.setBounds(370, 11, 79, 14);
		contentPane.add(lblPredstave);
		
		JLabel lblGledaoci = new JLabel("Gledaoci");
		lblGledaoci.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGledaoci.setBounds(588, 11, 84, 14);
		contentPane.add(lblGledaoci);
		
		table_Repertoar = new JTable(dtm4);
		JScrollPane scrollPane_3 = new JScrollPane(table_Repertoar);
		scrollPane_3.setBounds(10, 297, 778, 224);
		contentPane.add(scrollPane_3);
		
		scrollPane_3.setViewportView(table_Repertoar);
		
		JButton btnOsveziRepertoar = new JButton("Osvezi repertoar");
		btnOsveziRepertoar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabeluRepertoar();
			}
		});
		btnOsveziRepertoar.setBounds(644, 263, 144, 23);
		contentPane.add(btnOsveziRepertoar);
		
		JLabel lblRepertoarIUlaznice = new JLabel("Repertoar i ulaznice");
		lblRepertoarIUlaznice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRepertoarIUlaznice.setBounds(291, 272, 178, 14);
		contentPane.add(lblRepertoarIUlaznice);
		
		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Pozoriste pr:Kontroler.getInstanca().vratiPozorista())
					
					if (nazivPozoriste.equals(pr.getImePozorista())) {
						prikaziPredstave(nazivPozoriste);
					}
			}
		});
		btnPrikazi.setBounds(190, 138, 119, 23);
		contentPane.add(btnPrikazi);
		
		JButton btnSvePredstave = new JButton("Sve predstave");
		btnSvePredstave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				osveziTabeluPredstave();
			}
		});
		btnSvePredstave.setBounds(190, 104, 119, 23);
		contentPane.add(btnSvePredstave);
		
		/*JButton btnObrisiPozoriste = new JButton("Obrisi pozoriste");
		btnObrisiPozoriste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Kontroler.getInstanca().obrisiPozoriste(ID_Pozorista);
				osveziTabeluPozoriste();
				JOptionPane.showMessageDialog(null, "Uspesno ste Izbrisali pozoriste");
			}catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Doslo je do greske");
			}
			}
		});
		btnObrisiPozoriste.setBounds(10, 294, 170, 23);
		contentPane.add(btnObrisiPozoriste);
		
		JButton btnObrisiPredstavu = new JButton("Obrisi predstavu");
		btnObrisiPredstavu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Kontroler.getInstanca().obrisiPredstavu(ID_Predstave);
				osveziTabeluPredstave();
				JOptionPane.showMessageDialog(null, "Uspesno ste Izbrisali predstavu");
				}catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Doslo je do greske");
				}
			}
		});
		btnObrisiPredstavu.setBounds(190, 294, 188, 23);
		contentPane.add(btnObrisiPredstavu);
		
		JButton btnObrisiGledaoca = new JButton("Obrisi gledaoca");
		btnObrisiGledaoca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Kontroler.getInstanca().obrisiGledaoca(ID_Gledaoca);
				osveziTabeluGledaoci();
				JOptionPane.showMessageDialog(null, "Uspesno ste Izbrisali gledaoca");
				}catch (Exception e3) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Doslo je do greske");
				}
			}
		});
		btnObrisiGledaoca.setBounds(388, 294, 198, 23);
		contentPane.add(btnObrisiGledaoca);*/
		
		Object[]kolone1=new Object[2];
		kolone1[0]="ID Pozorista";
		kolone1[1]="Pozoriste";
		
		dtm1.addColumn(kolone1[0]);
		dtm1.addColumn(kolone1[1]);
		osveziTabeluPozoriste();
		
		Object[]kolone2=new Object[2];
		kolone2[0]="ID Predstave";
		kolone2[1]="Predstava";
		
		dtm2.addColumn(kolone2[0]);
		dtm2.addColumn(kolone2[1]);
		osveziTabeluPredstave();
		
		Object[]kolone3=new Object[2];
		kolone3[0]="ID Gledaoca";
		kolone3[1]="Gledalac";
		
		dtm3.addColumn(kolone3[0]);
		dtm3.addColumn(kolone3[1]);
		osveziTabeluGledaoci();
		
		Object[]kolone4=new Object[7];
		kolone4[0]="Pozoriste";
		kolone4[1]="Predstava";
		kolone4[2]="Datum";
		kolone4[3]="Vreme";
		kolone4[4]="Scena";
		kolone4[5]="Broj ulaznica";
		kolone4[6]="Cena po kom";
		
		dtm4.addColumn(kolone4[0]);
		dtm4.addColumn(kolone4[1]);
		dtm4.addColumn(kolone4[2]);
		dtm4.addColumn(kolone4[3]);
		dtm4.addColumn(kolone4[4]);
		dtm4.addColumn(kolone4[5]);
		dtm4.addColumn(kolone4[6]);
		osveziTabeluRepertoar();
	}

	

	protected void prikaziPredstave(String nazivPozoriste) {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[2];
		dtm2.setRowCount(0);
		
		for(Predstave pr:Kontroler.getInstanca().vratiRepertoar(nazivPozoriste)) {
			
			redovi[0]=pr.getId();
			redovi[1]=pr.getNazivPredstave();
			
			dtm2.addRow(redovi);
		}
	}

	private void osveziTabeluRepertoar() {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[7];
		dtm4.setRowCount(0);
		for(Predstave pr:Kontroler.getInstanca().vratiRepertoar()) {
			
			redovi[0]=pr.getImePozorista();
			redovi[1]=pr.getNazivPredstave();
			redovi[2]=pr.getDatumIzvodjenja();
			redovi[3]=pr.getVremeIzvodjenja();
			redovi[4]=pr.getScenaIzvodjenja();
			redovi[5]=pr.getBrojRaspolozivihUlaznica();
			redovi[6]=pr.getCenaUlaznica();
			
			dtm4.addRow(redovi);	
		}
	}

	private void osveziTabeluGledaoci() {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[2];
		dtm3.setRowCount(0);
		
		for(Gledalac gl:Kontroler.getInstanca().vratiGledaoce()) {
			
			redovi[0]=gl.getId_Gledalac();
			redovi[1]=gl.getImePrezime();
			
			dtm3.addRow(redovi);
		}
	}

	private void osveziTabeluPredstave() {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[2];
		dtm2.setRowCount(0);
		
		for(Predstave pr:Kontroler.getInstanca().vratiPredstave()) {
			
			redovi[0]=pr.getId();
			redovi[1]=pr.getNazivPredstave();
			
			dtm2.addRow(redovi);
		}
	}

	private void osveziTabeluPozoriste() {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[2];
		dtm1.setRowCount(0);
		
		for(Pozoriste p:Kontroler.getInstanca().vratiPozorista()) {
			
			redovi[0]=p.getId_Poz();
			redovi[1]=p.getImePozorista();
			
			dtm1.addRow(redovi);
		}
	}
}
