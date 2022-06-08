package controle;

import java.util.ArrayList;

import dao.UsuarioDAO;
import modelo.Usuario;
import uteis.Criptografia;

public class UsuarioProcessa {

	public static ArrayList<Usuario> usuarios = new ArrayList<>();
	public static String LoginUsusarioConectado;
	private static UsuarioDAO usiDAO = new UsuarioDAO();
	
	public static void abrir() {
		usuarios = usiDAO.ler();
		if(usuarios.size() == 0) {
			usuarios.add(new Usuario("admin", Criptografia.encripta("admin")));
		}
	}
	
	public static void salvar() {
		usiDAO.escrever(usuarios);
	}
	
	public static int verificarLogin(String email) {
		int retorno = -1;
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(email)) {
				return i;
			}
		}
		return retorno;
	}
	
	public static boolean checarSenha(int indice, String senha) {
		if (usuarios.get(indice).getSenha().equals(senha)) {
			LoginUsusarioConectado = usuarios.get(indice).getLogin();
			return true;
		}
		return false;
	}
}
