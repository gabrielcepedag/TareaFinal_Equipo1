package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import logico.CilindricoHueco;
import logico.Almacen;
import logico.Queso;
import logico.Cilindrico;
import logico.Esfera;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RegQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JTextField txtNombre;
	private JSpinner spnPrecioBase;
	private JSpinner spnPrecioUnitario;
	private JRadioButton rdbtnEsferico;
	private JRadioButton rdbtnCilindrico;
	private JRadioButton rdbtnCilinHueco;
	private JSpinner spnRadioEsfera;
	private JSpinner spnRadioCilindro;
	private JSpinner spnLongitudCil;
	private JSpinner spnRadioInt;
	private JSpinner spnRadioExt;
	private JPanel panelCilindrico;
	private JPanel panelCilinHueco;
	private JPanel panelEsferico;
	private JSpinner spnLongitudCilHueco;
	private Queso selected;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegQueso dialog = new RegQueso(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegQueso(Queso queso) {
		selected = queso;
		if(selected == null) {
			setTitle("Registrar Queso");
		}else {
			setTitle("Modificar Información de Queso");
		}
		
		setBounds(100, 100, 420, 390);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JPanel panelInfoGeneral = new JPanel();
				panelInfoGeneral.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelInfoGeneral.setBounds(8, 11, 385, 102);
				panel.add(panelInfoGeneral);
				panelInfoGeneral.setLayout(null);
				{
					JLabel lblNombre = new JLabel("Nombre: ");
					lblNombre.setBounds(120, 28, 60, 14);
					panelInfoGeneral.add(lblNombre);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setBounds(175, 24, 200, 22);
					panelInfoGeneral.add(txtNombre);
					txtNombre.setColumns(10);
				}
				{
					JLabel lblPrecioBase = new JLabel("Precio Base:  RD$");
					lblPrecioBase.setBounds(10, 65, 115, 14);
					panelInfoGeneral.add(lblPrecioBase);
				}
				
				spnPrecioBase = new JSpinner();
				spnPrecioBase.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				spnPrecioBase.setBounds(113, 61, 70, 22);
				panelInfoGeneral.add(spnPrecioBase);
				{
					JLabel lblPrecioUnitario = new JLabel("Precio Unitario:  RD$");
					lblPrecioUnitario.setBounds(187, 65, 125, 14);
					panelInfoGeneral.add(lblPrecioUnitario);
				}
				{
					spnPrecioUnitario = new JSpinner();
					spnPrecioUnitario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
					spnPrecioUnitario.setBounds(305, 61, 70, 22);
					panelInfoGeneral.add(spnPrecioUnitario);
				}
				
				JLabel lblCodigo = new JLabel("C\u00F3digo: ");
				lblCodigo.setBounds(10, 28, 46, 14);
				panelInfoGeneral.add(lblCodigo);
				
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setEnabled(false);
				txtCodigo.setText("Q-"+Queso.numQueso);
				txtCodigo.setBounds(60, 25, 50, 22);
				panelInfoGeneral.add(txtCodigo);
				txtCodigo.setColumns(10);
			}
			{
				JSeparator separator = new JSeparator();
				separator.setBounds(10, 130, 380, 2);
				panel.add(separator);
			}
			{
				JPanel panelTipo = new JPanel();
				panelTipo.setBorder(new TitledBorder(null, "Tipo de Queso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelTipo.setBounds(8, 143, 385, 155);
				panel.add(panelTipo);
				panelTipo.setLayout(null);
				
				rdbtnEsferico = new JRadioButton("Esf\u00E9rico");
				rdbtnEsferico.setSelected(true);
				rdbtnEsferico.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnEsferico.setSelected(true);
						rdbtnCilindrico.setSelected(false);
						rdbtnCilinHueco.setSelected(false);
						
						panelEsferico.setVisible(true);
						panelCilindrico.setVisible(false);
						panelCilinHueco.setVisible(false);
					}
				});
				
				rdbtnEsferico.setBounds(14, 27, 109, 23);
				panelTipo.add(rdbtnEsferico);
				
				rdbtnCilindrico = new JRadioButton("Cil\u00EDndrico");
				rdbtnCilindrico.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnCilindrico.setSelected(true);
						rdbtnEsferico.setSelected(false);
						rdbtnCilinHueco.setSelected(false);
						
						panelCilindrico.setVisible(true);
						panelEsferico.setVisible(false);
						panelCilinHueco.setVisible(false);
					}
				});
				rdbtnCilindrico.setBounds(137, 27, 109, 23);
				panelTipo.add(rdbtnCilindrico);
				
				rdbtnCilinHueco = new JRadioButton("Cil\u00EDndrico Hueco");
				rdbtnCilinHueco.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnCilinHueco.setSelected(true);
						rdbtnEsferico.setSelected(false);
						rdbtnCilindrico.setSelected(false);
						
						panelCilinHueco.setVisible(true);						
						panelEsferico.setVisible(false);
						panelCilindrico.setVisible(false);
					}
				});
				rdbtnCilinHueco.setBounds(260, 27, 109, 23);
				panelTipo.add(rdbtnCilinHueco);
				{
					panelCilindrico = new JPanel();
					panelCilindrico.setVisible(false);
					panelCilindrico.setLayout(null);
					panelCilindrico.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panelCilindrico.setBounds(10, 70, 365, 70);
					panelTipo.add(panelCilindrico);
					{
						JLabel lblRadioCil = new JLabel("Radio Cilindro: ");
						lblRadioCil.setBounds(10, 28, 140, 14);
						panelCilindrico.add(lblRadioCil);
					}
					{
						spnRadioCilindro = new JSpinner();
						spnRadioCilindro.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spnRadioCilindro.setBounds(120, 24, 55, 22);
						panelCilindrico.add(spnRadioCilindro);
					}
					{
						JSeparator separator = new JSeparator();
						separator.setOrientation(SwingConstants.VERTICAL);
						separator.setBounds(182, 5, 1, 60);
						panelCilindrico.add(separator);
					}
					{
						JLabel lblLongitud = new JLabel("Longitud Cilindro: ");
						lblLongitud.setBounds(190, 28, 111, 14);
						panelCilindrico.add(lblLongitud);
					}
					{
						spnLongitudCil = new JSpinner();
						spnLongitudCil.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spnLongitudCil.setBounds(300, 25, 55, 22);
						panelCilindrico.add(spnLongitudCil);
					}
				}
				{
					panelCilinHueco = new JPanel();
					panelCilinHueco.setVisible(false);
					panelCilinHueco.setLayout(null);
					panelCilinHueco.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panelCilinHueco.setBounds(10, 70, 365, 70);
					panelTipo.add(panelCilinHueco);
					{
						JLabel lblRadioExt = new JLabel("Radio Cilindro Ext.: ");
						lblRadioExt.setBounds(10, 28, 140, 14);
						panelCilinHueco.add(lblRadioExt);
					}
					{
						spnRadioExt = new JSpinner();
						spnRadioExt.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spnRadioExt.setBounds(120, 24, 55, 22);
						panelCilinHueco.add(spnRadioExt);
					}
					{
						JLabel lblRadioInt = new JLabel("Radio Cilindro Int.: ");
						lblRadioInt.setBounds(190, 42, 140, 14);
						panelCilinHueco.add(lblRadioInt);
					}
					{
						spnRadioInt = new JSpinner();
						spnRadioInt.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spnRadioInt.setBounds(300, 38, 55, 22);
						panelCilinHueco.add(spnRadioInt);
					}
					{
						JSeparator separator = new JSeparator();
						separator.setOrientation(SwingConstants.VERTICAL);
						separator.setBounds(182, 5, 1, 60);
						panelCilinHueco.add(separator);
					}
					{
						JLabel lblLongitud2 = new JLabel("Longitud Cilindro: ");
						lblLongitud2.setBounds(190, 14, 111, 14);
						panelCilinHueco.add(lblLongitud2);
					}
					{
						spnLongitudCilHueco = new JSpinner();
						spnLongitudCilHueco.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spnLongitudCilHueco.setBounds(300, 8, 55, 22);
						panelCilinHueco.add(spnLongitudCilHueco);
					}
				}
				
				panelEsferico = new JPanel();
				panelEsferico.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panelEsferico.setBounds(10, 70, 365, 70);
				panelTipo.add(panelEsferico);
				panelEsferico.setLayout(null);
				
				JLabel lblRadioEsf = new JLabel("Radio de la Esfera: ");
				lblRadioEsf.setBounds(10, 28, 140, 14);
				panelEsferico.add(lblRadioEsf);
				
				spnRadioEsfera = new JSpinner();
				spnRadioEsfera.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnRadioEsfera.setBounds(120, 24, 55, 22);
				panelEsferico.add(spnRadioEsfera);
				
				JSeparator separator = new JSeparator();
				separator.setOrientation(SwingConstants.VERTICAL);
				separator.setBounds(182, 5, 1, 60);
				panelEsferico.add(separator);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				if(selected == null) {
					btnRegistrar = new JButton("Registrar");
				}else {
					btnRegistrar = new JButton("Guardar Cambios");
				}
				
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected == null) {
							Queso aux = null;
							String codigo = new String("Q-"+Queso.numQueso);
							String nombre = txtNombre.getText();
							float precioBase = new Float(spnPrecioBase.getValue().toString());
							float precioUnitario = new Float(spnPrecioUnitario.getValue().toString());
							
							if(rdbtnEsferico.isSelected()) {
								int radio = new Integer(spnRadioEsfera.getValue().toString());
								aux = new Esfera(codigo, nombre, precioBase, precioUnitario, radio);
							}
							if(rdbtnCilindrico.isSelected()) {
								int radio = new Integer(spnRadioCilindro.getValue().toString());
								int longitud = new Integer(spnLongitudCil.getValue().toString());
								aux = new Cilindrico(new String("Q-"+Queso.numQueso), nombre, precioBase, precioUnitario, radio, longitud);
							}
							if(rdbtnCilinHueco.isSelected()) {
								int radio = new Integer(spnRadioExt.getValue().toString());
								int radioInterior = new Integer(spnRadioInt.getValue().toString());
								int longitud = new Integer(spnLongitudCilHueco.getValue().toString());
								aux = new CilindricoHueco(new String("Q-"+Queso.numQueso), nombre,precioBase, precioUnitario, radio, longitud, radioInterior);
							}
							Almacen.getInstance().agregarQueso(aux);
							JOptionPane.showMessageDialog(null, "El queso ha sido registrado satisfactoriamente.", "Registro de Publicación", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}else {
							selected.setNombre(txtNombre.getText());
							selected.setPrecioBase(new Float(spnPrecioBase.getValue().toString()));
							selected.setPrecioUnitario(new Float(spnPrecioUnitario.getValue().toString()));
							int index = Almacen.getInstance().buscarIndexOfQueso(selected);
							Almacen.getInstance().modificarQueso(index, selected);
							ListarQueso.loadTable(0);
							dispose();
						}
					}	
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
		loadQueso();
	}
	
	private void clean() {
		txtCodigo.setText("Q-"+Queso.numQueso);
		txtNombre.setText(" ");
		spnPrecioBase.setValue(0);
		spnPrecioUnitario.setValue(0);
		spnLongitudCil.setValue(new Integer(1));
		spnLongitudCilHueco.setValue(new Integer(1));
		spnRadioCilindro.setValue(new Integer(1));
		spnRadioEsfera.setValue(new Integer(1));
		spnRadioExt.setValue(new Integer(1));
		spnRadioInt.setValue(new Integer(1));
	}
	
	private void loadQueso() {
		if(selected != null) {
			txtCodigo.setText(selected.getId());
			txtNombre.setText(selected.getNombre());
			spnPrecioBase.setValue(new Float(selected.getPrecioBase()));
			spnPrecioUnitario.setValue(new Float(selected.getPrecioUnitario()));
			
			if(selected instanceof Esfera) {
				rdbtnEsferico.setSelected(true);
				rdbtnCilindrico.setSelected(false);
				rdbtnCilinHueco.setSelected(false);
			
				panelEsferico.setVisible(true);
				panelCilindrico.setVisible(false);
				panelCilinHueco.setVisible(false);
				spnRadioEsfera.setValue(new Float(((Esfera) selected).getRadioEsfera()));
			}else if(selected instanceof Cilindrico && !(selected instanceof CilindricoHueco)) { 
				rdbtnEsferico.setSelected(false);
				rdbtnCilindrico.setSelected(true);
				rdbtnCilinHueco.setSelected(false);
				
				panelEsferico.setVisible(false);
				panelCilindrico.setVisible(true);
				panelCilinHueco.setVisible(false);
				spnRadioCilindro.setValue(new Float(((Cilindrico) selected).getRadioCilindrico()));
				spnLongitudCil.setValue(new Float(((Cilindrico) selected).getLongitud()));				
			}else {
				rdbtnEsferico.setSelected(false);
				rdbtnCilindrico.setSelected(false);
				rdbtnCilinHueco.setSelected(true);
				
				panelEsferico.setVisible(false);
				panelCilindrico.setVisible(false);
				panelCilinHueco.setVisible(true);
				spnLongitudCilHueco.setValue(new Float(((CilindricoHueco) selected).getLongitud()));				
				spnRadioExt.setValue(new Float(((CilindricoHueco) selected).getRadioCilindrico()));				
				spnRadioInt.setValue(new Float(((CilindricoHueco) selected).getRadioInterior()));				
			}
			rdbtnEsferico.setEnabled(false);
			rdbtnCilindrico.setEnabled(false);
			rdbtnCilinHueco.setEnabled(false);
		}
	}
}
