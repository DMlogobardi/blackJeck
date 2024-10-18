package multiplay;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import Start.Start;
import blackJack.Carta;

public class Multigiocatore {

	private JFrame frame;
	private double puntata;
	private boolean punta = true;
	private int punti = 0;
	private int puntiBanco = 0;
	private int cont;
	private ArrayList<JLabel> mano;
	private ArrayList<JLabel> banco;
	private ArrayList<Carta> mazzo;
	private ArrayList<Integer> usciti;
	private boolean start = false;
	private boolean uguale = true;
	private ArrayList<Integer> lable;
	private Border inMenu;
	private Border bordCard;
	private boolean isBanco = false;
	private Boolean onOff = true;
	private Boolean newMano = false;
	private Boolean sballato = false;
	private Boolean carta = false;
	private Boolean pareggio = false;
	private double guadagni = 0;
	protected Clip clip;
	private ArrayList<Giocatori> nGiocatori;
	private int turno = 1;

	private JLabel lbl;
	private JTextPane textPane;
	private JLabel lblNewLabel_14;
	private JMenuBar MenuBar;
	private JLabel lblNewLabel_12;
	private JLabel giocatoreC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Multigiocatore window = new Multigiocatore();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public Multigiocatore() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		initialize();
	}

	public void giocatori() {
		int a = 0;
		boolean ok = false;
		while (ok == false) {
			try {
				a = Integer.parseInt(JOptionPane.showInputDialog(frame, "Numero giocatori"));
				ok = true;
				if (a < 2 || a > 4) {
					JOptionPane.showMessageDialog(frame, "Valoro inserito non valido");
					ok = false;
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, "Forse hai sbagliato qualcosa");
				ok = false;
			}
		}
		for (int i = 0; i <= a; i++) {
			Giocatori g = new Giocatori(0, 0.0, i += 1);
			nGiocatori.add(g);

		}
		giocatoreC.setText("Giocatore " + nGiocatori.get(0).getnGiocatore());
	}

	public int random() {
		Random rand = new Random();
		int upperbound = 52;
		int int_random = rand.nextInt(upperbound);
		return int_random;
	}

	public int indice() {
		int indice = 0;
		indice = random();
		if (usciti.size() == 0) {
			usciti.add(indice);
			return indice;
		} else {
			while (uguale == true) {
				for (int i = 0; i < usciti.size(); i++) {
					if (indice == usciti.get(i)) {
						indice = 0;
						indice = random();
					}
				}
				usciti.add(indice);
				uguale = false;
			}
			uguale = true;
			return indice;
		}
	}

	public int valore(int indice, boolean thisBanco) {
		if (mazzo.get(indice).getValore() == 11) {
			if (thisBanco == false) {
				if (punti < 11) {
					return 11;
				}
				return 1;
			} else {
				isBanco = false;
				if (puntiBanco < 11) {
					return 11;
				}
				return 1;
			}
		} else {
			return mazzo.get(indice).getValore();
		}
	}

	public int isAsso(int indice) {
		if (mazzo.get(indice).getValore() == 11) {
			return 1;
		}
		return 0;
	}

	public Boolean isInt(double d) {
		if (d % 1 == 0) {
			return true;
		}
		return false;
	}

	public void punta() {
		try {
			if (punta == true) {
				if (textPane.getText().equals(""))
					JOptionPane.showMessageDialog(frame, "Inserisci un numero");
				else {
					if (Double.parseDouble(textPane.getText()) > 0) {
						puntata += Double.parseDouble(textPane.getText());
						if (puntata % 1 == 0) {
							lbl.setText(Integer.toString((int) puntata));
							nGiocatori.get(turno -= 1).setPuntata(puntata);
							textPane.setText("");
						} else {
							lbl.setText(Double.toString(puntata));
							nGiocatori.get(turno -= 1).setPuntata(puntata);
							textPane.setText("");
						}
						punta = false;
					} else {
						JOptionPane.showMessageDialog(frame, "Non puoi puntare meno di 0");
						textPane.setText("");
					}
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Hai gia puntato");
				textPane.setText("");
			}
		} catch (NumberFormatException e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(frame, "Forse hai sbagliato qualcosa");
			textPane.setText("");
		}
	}

	public void start() {
		if (start == false) {
			if (puntata == 0)
				JOptionPane.showMessageDialog(frame, "Devi puntare per iniziare");
			else {
				int indice = indice();

				ImageIcon carta1 = new ImageIcon(mazzo.get(indice).getFronte());
				mano.get(0).setIcon(carta1);
				punti += valore(indice, isBanco);
				indice = indice();

				ImageIcon carta2 = new ImageIcon(mazzo.get(indice).getFronte());
				mano.get(1).setIcon(carta2);
				punti += valore(indice, isBanco);

				indice = indice();

				ImageIcon carta3 = new ImageIcon(mazzo.get(indice).getFronte());
				banco.get(0).setIcon(carta3);
				puntiBanco += valore(indice, isBanco = true);

				indice = indice();

				ImageIcon carta4 = new ImageIcon(mazzo.get(indice).getRetro());
				banco.get(1).setIcon(carta4);
				puntiBanco += valore(indice, isBanco = true);

				start = true;
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Il gioco e gia iniziato");
		}
	}

	private void fineMano() {

		if (punti == 21) {
			guadagni += puntata * 2;
			if (isInt(guadagni)) {
				JOptionPane.showMessageDialog(frame, "Hai vinto, ora hai: " + (int) guadagni);
				lblNewLabel_14.setText(Integer.toString((int) guadagni));
			} else {
				JOptionPane.showMessageDialog(frame, "Hai vinto, ora hai: " + guadagni);
				lblNewLabel_14.setText(Double.toString(guadagni));
			}
		} else if (puntiBanco > 21) {
			guadagni += puntata * 2;
			if (isInt(guadagni)) {
				JOptionPane.showMessageDialog(frame, "Il banco ha sballato hai vinto, ora hai: " + (int) guadagni);
				lblNewLabel_14.setText(Integer.toString((int) guadagni));
			} else {
				JOptionPane.showMessageDialog(frame, "Il banco ha sballato hai vinto, ora hai: " + guadagni);
				lblNewLabel_14.setText(Double.toString(guadagni));
			}
		} else if (puntiBanco > punti) {
			JOptionPane.showMessageDialog(frame, "Hai perso");
			guadagni -= puntata;
			if (isInt(guadagni))
				lblNewLabel_14.setText(Integer.toString((int) guadagni));
			else
				lblNewLabel_14.setText(Double.toString(guadagni));
		} else if (puntiBanco < punti) {
			guadagni += puntata * 2;
			if (isInt(guadagni)) {
				JOptionPane.showMessageDialog(frame, "Hai vinto, ora hai: " + (int) guadagni);
				lblNewLabel_14.setText(Integer.toString((int) guadagni));
			} else {
				JOptionPane.showMessageDialog(frame, "Hai vinto, ora hai: " + guadagni);
				lblNewLabel_14.setText(Double.toString(guadagni));
			}
		} else {
			if (newMano == false) {
				guadagni = puntata;
				if (isInt(punti)) {
					JOptionPane.showMessageDialog(frame, "Hai paregiato non perdi i tuoi: " + (int) puntata + "$");
					lblNewLabel_14.setText(Integer.toString((int) guadagni));
					pareggio = true;
				} else {
					JOptionPane.showMessageDialog(frame, "Hai paregiato non perdi i tuoi: " + puntata + "$");
					lblNewLabel_14.setText(Double.toString(guadagni));
					pareggio = true;
				}
			} else {
				if (isInt(punti)) {
					JOptionPane.showMessageDialog(frame, "Hai paregiato non perdi i tuoi: " + (int) puntata + "$");
					lblNewLabel_14.setText(Integer.toString((int) guadagni));
					pareggio = true;
				} else {
					JOptionPane.showMessageDialog(frame, "Hai paregiato non perdi i tuoi: " + puntata + "$");
					lblNewLabel_14.setText(Double.toString(guadagni));
					pareggio = true;
				}
			}
		}
	}

	public void nuovoG() {
		if (turno <= nGiocatori.size()) {
			nGiocatori.get(turno - 1).setPuntata(puntata);
			nGiocatori.get(turno - 1).setPunti(punti);
			turno++;
			giocatoreC.setText("Giocatore 2");
		}

	}

	public void carta() {
		if (start == true) {
			if (carta == false) {
				int indice;
				if (punti < 21) {
					if (cont < 5) {

						indice = indice();

						ImageIcon carta = new ImageIcon(mazzo.get(indice).getFronte());
						mano.get(lable.size()).setIcon(carta);
						punti += valore(indice, isBanco);
						lable.add(1);
						cont = cont + 1;
					} else
						JOptionPane.showMessageDialog(frame, "Non puoi avere piu carte");
					if (punti > 21) {
						JOptionPane.showMessageDialog(frame, "Hai sballato");
						if (sballato == false) {
							guadagni -= puntata;
							if (isInt(guadagni)) {
								lblNewLabel_14.setText(Integer.toString((int) guadagni));
								sballato = true;
								carta = true;
							} else {
								lblNewLabel_14.setText(Double.toString(guadagni));
								sballato = true;
								carta = true;
							}
						}
					} else if (punti == 21) {
						JOptionPane.showMessageDialog(frame, "Hai fatto 21");
						carta = true;
					}

				} else if (punti == 21) {
					JOptionPane.showMessageDialog(frame, "Hai gia 21");
					carta = true;
				} else if (punti > 21) {
					JOptionPane.showMessageDialog(frame, "Hai sballato");
					if (sballato == false) {
						guadagni -= puntata;
						if (isInt(guadagni)) {
							lblNewLabel_14.setText(Integer.toString((int) guadagni));
							sballato = true;
							carta = true;
						} else {
							lblNewLabel_14.setText(Double.toString(guadagni));
							sballato = true;
							carta = true;
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(frame, "La mano e conclusa");
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Il gioco non e iniziato");
		}
	}

	public void newMano() {
		// puntata
		if (pareggio == false) {
			puntata = 0;
			punta = true;
			lbl.setText("0");
		}
		// punti
		punti = 0;
		puntiBanco = 0;
		cont = 2;
		// mano
		for (int i = 0; i < mano.size(); i++) {
			mano.get(i).setIcon(null);
		}
		// banco
		for (int i = 0; i < banco.size(); i++) {
			banco.get(i).setIcon(null);
		}
		// usciti
		usciti.clear();
		// start
		start = false;
		// uguale
		uguale = true;
		// lable
		lable.clear();
		lable.add(2);
		lable.add(1);
		// set gui
		textPane.setText("");
		if (newMano == false)
			newMano = true;
		// set boolean
		sballato = false;
		carta = false;
		pareggio = false;
	}

	public void continua() {
		int a = JOptionPane.showConfirmDialog(frame, "Vuoi continuare?");
		if (a == JOptionPane.YES_OPTION) {
			newMano();
		} else {
			clip.stop();
			Start.main(null);
			frame.dispose();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	private void initialize() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		cont = 2;
		mano = new ArrayList<>();
		banco = new ArrayList<>();
		mazzo = new ArrayList<>();
		usciti = new ArrayList<>();
		lable = new ArrayList<>();
		nGiocatori = new ArrayList<>();
		inMenu = BorderFactory.createLineBorder(Color.BLUE, 1);
		bordCard = BorderFactory.createLineBorder(Color.GREEN, 2);
		File file = new File("Music/multi.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-20.0f);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(new ImageIcon("Img/icona.png").getImage());
		frame.setResizable(false);
		frame.setTitle("Black Jack");
		frame.setJMenuBar(MenuBar);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// giocatori();
				JOptionPane.showMessageDialog(frame, "In sviluppo");
				clip.stop();
				frame.dispose();
				Start.main(null);

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// incremento list lable
		lable.add(2);
		lable.add(1);

		// dichiarazione mazzo
		mazzo.add(new Carta(2, "Img/2cuori.png", "Img/Dorso.png"));
		mazzo.add(new Carta(2, "Img/2Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(2, "Img/2Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(2, "Img/2Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(3, "Img/3Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(3, "Img/3Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(3, "Img/3Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(3, "Img/3Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(4, "Img/4Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(4, "Img/4Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(4, "Img/4Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(4, "Img/4Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(5, "Img/5Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(5, "Img/5Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(5, "Img/5Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(5, "Img/5Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(6, "Img/6Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(6, "Img/6Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(6, "Img/6Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(6, "Img/6Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(7, "Img/7Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(7, "Img/7Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(7, "Img/7Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(7, "Img/7Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(8, "Img/8Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(8, "Img/8Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(8, "Img/8Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(8, "Img/8Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(9, "Img/9Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(9, "Img/9Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(9, "Img/9Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(9, "Img/9Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/10Cuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/10Quadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/10Fiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/10Picche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/JCuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/JQuadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/JFiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/JPicche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/QCuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/QQuadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/QFiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/QPicche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/KCuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/KQuadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/KFiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(10, "Img/KPicche.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(11, "Img/ACuori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(11, "Img/AQuadri.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(11, "Img/AFiori.jpg", "Img/Dorso.png"));
		mazzo.add(new Carta(11, "Img/APicche.jpg", "Img/Dorso.png"));

		MenuBar = new JMenuBar();
		MenuBar.setBounds(0, 0, 850, 20);
		frame.getContentPane().add(MenuBar);

		// musica on off
		lblNewLabel_12 = new JLabel("   Musica: on");
		MenuBar.add(lblNewLabel_12);

		JLabel lblNewLabel_8 = new JLabel("   Menu");
		MenuBar.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("   Esci");
		MenuBar.add(lblNewLabel_9);
		lblNewLabel_9.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_9.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_9.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		lblNewLabel_8.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_8.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_8.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				clip.stop();
				Start.main(null);
				frame.dispose();
			}
		});
		lblNewLabel_12.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_12.setBorder(null);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_12.setBorder(inMenu);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (onOff == false) {
					clip.setMicrosecondPosition(0);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					lblNewLabel_12.setText("   Musica: on");
					onOff = true;
				} else {
					clip.stop();
					lblNewLabel_12.setText("   Musica: off");
					onOff = false;
				}
			}
		});

		textPane = new JTextPane();
		textPane.setBounds(12, 324, 89, 20);
		textPane.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					punta();
			}
		});
		frame.getContentPane().add(textPane);

		lbl = new JLabel();
		lbl.setText("0");
		lbl.setBounds(732, 298, 64, 20);
		frame.getContentPane().add(lbl);

		JButton btnNewButton_4 = new JButton("Punta");
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				punta();
			}
		});
		btnNewButton_4.setBounds(12, 354, 89, 23);
		frame.getContentPane().add(btnNewButton_4);

		JLabel lblNewLabel_3 = new JLabel("$:");
		lblNewLabel_3.setBounds(707, 297, 15, 23);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Banco");
		lblNewLabel_4.setBounds(397, 200, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Mano");
		lblNewLabel_5.setBounds(397, 240, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel imgLabel = new JLabel(new ImageIcon("Img/Dorso.png"));
		imgLabel.setBounds(05, 57, 105, 157);
		imgLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				start();
			}
		});
		frame.getContentPane().add(imgLabel);

		JLabel lblNewLabel = new JLabel("Mazzo");
		lblNewLabel.setBounds(39, 32, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Punta");
		lblNewLabel_2.setBounds(12, 299, 89, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(132, 268, 105, 157);
		lblNewLabel_1.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setBounds(362, 268, 105, 157);
		lblNewLabel_1_1.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel();
		lblNewLabel_1_1_1.setBounds(477, 268, 105, 157);
		lblNewLabel_1_1_1.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel();
		lblNewLabel_1_1_2.setBounds(247, 267, 105, 157);
		lblNewLabel_1_1_2.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_3 = new JLabel();
		lblNewLabel_1_1_3.setBounds(592, 267, 105, 157);
		lblNewLabel_1_1_3.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_1_1_3);

		mano.add(lblNewLabel_1);
		mano.add(lblNewLabel_1_1_2);
		mano.add(lblNewLabel_1_1);
		mano.add(lblNewLabel_1_1_1);
		mano.add(lblNewLabel_1_1_3);

		JLabel lblNewLabel_6 = new JLabel();
		lblNewLabel_6.setBounds(132, 31, 105, 157);
		lblNewLabel_6.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel();
		lblNewLabel_6_1.setBounds(247, 31, 105, 157);
		lblNewLabel_6_1.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_6_1);

		JLabel lblNewLabel_6_2 = new JLabel();
		lblNewLabel_6_2.setBounds(362, 32, 105, 157);
		lblNewLabel_6_2.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_6_2);

		JLabel lblNewLabel_6_3 = new JLabel();
		lblNewLabel_6_3.setBounds(477, 31, 105, 157);
		lblNewLabel_6_3.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_6_3);

		JLabel lblNewLabel_6_4 = new JLabel();
		lblNewLabel_6_4.setBounds(592, 31, 105, 157);
		lblNewLabel_6_4.setBorder(bordCard);
		frame.getContentPane().add(lblNewLabel_6_4);

		banco.add(lblNewLabel_6);
		banco.add(lblNewLabel_6_1);
		banco.add(lblNewLabel_6_2);
		banco.add(lblNewLabel_6_3);
		banco.add(lblNewLabel_6_4);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		btnNewButton.setBounds(707, 401, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Stai");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuovoG();

			}
		});
		btnNewButton_1.setBounds(707, 368, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Carta");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carta();
			}
		});
		btnNewButton_2.setBounds(707, 334, 89, 23);
		frame.getContentPane().add(btnNewButton_2);

		JLabel lblNewLabel_13 = new JLabel("Tot:");
		lblNewLabel_13.setBounds(707, 273, 35, 14);
		frame.getContentPane().add(lblNewLabel_13);

		lblNewLabel_14 = new JLabel("0");
		lblNewLabel_14.setBounds(732, 273, 64, 14);
		frame.getContentPane().add(lblNewLabel_14);

		giocatoreC = new JLabel("");
		giocatoreC.setBounds(707, 31, 89, 23);
		giocatoreC.setBorder(bordCard);
		frame.getContentPane().add(giocatoreC);
	}
}
