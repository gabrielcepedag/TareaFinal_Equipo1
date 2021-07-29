package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Almacen;
import logico.Cilindrico;
import logico.CilindricoHueco;
import logico.Esfera;
import logico.Factura;
import logico.Queso;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VerFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private static DefaultTableModel model;
	private static Object fila[];

	private static Factura facturaSelected = null;

	public static void main(String[] args) {
		try {
			VerFactura dialog = new VerFactura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VerFactura(Factura factura) {
		facturaSelected = factura;
		setBounds(100, 100, 1054, 685);
		setLocationRelativeTo(null);
		setTitle("Factura");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(12, 13, 1012, 577);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(12, 13, 364, 415);
				panel.add(panel_1);
				panel_1.setBackground(Color.ORANGE);
				panel_1.setLayout(null);
				{
					JLabel lblNombre = new JLabel("Nombre: " + facturaSelected.getMiCliente().getNombre());
					lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
					lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblNombre.setBounds(6, 189, 309, 61);
					panel_1.add(lblNombre);
				}
				{
					JLabel label = new JLabel("Telefono: " + facturaSelected.getMiCliente().getTelefono());
					label.setHorizontalAlignment(SwingConstants.LEFT);
					label.setFont(new Font("Tahoma", Font.PLAIN, 20));
					label.setBounds(6, 233, 309, 61);
					panel_1.add(label);
				}
				{
					JLabel label = new JLabel("<html>Direcci\u00F3n: " + facturaSelected.getMiCliente().getDireccion() + "<html>");
					label.setHorizontalAlignment(SwingConstants.LEFT);
					label.setFont(new Font("Tahoma", Font.PLAIN, 20));
					label.setBounds(6, 287, 309, 51);
					panel_1.add(label);
				}
				{
					JLabel lblFacturaNo = new JLabel("Factura no.");
					lblFacturaNo.setBounds(6, 13, 362, 36);
					panel_1.add(lblFacturaNo);
					lblFacturaNo.setFont(new Font("Tahoma", Font.PLAIN, 35));
				}
				{
					JPanel panel_1_1 = new JPanel();
					panel_1_1.setBounds(6, 62, 252, 43);
					panel_1.add(panel_1_1);
					panel_1_1.setBorder(null);
					panel_1_1.setBackground(Color.WHITE);
					panel_1_1.setLayout(null);
					{
						JLabel label = new JLabel(facturaSelected.getId());
						label.setBounds(12, 10, 187, 25);
						label.setHorizontalAlignment(SwingConstants.LEFT);
						label.setFont(new Font("Tahoma", Font.PLAIN, 20));
						panel_1_1.add(label);
					}
				}
				{
					JLabel lblCliente = new JLabel("Cliente:");
					lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 35));
					lblCliente.setBounds(6, 140, 225, 36);
					panel_1.add(lblCliente);
				}
			}
			{
				JLabel lblQuesosComprados = new JLabel("Quesos Comprados:");
				lblQuesosComprados.setFont(new Font("Tahoma", Font.PLAIN, 35));
				lblQuesosComprados.setBounds(398, 30, 362, 36);
				panel.add(lblQuesosComprados);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(null);
				panel_1.setBackground(SystemColor.control);
				panel_1.setBounds(12, 439, 988, 125);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblPrecioTotalDe = new JLabel("Precio Total: " + facturaSelected.precioFactura());
					lblPrecioTotalDe.setHorizontalAlignment(SwingConstants.LEFT);
					lblPrecioTotalDe.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblPrecioTotalDe.setBounds(28, 29, 323, 61);
					panel_1.add(lblPrecioTotalDe);
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(null);
					panel_2.setBackground(Color.WHITE);
					panel_2.setBounds(365, 0, 16, 137);
					panel_1.add(panel_2);
				}
				{
					JPanel panel_1_1 = new JPanel();
					panel_1_1.setBounds(0, 0, 16, 137);
					panel_1.add(panel_1_1);
					panel_1_1.setBorder(null);
					panel_1_1.setBackground(Color.ORANGE);
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(null);
					panel_2.setBackground(Color.ORANGE);
					panel_2.setBounds(379, 0, 16, 137);
					panel_1.add(panel_2);
				}
				{
					JLabel lblPrecioQuesoEsferico = new JLabel("Precio del queso Esferico mas volum\u00E9trico: " + Almacen.getInstance().precioQuesoMayorVolumenFactura(facturaSelected.getId()));
					lblPrecioQuesoEsferico.setHorizontalAlignment(SwingConstants.LEFT);
					lblPrecioQuesoEsferico.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblPrecioQuesoEsferico.setBounds(407, 29, 519, 61);
					panel_1.add(lblPrecioQuesoEsferico);
				}
			}
			{
				scrollPane = new JScrollPane();
				scrollPane.setBounds(398, 79, 602, 347);
				panel.add(scrollPane);
				{
					String headers[] = {"Id", "Precio Total", "Volumen", "Tipo"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table = new JTable();
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton Cancelar = new JButton("Cancelar");
				Cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				Cancelar.setActionCommand("Cancel");
				buttonPane.add(Cancelar);
			}
		}
		loadTable();
	}
	public static void loadTable() {
		if(facturaSelected != null) {
			model.setRowCount(0);
			fila = new Object[model.getColumnCount()];
			for (Queso queso: facturaSelected.getMisQuesos()) {
				fila[0] = queso.getId();
				fila[1] = queso.precioTotal();
				fila[2] = queso.volumen();
				if (queso instanceof Esfera) {
					fila[3] = "Esférico";
				}
				if (queso instanceof Cilindrico) {
					fila[3] = "Cilíndrico";
				}
				if (queso instanceof CilindricoHueco) {
					fila[3] = "Cilíndrico Hueco";
				}
				model.addRow(fila);
			}
		}
	}
}
