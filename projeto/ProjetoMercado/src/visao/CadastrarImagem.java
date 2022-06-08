package visao;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controle.ProdutoProcess;
import dao.ProdutoDAO;
import controle.ProdutoProcess;
import modelo.Produto;

public class CadastrarImagem extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btImagem, btCancelar, btSalvar;
	private JLabel lbId, lbNome, lbImagem;
	private ImageIcon img;
	private Image image;
	private Image newImg;
	private File arquivo;
	private String imgIco = ".\\assets\\icone.png";
	Produto prod;

	CadastrarImagem(int indice) {
		setTitle("Cadastrar imagem do Produto");
		setBounds(270, 180, 557, 370);
		setIconImage(new ImageIcon(imgIco).getImage());
		panel = new JPanel();
		setContentPane(panel);
		setLayout(null);

		prod = ProdutoProcess.getProduto(indice+1);
		ProdutoProcess.getPd();
		img = new ImageIcon(ProdutoDAO.getImgPath(prod));
		image = img.getImage();
		newImg = image.getScaledInstance(300, 250, java.awt.Image.SCALE_SMOOTH);
		lbId = new JLabel("Código do Produto: \t" + String.format("%d", prod.getCodProduto()));
		lbNome = new JLabel("Nome: \t" + prod.getNomeProduto());
		lbImagem = new JLabel();
		img = new ImageIcon(newImg);
		lbImagem.setIcon(img);
		lbId.setBounds(15, 40, 200, 30);
		lbNome.setBounds(15, 80, 200, 30);
		lbImagem.setBounds(230, 38, 300, 250);
		panel.add(lbId);
		panel.add(lbNome);
		panel.add(lbImagem);

		btImagem = new JButton("Carregar imagem");
		btImagem.setBounds(230, 10, 300, 25);
		panel.add(btImagem);
		btImagem.addActionListener(this);

		btCancelar = new JButton("Cancelar");
		btCancelar.setBounds(230, 290, 150, 30);
		panel.add(btCancelar);
		btCancelar.addActionListener(this);

		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(378, 290, 150, 30);
		panel.add(btSalvar);
		btSalvar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (btImagem == e.getSource()) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens tipo: png, jpg ou jpeg",
					new String[] { "png", "jpg", "jpeg" });
			fc.setFileFilter(filter);
			if (fc.showOpenDialog(this) != 1) {
				arquivo = fc.getSelectedFile();
				img = new ImageIcon(arquivo.getAbsolutePath());
				lbImagem.setIcon(
						new ImageIcon(img.getImage().getScaledInstance(300, 250, java.awt.Image.SCALE_SMOOTH)));
			}
		} else if (btSalvar == e.getSource()) {
			if (arquivo != null) {
				if (ProdutoProcess.getPd().saveImg(prod, arquivo)) {
					JOptionPane.showMessageDialog(this, "Alterada com sucesso.");
					this.dispose();
				}
			}
		}
	}
}