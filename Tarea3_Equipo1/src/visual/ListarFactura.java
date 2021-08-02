package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Almacen;
import logico.Factura;

import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListarFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdBuscar;
	public static String cod;;
	private static DefaultTableModel model;
	private static Object rows[];
	private JTable tableClientes;
	private JButton cancelButton;
	private JButton Ingresar;
	private Factura selectedFactura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarFactura dialog = new ListarFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarFactura() {
		setBounds(100, 100, 919, 611);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 83, 593, 436);
				panel.add(scrollPane);
				{
					String headers[] = {"Codigo", "Cliente", "Cant. De quesos", "Precio total"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					tableClientes = new JTable();
					tableClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableClientes.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							int index = -1;
							index = tableClientes.getSelectedRow();
							if(index != -1) {
								
								Ingresar.setEnabled(true);
								cod = (String)(model.getValueAt(index, 0));
								selectedFactura = Almacen.getInstance().bucarFacturaById(cod);
							}
						}

					});
					tableClientes.setModel(model);
					scrollPane.setViewportView(tableClientes);
				}
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setForeground(new Color(255, 255, 255));
			panel_1.setBounds(0, 13, 591, 62);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Listado de Facturas");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 35));
			lblNewLabel.setBounds(39, 13, 362, 36);
			panel_1.add(lblNewLabel);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(Color.ORANGE);
			panel_2.setBounds(0, 0, 26, 62);
			panel_1.add(panel_2);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setBounds(603, 13, 276, 506);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(0, 407, 276, 99);
			panel_4.setBackground(Color.ORANGE);
			panel_3.add(panel_4);
			
			JLabel lblListadoDeClientes = new JLabel("Buscar:");
			lblListadoDeClientes.setFont(new Font("Arial", Font.PLAIN, 40));
			lblListadoDeClientes.setBounds(12, 13, 362, 36);
			panel_3.add(lblListadoDeClientes);
			
			JLabel lblIngreseLaCdula = new JLabel("Ingrese el Id:");
			lblIngreseLaCdula.setFont(new Font("Arial", Font.PLAIN, 25));
			lblIngreseLaCdula.setBounds(12, 68, 362, 36);
			panel_3.add(lblIngreseLaCdula);
			
			txtIdBuscar = new JTextField();
			txtIdBuscar.setBackground(SystemColor.menu);
			txtIdBuscar.setBounds(12, 117, 236, 36);
			panel_3.add(txtIdBuscar);
			txtIdBuscar.setColumns(10);
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cod = txtIdBuscar.getText();
					Factura aux = Almacen.getInstance().bucarFacturaById(cod);
					if (aux == null) {
						JOptionPane.showMessageDialog(null, "Esta Factura no existe");
					}
					else {
						VerFactura facturaVer = new VerFactura(aux);
						facturaVer.setModal(true);
						facturaVer.setVisible(true);
						loadTable();
					}
					
				}
			});
			btnBuscar.setActionCommand("OK");
			btnBuscar.setBounds(12, 178, 81, 25);
			panel_3.add(btnBuscar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				Ingresar = new JButton("Ver Factura");
				Ingresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						VerFactura verFactura = new VerFactura(selectedFactura);
						verFactura.setModal(true);
						verFactura.setVisible(true);
						Ingresar.setEnabled(false);
						loadTable();
					}
				});
				Ingresar.setEnabled(false);
				Ingresar.setActionCommand("OK");
				buttonPane.add(Ingresar);
				getRootPane().setDefaultButton(Ingresar);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			loadTable();
		}
	}
	
	public static void loadTable() {
		float precio = 0;
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		for (Factura factura : Almacen.getInstance().getMisFacturas()) {
			rows[0] = factura.getId();
			rows[1] = factura.getMiCliente().getNombre();
			rows[2] = factura.getMisQuesos().size();
			precio = factura.precioFactura();
			precio = (float) (Math.round(precio * 100.0) / 100.0);
			rows[3] = precio;
			model.addRow(rows);
		}
	}
}