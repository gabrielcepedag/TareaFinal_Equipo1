package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Almacen;
import logico.Factura;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream almacen;
				FileOutputStream almacen2;
				ObjectInputStream almacenRead;
				ObjectOutputStream almacenWrite;
				try {
					almacen = new FileInputStream ("almacen.dat");
					almacenRead = new ObjectInputStream(almacen);
					Almacen temp = (Almacen)almacenRead.readObject();
					Almacen.setControl(temp);
					almacen.close();
					almacenRead.close();
				} catch (FileNotFoundException e) {
					try {
						almacen2 = new  FileOutputStream("almacen.dat");
						almacenWrite = new ObjectOutputStream(almacen2);
						almacenWrite.writeObject(Almacen.getInstance());
						almacen2.close();
						almacen2.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FileOutputStream almacen2;
				ObjectOutputStream almacenWrite;
				try {
					almacen2 = new  FileOutputStream("almacen.dat");
					almacenWrite = new ObjectOutputStream(almacen2);
					almacenWrite.writeObject(Almacen.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 920);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 13, 1336, 391);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(-14, 316, 1350, 75);
		panel_1.add(panel_2);
		panel_2.setBackground(Color.ORANGE);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/Queso logo.png")));
		lblNewLabel_1.setBounds(161, 13, 346, 274);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fabrica de Quesos ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 60));
		lblNewLabel_2.setBounds(519, 58, 659, 121);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblLaHabana = new JLabel("Complejo l\u00E1cteo la Habana");
		lblLaHabana.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblLaHabana.setBounds(519, 131, 728, 121);
		panel_1.add(lblLaHabana);
		
		JLabel lblNewLabel_3 = new JLabel("Sistema de Facturaci\u00F3n:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(519, 33, 233, 51);
		panel_1.add(lblNewLabel_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(SystemColor.controlHighlight);
		panel_3.setBounds(332, 452, 213, 123);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 229, 21);
		panel_3.add(panel_5);
		panel_5.setBackground(Color.ORANGE);
		
		JLabel label = new JLabel("");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ListarCliente listarCliente = new ListarCliente();
				listarCliente.setModal(true);
				listarCliente.setVisible(true);
			}
		});
		label.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/ListarCliente.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 23, 213, 100);
		panel_3.add(label);
		
		JLabel lblNewLabel = new JLabel("Listar Clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(371, 584, 135, 44);
		panel.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(null);
		panel_4.setBackground(SystemColor.controlHighlight);
		panel_4.setBounds(604, 452, 213, 123);
		panel.add(panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.ORANGE);
		panel_6.setBounds(0, 0, 229, 21);
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegQueso regQueso = new RegQueso(null);
				regQueso.setModal(true);
				regQueso.setVisible(true);
			}
		});
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 23, 213, 100);
		panel_4.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/NuevoQueso.png")));
		
		JLabel lblRegistrarQuesos = new JLabel("Registrar Quesos");
		lblRegistrarQuesos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRegistrarQuesos.setBounds(634, 584, 153, 44);
		panel.add(lblRegistrarQuesos);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(null);
		panel_7.setBackground(SystemColor.controlHighlight);
		panel_7.setBounds(878, 452, 213, 123);
		panel.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.ORANGE);
		panel_8.setBounds(0, 0, 229, 21);
		panel_7.add(panel_8);
		
		JLabel label_1 = new JLabel("");
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarQueso listarQueso = new ListarQueso();
				listarQueso.setModal(true);
				listarQueso.setVisible(true);
			}
		});
		label_1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/ListarQuesos.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 23, 213, 100);
		panel_7.add(label_1);
		
		JLabel lblListarQuesos = new JLabel("Listar Quesos");
		lblListarQuesos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListarQuesos.setBounds(921, 584, 127, 44);
		panel.add(lblListarQuesos);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(null);
		panel_9.setBackground(SystemColor.controlHighlight);
		panel_9.setBounds(466, 641, 213, 123);
		panel.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.ORANGE);
		panel_10.setBounds(0, 0, 229, 21);
		panel_9.add(panel_10);
		
		JLabel label_2 = new JLabel("");
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CrearFactura crearFactura = new CrearFactura();
				crearFactura.setModal(true);
				crearFactura.setVisible(true);
			}
		});
		label_2.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/CrearFactura.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 23, 213, 100);
		panel_9.add(label_2);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBorder(null);
		panel_11.setBackground(SystemColor.controlHighlight);
		panel_11.setBounds(738, 641, 213, 123);
		panel.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.ORANGE);
		panel_12.setBounds(0, 0, 229, 21);
		panel_11.add(panel_12);
		
		JLabel label_3 = new JLabel("");
		label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarFactura listarFactura = new ListarFactura();
				listarFactura.setModal(true);
				listarFactura.setVisible(true);
			}
		});
		label_3.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/BuscarFacturas.png")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 23, 213, 100);
		panel_11.add(label_3);
		
		JLabel lblCrearFactura = new JLabel("Crear Factura");
		lblCrearFactura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCrearFactura.setBounds(509, 773, 127, 44);
		panel.add(lblCrearFactura);
		
		JLabel lblListarbuscarFacturas = new JLabel("Listar/Buscar facturas");
		lblListarbuscarFacturas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListarbuscarFacturas.setBounds(743, 773, 202, 44);
		panel.add(lblListarbuscarFacturas);
	}

}
