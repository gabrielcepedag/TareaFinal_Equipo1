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
import logico.Cliente;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static String cedulaAux;
	private static DefaultTableModel model;
	private static Object rows[];
	private JTable tableClientes;
	private JButton cancelButton;
	private Cliente SelectedCliente;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarCliente dialog = new ListarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarCliente() {
		setBounds(100, 100, 826, 611);
		setTitle("Listar Cliente");
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
				scrollPane.setBounds(0, 83, 786, 436);
				panel.add(scrollPane);
				{
					String headers[] = {"Cedula", "Nombre", "Telefono", "Dirección"};
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
								
								String cedula = (String)(model.getValueAt(index, 0));
								SelectedCliente = Almacen.getInstance().buscarClienteByCedula(cedula);
								btnEliminar.setEnabled(true);
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
			panel_1.setBounds(0, 13, 786, 62);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Listado de Clientes");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 35));
			lblNewLabel.setBounds(39, 13, 362, 36);
			panel_1.add(lblNewLabel);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(Color.ORANGE);
			panel_2.setBounds(0, 0, 26, 62);
			panel_1.add(panel_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = Integer.valueOf(JOptionPane.showConfirmDialog(null, "Desea eliminar este cliente", "Eliminar Cliente", JOptionPane.YES_NO_OPTION));
						if(opcion == JOptionPane.YES_OPTION) {
							Almacen.getInstance().eliminarCliente(SelectedCliente);
							loadTable();
						}
						else {
							loadTable();
						}
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			loadTable();
		}
	}
	
	public static void loadTable() {
		
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		for(int i = 0; i < Almacen.getInstance().getMisClientes().size(); i++) {
			rows[0] = Almacen.getInstance().getMisClientes().get(i).getCedula();
			rows[1] = Almacen.getInstance().getMisClientes().get(i).getNombre();
			rows[2] = Almacen.getInstance().getMisClientes().get(i).getTelefono();
			rows[3] = Almacen.getInstance().getMisClientes().get(i).getDireccion();
			model.addRow(rows);
		}	
	}
}