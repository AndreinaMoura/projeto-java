package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controle.ProdutoProcess;
import controle.UsuarioProcessa;
import uteis.Criptografia;

public class LoginForm extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L; 
	private JPanel painel;
	private JLabel rotulo1, rotulo2, rotulo3, rotulo4 ,lbFundo;
	private JTextField tfLogin;
	private JPasswordField senha;
	private JButton login;
	private String imgIco = ".\\assets\\2.png";
	private String [] fundo = {".\\assets\\fundo3.png"};
	private ImageIcon icon;
	
	LoginForm() {
		setTitle("Tela de Login");
		setBounds(100, 100, 600, 420);
		setIconImage(new ImageIcon(imgIco).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		painel = new JPanel();
		painel.setBackground(new Color(255, 233, 213));
		setContentPane(painel);
		setLayout(null);
		
		rotulo3 = new JLabel("BEM VINDO!");
		rotulo3.setBounds(260, 200, 100, 20);
		
		rotulo4 = new JLabel("Digite o seu usuario e senha:");
		rotulo4.setBounds(215, 220, 170, 20);
		
		rotulo1 = new JLabel("Login");
		rotulo1.setBounds(180, 250, 100, 20);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(220, 250, 170, 30);
		
		rotulo2 = new JLabel ("Senha");
		rotulo2.setBounds(180, 285, 100, 20);

		senha = new JPasswordField();
		senha.setEchoChar('*');
		senha.setBounds(220, 285, 170, 30);
		
		login = new JButton("Login");
		login.setBounds(220, 320, 170, 30);
		
		lbFundo = new JLabel("");
		lbFundo.setBounds(5,0,600,210);
		fundo(0);
		painel.add(lbFundo);
		
		login.addActionListener(this);
		 
		painel.add(rotulo3);
		painel.add(rotulo4);
		painel.add(rotulo1);
		painel.add(tfLogin);
		painel.add(rotulo2);
		painel.add(senha);
		painel.add(login);
	}
	
	private void fundo (int indice) {
		icon = new ImageIcon(new ImageIcon(fundo[indice]).getImage().getScaledInstance(570, 180, 500));
		lbFundo.setIcon(icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			if (tfLogin.getText().length() > 0 && new String(senha.getPassword()).length() > 0) {
				int indice = UsuarioProcessa.verificarLogin(tfLogin.getText());
				if (indice != -1) {
					if (UsuarioProcessa.checarSenha(indice, Criptografia.encripta(new String(senha.getPassword())))) {
						this.dispose();
						TelaPrincipal pf = new TelaPrincipal();
						pf.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(this, "Acesso negado");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Usuário não encontrado");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Preencha o login e a senha");
			}
		}
	}
	public static void main(String[] args) {
		ProdutoProcess.abrir();
		UsuarioProcessa.abrir();
		LoginForm login = new LoginForm();
		login.setVisible(true);
	}
}