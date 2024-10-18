package Start;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import blackJack.Blackjeck;
import multiplay.Multigiocatore;

public class Start {

	private Border inMenu;
	private Boolean onOff = true;
	private JFrame frame;

	/**
	 * @param onOff
	 */
	public Start(Boolean onOff) {
		this.onOff = onOff;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Start window = new Start();
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
	public Start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	private void initialize() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		inMenu = BorderFactory.createLineBorder(Color.BLUE, 1);
		File file = new File("Music/menu.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-20.0f);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(new ImageIcon("Img/icona.png").getImage());
		frame.setResizable(false);
		frame.setTitle("Black Jack");

		JLabel lblNewLabel_1 = new JLabel("Esci");
		lblNewLabel_1.setBounds(197, 197, 46, 14);
		lblNewLabel_1.addMouseListener(new MouseListener() {

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
				lblNewLabel_1.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("info");
		lblNewLabel_2.setBounds(197, 147, 46, 14);
		lblNewLabel_2.addMouseListener(new MouseListener() {

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
				lblNewLabel_2.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_2.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(lblNewLabel_2, "Giochiamo ad una rivisitazione del Black Jack standard ");
			}
		});
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("inizia");
		lblNewLabel_3.setBounds(197, 97, 46, 14);
		lblNewLabel_3.addMouseListener(new MouseListener() {

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
				lblNewLabel_3.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_3.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				clip.stop();
				Blackjeck.main(null);
				frame.dispose();
			}
		});
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Black Jack");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 11, 414, 70);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Crediti");
		lblNewLabel_5.setBounds(197, 122, 46, 14);
		lblNewLabel_5.addMouseListener(new MouseListener() {

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
				lblNewLabel_5.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_5.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(lblNewLabel_2,
						"Creato dal gruppo Giacomo nero, leader: Davide Nino Longobardi");
			}
		});
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel = new JLabel("Musica: on");
		lblNewLabel.setBounds(357, 236, 67, 14);
		lblNewLabel.addMouseListener(new MouseListener() {

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
				lblNewLabel.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (onOff == false) {
					clip.setMicrosecondPosition(0);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					lblNewLabel.setText("Musica: on");
					onOff = true;
				} else {
					clip.stop();
					lblNewLabel.setText("Musica: off");
					onOff = false;
				}
			}
		});
		frame.getContentPane().add(lblNewLabel);

		JLabel multiplayer = new JLabel("Multiplayer");
		multiplayer.setBounds(197, 172, 68, 14);
		multiplayer.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				multiplayer.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				multiplayer.setBorder(inMenu);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				clip.stop();
				Multigiocatore.main(null);
				frame.dispose();
			}
		});
		frame.getContentPane().add(multiplayer);
	}
}
