package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controle.ProdutoProcess;
import dao.ProdutoDAO;
import modelo.Produto;

public class ProdutoForm extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel lbImagem, lbFundo,ftProd, text, codigoProduto, estoque, nomeProduto, lucro, precoVenda, precoUnitario, dtFabricacao, dtValidade,
			rotulos, fornecedor;
	private JTextField tfcodigoProduto, tfestoque;
	private JTextField tfnomeProduto;
	private JTextField tflucro;
	private JTextField tfprecoVenda;
	private JTextField tfprecoUnitario;
	private JTextField tfdtFabricacao;
	private JTextField tfdtValidade;
	private JTextField tffornecedor;
	private JScrollPane rolagem;
	private JTextArea verResultados;
	private JButton create, read, update, delete, addImg;
	private String imgIco = ".\\assets\\2.png";
	private int autoId = ProdutoProcess.produtos.size() + 1;
	private String texto = "";
	private String [] fundo = {".\\assets\\2.png"};
	private ImageIcon icon;

	private final Color C1 = new Color(238, 238, 238);
	private final Color C2 = new Color(180,112,54);


	private final Locale BRASIL = new Locale("pt", "BR");
	private DecimalFormat df = new DecimalFormat("#.00");

	ProdutoForm() {
		setTitle("Mercado Dois Irmãos");
		setBounds(100, 100, 800, 720);
		setIconImage(new ImageIcon(imgIco).getImage());
		painel = new JPanel();
		painel.setBackground(new Color(255, 233, 213));
		setContentPane(painel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		text = new JLabel("Cadastro e Consulta");
		text.setBounds(290, 5, 400, 30);
		text.setFont(new Font("OpenSans",Font.CENTER_BASELINE,25));
		text.setForeground(new Color(180,112,54));
		painel.add(text);

		codigoProduto = new JLabel("Código do produto:");
		codigoProduto.setBounds(20, 45, 120, 30);
		codigoProduto.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(codigoProduto);
		tfcodigoProduto = new JTextField(String.format("%d", autoId));
		tfcodigoProduto.setEditable(false);
		tfcodigoProduto.setBounds(140, 45, 300, 30);
		painel.add(tfcodigoProduto);
		tfcodigoProduto.setBackground(C1);
		tfcodigoProduto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		

		nomeProduto = new JLabel("Nome do produto:");
		nomeProduto.setBounds(20, 80, 120, 30);
		nomeProduto.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(nomeProduto);
		tfnomeProduto = new JTextField();
		tfnomeProduto.setBounds(140, 80, 300, 30);
		painel.add(tfnomeProduto);
		tfnomeProduto.setBackground(C1);
		tfnomeProduto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		estoque = new JLabel("Estoque:");
		estoque.setBounds(20, 115, 120, 30);
		estoque.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(estoque);
		tfestoque = new JTextField();
		tfestoque.setBounds(140, 115, 300, 30);
		painel.add(tfestoque);
		tfestoque.setBackground(C1);
		tfestoque.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		fornecedor = new JLabel("Fornecedor:");
		fornecedor.setBounds(20, 150, 120, 30);
		fornecedor.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(fornecedor);
		tffornecedor = new JTextField();
		tffornecedor.setBounds(140, 150, 300, 30);
		painel.add(tffornecedor);
		tffornecedor.setBackground(C1);
		tffornecedor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		lucro = new JLabel("Margem de lucro:");
		lucro.setBounds(20, 185, 120, 30);
		lucro.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(lucro);
		tflucro = new JTextField();
		tflucro.setBounds(140, 185, 300, 30);
		painel.add(tflucro);
		tflucro.setBackground(C1);
		tflucro.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		precoUnitario = new JLabel("Preço Unitário:");
		precoUnitario.setBounds(20, 220, 120, 30);
		precoUnitario.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(precoUnitario);
		tfprecoUnitario = new JTextField();
		tfprecoUnitario.setBounds(140, 220, 300, 30);
		painel.add(tfprecoUnitario);
		tfprecoUnitario.setBackground(C1);
		tfprecoUnitario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));


		dtFabricacao = new JLabel("Data de Fabricação:");
		dtFabricacao.setBounds(20, 255, 120, 30);
		dtFabricacao.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(dtFabricacao);
		tfdtFabricacao = new JTextField();
		tfdtFabricacao.setBounds(140, 255, 300, 30);
		painel.add(tfdtFabricacao);
		tfdtFabricacao.setBackground(C1);
		tfdtFabricacao.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		dtValidade = new JLabel("Data de Validade:");
		dtValidade.setBounds(20, 290, 120, 30);
		dtValidade.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(dtValidade);
		tfdtValidade = new JTextField();
		tfdtValidade.setBounds(140, 290, 300, 30);
		painel.add(tfdtValidade);
		tfdtValidade.setBackground(C1);
		tfdtValidade.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		precoVenda = new JLabel("Preço de Venda:");
		precoVenda.setBounds(20, 325, 120, 30);
		precoVenda.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(precoVenda);
		tfprecoVenda = new JTextField();
		tfprecoVenda.setBounds(140, 325, 300, 30);
		painel.add(tfprecoVenda);
		tfprecoVenda.setEditable(false);
		tfprecoVenda.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		tfprecoVenda.setBackground(C2);
		
		ftProd = new JLabel("Foto do produto");
		ftProd.setBounds(500, 40, 260, 30);
		ftProd.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,20));
		painel.add(ftProd);
		
		lbFundo = new JLabel("");
		lbFundo.setBounds(690,0, 160, 70);
		fundo(0);
		painel.add(lbFundo);
		
		rotulos = new JLabel(
				"Cód       |       Nome      |     Estoque     |     Fornecedor    |   Vencimento  |      Preço     |       Status:");
		rotulos.setBounds(20, 410, 900, 30);
		rotulos.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		painel.add(rotulos);
		rotulos.setForeground(Color.black);

		verResultados = new JTextArea();
		verResultados.setEditable(false);
		verResultados.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		verResultados.setFont(new Font("OpenSans",Font.LAYOUT_LEFT_TO_RIGHT,13));
		preencherAreaDeTexto();
		rolagem = new JScrollPane(verResultados);
		rolagem.setBounds(20, 440, 740, 200);
		painel.add(rolagem);
		
		lbImagem = new JLabel();
		lbImagem.setBounds(500, 130, 260, 200);
		painel.add(lbImagem);
		lbImagem.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		create = new JButton("Cadastrar");
		create.setBounds(140, 370, 110, 30);
		painel.add(create);
		create.addActionListener(this);

		read = new JButton("Buscar");
		read.setBounds(260, 370, 110, 30);
		painel.add(read);
		read.addActionListener(this);

		update = new JButton("Atualizar");
		update.setBounds(380, 370, 110, 30);
		update.setEnabled(false);
		painel.add(update);
		update.addActionListener(this);

		delete = new JButton("Excluir");
		delete.setBounds(500, 370, 110, 30);
		delete.setEnabled(false);
		painel.add(delete);
		delete.addActionListener(this);
		
		addImg = new JButton("Adicionar imagem");
		addImg.setBounds(500, 90, 260, 30);
		addImg.setEnabled(false);
		painel.add(addImg);
		addImg.addActionListener(this);
	}
	
	private void fundo (int indice) {
		icon = new ImageIcon(new ImageIcon(fundo[indice]).getImage().getScaledInstance(70, 70, 70));
		lbFundo.setIcon(icon);
	}

	private void cadastrar() {
		if (tfcodigoProduto.getText().length() != 0 && tfestoque.getText().length() != 0
				&& tfnomeProduto.getText().length() != 0 && tffornecedor.getText().length() != 0
				&& tflucro.getText().length() != 0 && tfprecoUnitario.getText().length() != 0
				&& tfdtFabricacao.getText().length() != 0 && tfdtValidade.getText().length() != 0) {

			df.setCurrency(Currency.getInstance(BRASIL));
			float pU;
			try {
				pU = Float.parseFloat(df.parse(tfprecoUnitario.getText()).toString());
			} catch (ParseException e) {
				System.out.println(e);
				pU = 0;
			}

			ProdutoProcess.produtos.add(new Produto(autoId, tfnomeProduto.getText(),
					Integer.parseInt(tfestoque.getText()), tffornecedor.getText(), Integer.parseInt(tflucro.getText()),
					tfdtFabricacao.getText(), tfdtValidade.getText(), pU));
			autoId++;
			ProdutoProcess.salvar();
			preencherAreaDeTexto();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
	}

	private void limparCampos() {
		tfcodigoProduto.setText(null);
		tfestoque.setText(null);
		tfnomeProduto.setText(null);
		tflucro.setText(null);
		tffornecedor.setText(null);
		tfprecoVenda.setText(null);
		tfprecoUnitario.setText(null);
		tfdtFabricacao.setText(null);
		tfdtValidade.setText(null);
	}

	private void preencherAreaDeTexto() {
		texto = "";
		for (Produto p : ProdutoProcess.produtos) {
			texto += p.toString();
		}
		verResultados.setText(texto);
	}

	private void buscar() throws IOException {
		String entrada = JOptionPane.showInputDialog(this, "Digite o código do produto:");

		boolean isNumeric = true;
		if (entrada != null) {
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isDigit(entrada.charAt(i))) {
					isNumeric = false;
				}
			}
		} else {
			isNumeric = false;
		}
		if (isNumeric) {
			int id = Integer.parseInt(entrada);
			Produto prod = new Produto(id);
			if (ProdutoProcess.produtos.contains(prod)) {
				int indice = ProdutoProcess.produtos.indexOf(prod);
				tfcodigoProduto.setText(ProdutoProcess.produtos.get(indice).getCodProduto("s"));
				tfestoque.setText(ProdutoProcess.produtos.get(indice).getEstoque("s"));
				tfnomeProduto.setText(ProdutoProcess.produtos.get(indice).getNomeProduto());
				tffornecedor.setText(ProdutoProcess.produtos.get(indice).getFornecedor());
				tflucro.setText(ProdutoProcess.produtos.get(indice).getLucro("s"));
				tfprecoUnitario.setText(ProdutoProcess.produtos.get(indice).getPrecoUnitario("s"));
				tfdtFabricacao.setText(ProdutoProcess.produtos.get(indice).getDtFabricacao("s"));
				tfdtValidade.setText(ProdutoProcess.produtos.get(indice).getDtValidade("s"));
				tfprecoVenda.setText(ProdutoProcess.produtos.get(indice).valorTotal("s"));
				lbImagem.setIcon(new ImageIcon(ImageIO.read(new File(ProdutoDAO.getImgPath(prod)))));
				create.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);
				addImg.setEnabled(true);
				ProdutoProcess.salvar();
			} else {
				JOptionPane.showMessageDialog(this, "Produto não encontrado");
			}
		}

	}

	private void alterar() {
		int id = Integer.parseInt(tfcodigoProduto.getText());
		Produto prod = new Produto(id);
		int indice = ProdutoProcess.produtos.indexOf(prod);
		if (tfcodigoProduto.getText().length() != 0 && tfestoque.getText().length() != 0
				&& tfnomeProduto.getText().length() != 0 && tffornecedor.getText().length() != 0
				&& tflucro.getText().length() != 0 && tfprecoUnitario.getText().length() != 0
				&& tfdtFabricacao.getText().length() != 0 && tfdtValidade.getText().length() != 0) {

			df.setCurrency(Currency.getInstance(BRASIL));
			float pU;
			try {
				pU = Float.parseFloat(df.parse(tfprecoUnitario.getText()).toString());
			} catch (ParseException e) {
				System.out.println(e);
				pU = 0;
			}

			ProdutoProcess.produtos.set(indice,
					new Produto(Integer.parseInt(tfcodigoProduto.getText()), tfnomeProduto.getText(),
							Integer.parseInt(tfestoque.getText()), tffornecedor.getText(),
							Integer.parseInt(tflucro.getText()), tfdtFabricacao.getText(), tfdtValidade.getText(), pU));
			preencherAreaDeTexto();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
		tfcodigoProduto.setText(String.format("%d", autoId));
		ProdutoProcess.salvar();
	}

	private void excluir() {
		int id = Integer.parseInt(tfcodigoProduto.getText());
		Produto prod = new Produto(id);
		int indice = ProdutoProcess.produtos.indexOf(prod);
		ProdutoProcess.produtos.remove(indice);
		preencherAreaDeTexto();
		limparCampos();
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
		tfcodigoProduto.setText(String.format("%d", autoId));
		ProdutoProcess.salvar();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == create) {
			cadastrar();
		}
		if (e.getSource() == read) {
			try {
				buscar();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == update) {
			alterar();
		}
		if (e.getSource() == delete) {
			excluir();
		}
		if (e.getSource() == addImg) {
			int id = Integer.parseInt(tfcodigoProduto.getText());
			Produto prod = new Produto(id);
			int indice = ProdutoProcess.produtos.indexOf(prod);
			CadastrarImagem CI = new CadastrarImagem(indice);
			CI.setModal(true);
			CI.setVisible(true);
		}
	}

	public void setModal(boolean b) {
	}
}