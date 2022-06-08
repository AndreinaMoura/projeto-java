package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controle.ProcessaVenda;
import dao.ProdutoDAO;
import controle.ProdutoProcess;
import controle.UsuarioProcessa;
import modelo.Venda;
import modelo.Produto;

public class VendasForm extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroll;
	private JButton btAdd, btDel, btCancelar, btSalvar;
	private JLabel lbNum, lbData, lbHora, lbQuantidade, lbProduto, lbFundo, text, lbTotalItens, lbTotalDinheiro,
			lbImagem;
	private int numero;
	private String hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	private String agora = new SimpleDateFormat("hh:mm").format(new Date());
	private JTextField tfNum, tfData, tfHora, tfQuantidade, tfTotalItens, tfTotalDinheiro;
	private JComboBox<String> cbProduto;
	private String imgIco = ".\\assets\\2.png";
	private String[] fundo = { ".\\assets\\2.png" };
	private ImageIcon icon;
	private ImageIcon img;
	private Image image;
	private Image newImg;
	private Venda compra;
	private Produto produto;

	private final Locale BRASIL = new Locale("pt", "BR");
	private DecimalFormat df = new DecimalFormat("#.00");

	VendasForm() {
		setTitle("Cadastro de Vendas");
		setBounds(201, 160, 697, 410);
		setIconImage(new ImageIcon(imgIco).getImage());
		panel = new JPanel();
		panel.setBackground(new Color(255, 233, 213));
		setContentPane(panel);
		setLayout(null);
		numero = ProcessaVenda.getAutoNumero();

		text = new JLabel("Mercado dois irmãos");
		text.setBounds(100, 15, 400, 30);
		text.setFont(new Font("OpenSans", Font.CENTER_BASELINE, 25));
		text.setForeground(new Color(180, 112, 54));
		panel.add(text);

		tfNum = new JTextField();
		tfNum.setText(String.format("%d", numero));
		tfNum.setEnabled(false);
		tfNum.setBounds(10, 70, 50, 25);
		panel.add(tfNum);

		lbNum = new JLabel("Código");
		lbNum.setBounds(10, 50, 50, 25);
		panel.add(lbNum);

		tfData = new JTextField(hoje);
		tfData.setEnabled(false);
		tfData.setBounds(60, 70, 80, 25);
		panel.add(tfData);

		lbData = new JLabel("Data");
		lbData.setBounds(60, 50, 80, 25);
		panel.add(lbData);

		tfHora = new JTextField(agora);
		tfHora.setEnabled(false);
		tfHora.setBounds(140, 70, 80, 25);
		panel.add(tfHora);

		lbHora = new JLabel("Hora");
		lbHora.setBounds(140, 50, 80, 25);
		panel.add(lbHora);

		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(430, 70, 70, 25);
		panel.add(tfQuantidade);

		lbQuantidade = new JLabel("Quantidade");
		lbQuantidade.setBounds(430, 50, 70, 25);
		panel.add(lbQuantidade);

		lbProduto = new JLabel("Quantidade");
		lbProduto.setBounds(220, 50, 70, 25);
		panel.add(lbProduto);

		cbProduto = new JComboBox<String>();
		cbProduto.setBounds(220, 70, 210, 25);
		panel.add(cbProduto);
		for (Produto p : ProdutoProcess.getProdutos()) {
			cbProduto.addItem(p.getCodProduto() + " " + p.getNomeProduto() + " " + p.getPrecoUnitario());
		}
		cbProduto.addActionListener(this);

		produto = ProdutoProcess.getProduto(Integer.parseInt(cbProduto.getSelectedItem().toString().split("")[0]));
		ProdutoProcess.getPd();
		img = new ImageIcon(ProdutoDAO.getImgPath(produto));
		image = img.getImage();
		newImg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);

		lbImagem = new JLabel();
		lbImagem.setIcon(img);
		lbImagem.setBounds(568, 0, 100, 100);
		panel.add(lbImagem);

		btAdd = new JButton("Adicionar");
		btAdd.setBounds(500, 70, 68, 25);
		panel.add(btAdd);
		btAdd.addActionListener(this);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Data");
		tableModel.addColumn("Hora");
		tableModel.addColumn("Produto");
		tableModel.addColumn("Preço");
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Subtotal");

		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 100, 659, 230);
		panel.add(scroll);

		lbTotalItens = new JLabel("Total de Ítens:");
		lbTotalItens.setBounds(10, 330, 80, 30);
		panel.add(lbTotalItens);

		tfTotalItens = new JTextField();
		tfTotalItens.setBounds(90, 335, 50, 25);
		tfTotalItens.setText(String.format("%d", ProcessaVenda.getTotalItens()));
		panel.add(tfTotalItens);

		lbTotalDinheiro = new JLabel("Total em R$:");
		lbTotalDinheiro.setBounds(140, 330, 70, 30);
		panel.add(lbTotalDinheiro);

		tfTotalDinheiro = new JTextField();
		tfTotalDinheiro.setBounds(210, 335, 60, 25);
		tfTotalDinheiro.setText(String.format("%.2f", ProcessaVenda.getTotalDinheiro()));
		panel.add(tfTotalDinheiro);

		btDel = new JButton("Excluir");
		btDel.setBounds(318, 330, 120, 30);
		panel.add(btDel);
		btDel.addActionListener(this);

		btCancelar = new JButton("Cancelar");
		btCancelar.setBounds(428, 330, 120, 30);
		panel.add(btCancelar);
		btCancelar.addActionListener(this);

		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(548, 330, 120, 30);
		panel.add(btSalvar);
		btSalvar.addActionListener(this);

		lbFundo = new JLabel("");
		lbFundo.setBounds(20, 0, 60, 60);
		fundo(0);
		panel.add(lbFundo);
	}

	private void fundo(int indice) {
		icon = new ImageIcon(new ImageIcon(fundo[indice]).getImage().getScaledInstance(57, 50, 50));
		lbFundo.setIcon(icon);
	}

	private void alternaImagem() {
		produto = ProdutoProcess.getProduto(Integer.parseInt(cbProduto.getSelectedItem().toString().split("")[0]));
		ProdutoProcess.getPd();
		img = new ImageIcon(ProdutoDAO.getImgPath(produto));
		image = img.getImage();
		newImg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);
		lbImagem.setIcon(img);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAdd) {
			if (!tfQuantidade.getText().isEmpty()) {
				compra = new Venda();
				compra.setNum(numero);
				compra.setData(tfData.getText());
				compra.setHora(tfHora.getText());
				compra.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
				produto = ProdutoProcess.getProdutos().get(cbProduto.getSelectedIndex());
				if (ProdutoProcess.getProdutos().get(cbProduto.getSelectedIndex()).darBaixa(compra.getQuantidade())) {
					compra.setProduto(produto);
					tableModel.addRow(compra.getStringVetor());
					numero++;
					tfNum.setText(String.format("%d", numero));
					tfData = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
					tfHora = new JTextField(new SimpleDateFormat("hh:mm").format(new Date()));
					tfQuantidade.setText("");
					tfTotalItens.setText(String.format("%d",ProcessaVenda.getTotalItens()));
					tfTotalDinheiro.setText(String.format("%.2f", ProcessaVenda.getTotalDinheiro()));
					ProdutoProcess.setProdutos(ProdutoProcess.getProdutos());
				} else {
					JOptionPane.showMessageDialog(null, "Quantidade insuficiente no estoque");
				}
			}
		} else if (e.getSource() == btDel) {
			if (table.getSelectedRow() >= 0) {
				tableModel.removeRow(table.getSelectedRow());
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha");
			}
		} else if (e.getSource() == btCancelar) {
			dispose();
		} else if(e.getSource() == btSalvar) {
			ArrayList<Venda> compras = new ArrayList<>();
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				compra = new Venda();
				compra.setNum(Integer.parseInt((String) tableModel.getValueAt(i, 0)));
				compra.setData((String) tableModel.getValueAt(i, 1));
				compra.setHora((String) tableModel.getValueAt(i, 2));
				compra.setProduto(new Produto(Integer.parseInt((String) tableModel.getValueAt(i, 3)),
						Double.parseDouble((String) tableModel.getValueAt(i, 4))));
				compra.setQuantidade(Integer.parseInt((String) tableModel.getValueAt(i, 5)));
				compras.add(compra);
			}
			ProcessaVenda.setCompras(compras);
			dispose();
		} else if(e.getSource() == cbProduto) {
			alternaImagem();
		}
	}
}
