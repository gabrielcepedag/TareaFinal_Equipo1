package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import logico.Cliente;
import logico.Almacen;
import logico.Factura;
import logico.Queso;
import logico.Esfera;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class CrearFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JButton btnBuscar;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private DefaultListModel<String> listModelDisponibles;
	private DefaultListModel<String> listModelComprados;
	private JList<String> listDisponibles;
	private JList<String> listComprados;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JButton btnFinalizar;
	private JTextField txtCodigo;
	private JTextField txtTotal;
	private Cliente auxCliente = null;
	private Factura factura = null;
	private ArrayList<Queso> listQuesosComprados = new ArrayList<Queso>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearFactura dialog = new CrearFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearFactura() {
		
		Esfera queso = new Esfera("0005","Gouda",5,10,2);
		Almacen.getInstance().agregarQueso(queso);
		setTitle("Punto de Venta - Complejo L\u00E1cteo La Habana");
		setBounds(100, 100, 500, 615);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(null);
				{
					JPanel panelInfoPersonal = new JPanel();
					panelInfoPersonal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panelInfoPersonal.setBounds(13, 65, 450, 90);
					panel_1.add(panelInfoPersonal);
					panelInfoPersonal.setLayout(null);
					{
						JLabel lblNombre = new JLabel("Nombre: ");
						lblNombre.setBounds(10, 15, 60, 14);
						panelInfoPersonal.add(lblNombre);
					}
					{
						txtNombre = new JTextField();
						txtNombre.setEnabled(false);
						txtNombre.setBounds(75, 11, 190, 22);
						panelInfoPersonal.add(txtNombre);
						txtNombre.setColumns(10);
					}
					{
						JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
						lblTelefono.setBounds(275, 15, 60, 14);
						panelInfoPersonal.add(lblTelefono);
					}
					{
						txtTelefono = new JTextField();
						txtTelefono.setEnabled(false);
						txtTelefono.setColumns(10);
						txtTelefono.setBounds(335, 11, 103, 22);
						panelInfoPersonal.add(txtTelefono);
					}
					{
						JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
						lblDireccion.setBounds(10, 55, 60, 14);
						panelInfoPersonal.add(lblDireccion);
					}
					{
						txtDireccion = new JTextField();
						txtDireccion.setEnabled(false);
						txtDireccion.setColumns(10);
						txtDireccion.setBounds(75, 51, 362, 22);
						panelInfoPersonal.add(txtDireccion);
					}
				}
				{
					JLabel lblCedula = new JLabel("Ingrese su c\u00E9dula: ");
					lblCedula.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblCedula.setBounds(20, 15, 150, 16);
					panel_1.add(lblCedula);
				}
				
				txtCedula = new JTextField();
				txtCedula.setBounds(140, 13, 180, 22);
				panel_1.add(txtCedula);
				txtCedula.setColumns(10);
				
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						auxCliente = Almacen.getInstance().buscarClienteByCedula(txtCedula.getText());
						cleanCliente();
						listQuesosComprados.clear();
						
						if(auxCliente != null) {
							loadCliente(auxCliente);
							txtNombre.setEnabled(false);
							txtDireccion.setEnabled(false);
							txtTelefono.setEnabled(false);
						}else {
							txtNombre.setEnabled(true);
							txtDireccion.setEnabled(true);
							txtTelefono.setEnabled(true);
						}
					}
				});
				btnBuscar.setEnabled(true);
				btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnBuscar.setBounds(341, 11, 120, 26);
				panel_1.add(btnBuscar);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(13, 50, 450, 2);
				panel_1.add(separator);
				{
					JSeparator separator1 = new JSeparator();
					separator1.setBounds(13, 173, 450, 2);
					panel_1.add(separator1);
				}
				{
					JLabel lblDisponibles = new JLabel("Quesos Disponibles:");
					lblDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 12));
					lblDisponibles.setBounds(13, 210, 140, 16);
					panel_1.add(lblDisponibles);
				}
				
				JScrollPane scrollPaneDisponibles = new JScrollPane();
				scrollPaneDisponibles.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPaneDisponibles.setBounds(13, 235, 175, 200);
				panel_1.add(scrollPaneDisponibles);
				
				listDisponibles = new JList<String>();
				listDisponibles.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						int index = -1;
						index = listDisponibles.getSelectedIndex();
						if (index != -1) {
							btnDerecha.setEnabled(true);
						}
					}
				});
				listModelDisponibles = new DefaultListModel<String>();
				listDisponibles.setModel(listModelDisponibles);
				listDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPaneDisponibles.setViewportView(listDisponibles);
				
				JScrollPane scrollPaneComprados = new JScrollPane();
				scrollPaneComprados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPaneComprados.setBounds(286, 235, 175, 200);
				panel_1.add(scrollPaneComprados);
				
				listComprados = new JList<String>();
				listComprados.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int index = -1;
						index = listComprados.getSelectedIndex();
						if (index != -1) {
							btnIzquierda.setEnabled(true);
						}
					}
				});
				listModelComprados = new DefaultListModel<String>();
				listComprados.setModel(listModelComprados);
				listComprados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPaneComprados.setViewportView(listComprados);
				
				JLabel lblComprados = new JLabel("Carrito de Compras:");
				lblComprados.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblComprados.setBounds(283, 210, 140, 16);
				panel_1.add(lblComprados);
				
				btnDerecha = new JButton(">>>");
				btnDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!txtCedula.getText().isEmpty()) {
						String aux = listDisponibles.getSelectedValue().toString();
						listModelComprados.addElement(aux);
						listModelDisponibles.remove(listDisponibles.getSelectedIndex());
						btnDerecha.setEnabled(false);
						listQuesosComprados.add(Almacen.getInstance().buscarQuesoById(aux.substring(0, aux.indexOf('|')-1)));
						
						System.out.println("PRECIO QUESO: "+Almacen.getInstance().buscarQuesoById(aux.substring(0, aux.indexOf('|')-1)).precioTotal());
						txtTotal.setText("RD$ "+calcTotal());					
					}else {
							JOptionPane.showMessageDialog(null, "Debe especificar su cédula para realizar el pedido.", "Información", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnDerecha.setEnabled(false);
				btnDerecha.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnDerecha.setBounds(205, 290, 65, 30);
				panel_1.add(btnDerecha);
				
				JLabel lblComprar = new JLabel("Mover al Carrito");
				lblComprar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblComprar.setBounds(200, 275, 85, 14);
				panel_1.add(lblComprar);
				
				btnIzquierda = new JButton("<<<");
				btnIzquierda.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String aux = listComprados.getSelectedValue().toString();
						listModelDisponibles.addElement(aux);
						listModelComprados.remove(listComprados.getSelectedIndex());
						listQuesosComprados.remove(Almacen.getInstance().buscarQuesoById(aux.substring(0, aux.indexOf('|')-1)));
						btnIzquierda.setEnabled(false);
						
						txtTotal.setText("RD$ "+calcTotal());					
					}
				});
				btnIzquierda.setEnabled(false);
				btnIzquierda.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnIzquierda.setBounds(205, 335, 65, 30);
				panel_1.add(btnIzquierda);
				
				JLabel lblSacar = new JLabel("Sacar del Carrito");
				lblSacar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblSacar.setBounds(200, 370, 85, 14);
				panel_1.add(lblSacar);
				{
					JLabel lblEigeQuesos = new JLabel("Eliga los quesos que desea comprar");
					lblEigeQuesos.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblEigeQuesos.setBounds(13, 180, 250, 14);
					panel_1.add(lblEigeQuesos);
				}
				{
					JSeparator separator2 = new JSeparator();
					separator2.setBounds(13, 446, 450, 2);
					panel_1.add(separator2);
				}
				{
					JPanel buttonPane = new JPanel();
					buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
					getContentPane().add(buttonPane, BorderLayout.SOUTH);
					{
						btnFinalizar = new JButton("Finalizar Pedido");
						btnFinalizar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!listModelComprados.isEmpty()) {
									if(auxCliente == null) {
										auxCliente = new Cliente(txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText());
										Almacen.getInstance().agregarCliente(auxCliente);
									}
									factura = new Factura(new String("F-"+Factura.numFactura), auxCliente);
									for (Queso queso : listQuesosComprados) {
										factura.agregarQueso(queso);
										queso.setVendido(true);
									}
									Almacen.getInstance().agregarFactura(factura);
									Date date = new Date();
									File archivo = new File ("Facturas/Factura-"+new SimpleDateFormat("dd-MM-yyyy").format(date));
									FileWriter escritor;
									String info = "";
									info = "Almacen de Quesos LA HABANA             " + factura.getId()+ " | " +new SimpleDateFormat("dd-MM-yyyy").format(date)+ "\n"
											+ ".....................................................................\n"
										    + "Cliente:\n\n"
										    + "Nombre: " + factura.getMiCliente().getNombre() + "\n"
										    + "Cedula: " + factura.getMiCliente().getCedula() + "\n"
										    + "Telefono: " + factura.getMiCliente().getDireccion() + "\n"
											+ "Dirección: " + factura.getMiCliente().getTelefono() + "\n\n"
											+ "Productos:\n";
									for (int i = 0; i < listQuesosComprados.size(); i++) {
										info = info + listQuesosComprados.get(i).getId()+" | "+listQuesosComprados.get(i).getNombre()+"\n";
									}
									info = info + "\n.....................................................................\n"
											+ "Precio Total: " + factura.precioFactura() +"\n";
									
									try {
									escritor = new FileWriter(archivo);
									// Escribe el archivo con la informacion
							        for (int i=0; i<info.length(); i++)
							            escritor.write(info.charAt(i));
							            escritor.close();
									} catch (IOException Ioe) {
										Ioe.printStackTrace();
									}
									
									try {
										Socket socket = new Socket("127.0.0.1",9000);
										DataOutputStream envio = new DataOutputStream(socket.getOutputStream());
										envio.writeUTF(info);
										envio.flush();
										socket.close();
										
									} catch (UnknownHostException e1) {
										System.out.println("No se encontró la IP proporcionada ");
										e1.printStackTrace();
									} catch (IOException ioe) {
										System.out.println("Conexión rechazada "+ioe);
										System.exit(1);
									}
									
									JOptionPane.showMessageDialog(null, "¡Su pedido ha sido realizado satisfactoriamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
									cleanCliente();
									listQuesosComprados.clear();
									
								}else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un (1) queso a comprar.", "Información", JOptionPane.WARNING_MESSAGE);
								}		
							}
						});
						btnFinalizar.setActionCommand("OK");
						buttonPane.add(btnFinalizar);
						getRootPane().setDefaultButton(btnFinalizar);
					}
					{
						JButton btnCancelar = new JButton("Cancelar");
						btnCancelar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								dispose();
							}
						});
						btnCancelar.setActionCommand("Cancel");
						buttonPane.add(btnCancelar);
					}
				}
				{
					JPanel panelDetallesPedido = new JPanel();
					panelDetallesPedido.setBorder(new TitledBorder(null, "Detalles del Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					panelDetallesPedido.setBounds(13, 455, 450, 70);
					panel_1.add(panelDetallesPedido);
					panelDetallesPedido.setLayout(null);
					{
						JLabel lblCodigo = new JLabel("C\u00F3digo del Pedido: ");
						lblCodigo.setBounds(10, 28, 110, 14);
						panelDetallesPedido.add(lblCodigo);
					}
					{
						txtCodigo = new JTextField();
						txtCodigo.setText("F-"+Factura.numFactura);
						txtCodigo.setEnabled(false);
						txtCodigo.setBounds(120, 25, 86, 21);
						panelDetallesPedido.add(txtCodigo);
						txtCodigo.setColumns(10);
					}
					{
						JLabel lblTotal = new JLabel("Total del Pedido: ");
						lblTotal.setBounds(220, 28, 100, 14);
						panelDetallesPedido.add(lblTotal);
					}
					{
						txtTotal = new JTextField();
						txtTotal.setText("RD$ 0.0");
						txtTotal.setEnabled(false);
						txtTotal.setBounds(320, 25, 120, 21);
						panelDetallesPedido.add(txtTotal);
						txtTotal.setColumns(10);
					}
				}
			}
		}		
		loadQuesosDisponibles();
	}
	
	private void loadCliente(Cliente cliente) {
		txtNombre.setText(cliente.getNombre());
		txtDireccion.setText(cliente.getDireccion());
		txtTelefono.setText(cliente.getTelefono());
	}
	
	private void cleanCliente() {
		txtNombre.setText(" ");
		txtDireccion.setText(" ");
		txtTelefono.setText(" ");
		txtCodigo.setText("F-"+Factura.numFactura);
		txtTotal.setText(" ");
		loadQuesosDisponibles();
	}
	
	private void loadQuesosDisponibles() {
		listModelDisponibles.removeAllElements();
		listModelComprados.removeAllElements();
		
		for (Queso queso : Almacen.getInstance().getMisQuesos()) {
			String aux = new String(queso.getId()+" | "+queso.getNombre());
			
			if(queso.getVendido() == false) {
				listModelDisponibles.addElement(aux);
			}			
		}
	}
	
	private float calcTotal() {
		float total = 0;
		if (!listQuesosComprados.isEmpty()) {		
			for (Queso queso : listQuesosComprados) {
				total += queso.precioTotal();
			}
		}
		total = (float) (Math.round(total * 100.0) / 100.0);
				
		return total;
	}
}
