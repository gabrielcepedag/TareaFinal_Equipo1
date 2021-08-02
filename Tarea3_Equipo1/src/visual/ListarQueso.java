package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Almacen;
import logico.Cilindrico;
import logico.CilindricoHueco;
import logico.Esfera;
import logico.Queso;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class ListarQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private static DefaultTableModel model;
	private static Object fila[];
	private JButton modificar;
	private JButton eliminar;
	private JButton cancelar;
	private JTable table;
	private Queso selected = null;
	private JComboBox<String> comboBox;
	private JPanel panel;
	private JLabel lblListadoDeQuesos;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblDatos;
	private JPanel panel_4;
	private JLabel lblNewLabel;
	private JLabel lblQuesosCilndricos;
	private JLabel lblQuesosCilndricosHuecos;

	public static void main(String[] args) {
		try {
			ListarQueso dialog = new ListarQueso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarQueso() {
		setBounds(100, 100, 949, 559);
		setTitle("Listar Quesos");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		{
			JLabel label = new JLabel("Tipo de Queso:");
			label.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label.setBounds(12, 95, 143, 24);
			panel_1.add(label);
		}
		{
			comboBox = new JComboBox<String>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selection = comboBox.getSelectedIndex();
					loadTable(selection);
				}
			});
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"<Todos>", "Quesos Esfericos", "Quesos Cil\u00EDndricos", "Quesos Cil\u00EDndricos Huecos"}));
			comboBox.setBounds(154, 96, 215, 24);
			panel_1.add(comboBox);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 133, 628, 321);
			panel_1.add(scrollPane);
			{
				String headers[] = {"Id", "Precio Base", "Precio Unitario", "Precio Total", "Volumen", "Tipo"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				loadTable(0);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int index = -1;
						index = table.getSelectedRow();
						if(index != -1) {
							modificar.setEnabled(true);
							eliminar.setEnabled(true);
							String id = (String)(model.getValueAt(index, 0));
							selected = Almacen.getInstance().buscarQuesoById(id);				
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			panel = new JPanel();
			panel.setLayout(null);
			panel.setForeground(Color.WHITE);
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 13, 915, 62);
			panel_1.add(panel);
			{
				lblListadoDeQuesos = new JLabel("Listado de Quesos");
				lblListadoDeQuesos.setFont(new Font("Arial", Font.PLAIN, 35));
				lblListadoDeQuesos.setBounds(39, 13, 362, 36);
				panel.add(lblListadoDeQuesos);
			}
			{
				panel_2 = new JPanel();
				panel_2.setBackground(Color.ORANGE);
				panel_2.setBounds(0, 0, 26, 62);
				panel.add(panel_2);
			}
		}
		{
			panel_3 = new JPanel();
			panel_3.setBounds(661, 95, 254, 359);
			panel_1.add(panel_3);
			panel_3.setLayout(null);
			panel_3.setForeground(Color.WHITE);
			panel_3.setBackground(Color.WHITE);
			{
				panel_4 = new JPanel();
				panel_4.setBackground(Color.ORANGE);
				panel_4.setBounds(0, 0, 254, 62);
				panel_3.add(panel_4);
				panel_4.setLayout(null);
				{
					lblDatos = new JLabel("Datos Extras");
					lblDatos.setBounds(23, 13, 219, 41);
					panel_4.add(lblDatos);
					lblDatos.setFont(new Font("Arial", Font.PLAIN, 35));
				}
			}
			{
				lblNewLabel = new JLabel("Quesos Esfericos:  " + Almacen.getInstance().cantQuesosRedondos());
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel.setBounds(10, 85, 230, 46);
				panel_3.add(lblNewLabel);
			}
			{
				lblQuesosCilndricos = new JLabel("Quesos Cil\u00EDndricos:  " + Almacen.getInstance().cantQuesosCilindricos());
				lblQuesosCilndricos.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblQuesosCilndricos.setBounds(10, 131, 230, 46);
				panel_3.add(lblQuesosCilndricos);
			}
			{
				lblQuesosCilndricosHuecos = new JLabel("Quesos Cil\u00EDndricos Huecos:  " + Almacen.getInstance().cantQuesosCilindricosHueco());
				lblQuesosCilndricosHuecos.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblQuesosCilndricosHuecos.setBounds(10, 175, 230, 46);
				panel_3.add(lblQuesosCilndricosHuecos);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				modificar = new JButton("Modificar");
				modificar.setEnabled(false);
				modificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RegQueso regQueso = new RegQueso(selected);
						regQueso.setModal(true);
						regQueso.setVisible(true);
					}
				});
				modificar.setActionCommand("OK");
				buttonPane.add(modificar);
				getRootPane().setDefaultButton(modificar);
			}
			{  
				eliminar = new JButton("Eliminar");
				eliminar.setEnabled(false);
				eliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Almacen.getInstance().eliminarQueso(selected);
						loadTable(comboBox.getSelectedIndex());
					}
				});
				eliminar.setActionCommand("Cancel");
				buttonPane.add(eliminar);
			}
			{
				cancelar =  new JButton("Cancelar");
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelar.setActionCommand("Cancel");
				buttonPane.add(cancelar);
			}
		}
	}
	
	public static void loadTable(int selection) {
		float precio = 0;
		
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		switch (selection) {
		case 0:
			for (Queso aux : Almacen.getInstance().getMisQuesos()) {
				if (aux.getVendido() == false) {
					fila[0] = aux.getId();
					fila[1] = aux.getPrecioBase();
					fila[2] = aux.getPrecioUnitario();
					precio = aux.precioTotal();
					precio = (float) (Math.round(precio * 100.0) / 100.0);
					fila[3] = precio;
					precio = aux.volumen();
					precio = (float) (Math.round(precio * 100.0) / 100.0);
					fila[4] = precio;
					if(aux instanceof Esfera) {				
						fila[5] = "Esferico"; 
					}
					if(aux instanceof Cilindrico) {
						fila[5] = "Cilíndrico"; 
					}
					if(aux instanceof CilindricoHueco) {
						fila[5] = "Cilíndtico Hueco"; 
					}
					model.addRow(fila);
				}
			}
			break;
		case 1:
			for (Queso aux : Almacen.getInstance().getMisQuesos()) {
				if(aux instanceof Esfera) {
					if (aux.getVendido() == false) {
						fila[0] = aux.getId();
						fila[1] = aux.getPrecioBase();
						fila[2] = aux.getPrecioUnitario();
						precio = aux.precioTotal();
						precio = (float) (Math.round(precio * 100.0) / 100.0);
						fila[3] = precio;
						precio = aux.volumen();
						precio = (float) (Math.round(precio * 100.0) / 100.0);
						fila[4] = precio;
						fila[5] = "Esférico";
						model.addRow(fila);
					}
				}
			}
			break;
			
		case 2:
			for (Queso aux : Almacen.getInstance().getMisQuesos()) {
				if(aux instanceof Cilindrico) {
					if (aux.getVendido() == false) {
						fila[0] = aux.getId();
						fila[1] = aux.getPrecioBase();
						fila[2] = aux.getPrecioUnitario();
						precio = aux.precioTotal();
						precio = (float) (Math.round(precio * 100.0) / 100.0);
						fila[3] = precio;
						precio = aux.volumen();
						precio = (float) (Math.round(precio * 100.0) / 100.0);
						fila[4] = precio;
						fila[5] = "Cilíndrico";
						model.addRow(fila);
					}
				}
			}
			break;
			
		case 3:
			for (Queso aux : Almacen.getInstance().getMisQuesos()) {
				if(aux instanceof CilindricoHueco) {
					if (aux.getVendido() == false) {
						fila[0] = aux.getId();
						fila[1] = aux.getPrecioBase();
						fila[2] = aux.getPrecioUnitario();
						precio = aux.precioTotal();
						precio = (float) (Math.round(precio * 100.0) / 100.0);
						fila[3] = precio;
						precio = aux.volumen();
						precio = (float) (Math.round(precio * 100.0) / 100.0);
						fila[4] = precio;
						fila[5] = "Cilíndrico Hueco";
						model.addRow(fila);
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
