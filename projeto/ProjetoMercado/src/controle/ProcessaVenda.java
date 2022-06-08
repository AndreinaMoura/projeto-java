package controle;

import java.util.ArrayList;

import dao.VendaDAO;
import modelo.Venda;

public class ProcessaVenda {

	public static VendaDAO cd = new VendaDAO();
	public static ArrayList<Venda> compras = cd.ler();

	public static ArrayList<Venda> getCompras() {
		return compras;
	}

	public static void setCompras(ArrayList<Venda> compras) {
		ProcessaVenda.compras = compras;
		cd.escrever(compras);
	}

	public static int getAutoNumero() {
		if (ProcessaVenda.compras.isEmpty())
			return 1;
		else
			return ProcessaVenda.compras.get(ProcessaVenda.compras.size() - 1).getNum() + 1;
	}

	public static int getTotalItens() {
		int total = 0;
		for (Venda c : compras) {
			total += c.getQuantidade();
		}
		return total;
	}

	public static int getTotalItens(String data) {
		int total = 0;
		for (Venda c : compras) {
			if (data.contentEquals(c.getData())) {
				total += c.getQuantidade();
			}
		}
		return total;
	}

	public static double getTotalDinheiro() {
		double total = 0;
		for (Venda c : compras) {
			total += c.getSubtotal();
		}
		return total;
	}

	public static double getTotalDinheiro(String data) {
		double total = 0;
		for (Venda c : compras) {
			if (data.contentEquals(c.getData())) {
				total += c.getSubtotal();
			}
		}
		return total;
	}
}