package controle;

import java.util.ArrayList;


import modelo.Produto;
import dao.ProdutoDAO;

public class ProdutoProcess {

	public static ArrayList<Produto> produtos = new ArrayList<>();
	private static ProdutoDAO pd = new ProdutoDAO();

	public static void abrir() {
		produtos = pd.ler();
	}

	public static void salvar() {
		pd.escrever(produtos);
	}

	public static ProdutoDAO getPd() {
		return pd;
	}
	public static void setPd(ProdutoDAO pd) {
		ProdutoProcess.pd = pd;
	}
	public static ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public static void setProdutos(ArrayList<Produto> produtos) {
		ProdutoProcess.produtos = produtos;
		pd.escrever(produtos);
	}

	public static Produto getProduto(int codigo) {
		Produto prod = new Produto(codigo);
		if (produtos.contains(prod)) {
			return produtos.get(produtos.indexOf(prod));
		}
		return null;
	}

	public static int getAutoCodigo() {
		if (ProdutoProcess.produtos.isEmpty())
			return 1;
		else
			return ProdutoProcess.produtos.get(ProdutoProcess.produtos.size() - 1).getCodProduto() + 1;
	}

	public static int getTotalItens() {
		int total = 0;
		for (Produto p : produtos) {
			total += p.getQuantidade();
		}
		return total;
	}

	public static double getTotalDinheiro() {
		double total = 0;
		for (Produto p : produtos) {
			total += p.getSubtotal();
		}
		return total;
	}
}