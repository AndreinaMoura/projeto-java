package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controle.ProdutoProcess;

public class TelaPrincipal extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JPanel painel;
		private JMenuBar barraMenu = new JMenuBar();
		private JMenu menuArquivo, menuSistema;
		private JMenuItem itemProdutos, itemVendas, itemLogin, itemSair;
		private String imgIco = ".\\assets\\2.png";
		private String imgFundo = "./assets/fundo.png";
		private ImageIcon fundo;
		private JLabel lbFundo = new JLabel();

		TelaPrincipal() {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("Mercado Dois Irmaos");
			setBounds(100, 100, 900, 700);
			setIconImage(new ImageIcon(imgIco).getImage());
			painel = new JPanel();
			painel.setBackground(new Color(255, 233, 213));
			setJMenuBar(barraMenu);
			setContentPane(painel);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(null);

			menuArquivo = new JMenu("Arquivo");
			menuSistema = new JMenu("Sistema");
			barraMenu.add(menuArquivo);
			barraMenu.add(menuSistema);
			itemLogin = new JMenuItem("Login");
			itemProdutos = new JMenuItem("Produtos");
			itemVendas = new JMenuItem("Vendas");
			itemSair = new JMenuItem("Sair do Sistema");
			menuArquivo.add(itemProdutos);
			menuArquivo.add(itemVendas);
			menuSistema.add(itemSair);
			
			fundo = new ImageIcon(new ImageIcon(imgFundo).getImage().getScaledInstance(850, 605, 100));
			lbFundo.setIcon(fundo);
			lbFundo.setBounds(20, 15, 850, 605);
			painel.add(lbFundo);

			itemProdutos.addActionListener(this);
			itemVendas.addActionListener(this);
			itemLogin.addActionListener(this);
			itemSair.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == itemProdutos) {
				ProdutoForm pf = new ProdutoForm();
				pf.setModal(true);
				pf.setVisible(true);
			} else if (e.getSource() == itemVendas) {
				VendasForm cf = new VendasForm();
				cf.setModal(true);
				cf.setVisible(true);
			}
			else {
				dispose();
			}
		}

		public static void main(String[] args) {
			TelaPrincipal mf = new TelaPrincipal();
			ProdutoProcess.abrir();
			mf.setVisible(true);
		}
	}