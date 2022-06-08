package modelo;

public class Venda {

	private int num;
	private String data;
	private String hora;
	private Produto produto;
	private int quantidade;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	// Outros Métodos
	public double getSubtotal() {
		return this.quantidade * this.produto.getPrecoUnitario();
	}

	@Override
	public String toString() {
		return "Compra [num=" + num + ", data=" + data + ", hora=" + hora + ", produto=" + produto.getCodProduto()
				+ ", preco =" + produto.getPrecoUnitario() + ", quantidade=" + quantidade + "]";
	}

	//Metodo para enviar os dados para um arquivo de texto
	public String toCSV() {
		return num + ";" + data + ";" + hora + ";" + produto.getCodProduto() + ";" + produto.getPrecoUnitario() + ";" + quantidade
				+ "\r\n";
	}

	public String[] getStringVetor() {
		return new String[] { "" + num, data, hora, "" + produto.getCodProduto(), "" + produto.getPrecoUnitario(), "" + quantidade,
				String.format("%.2f", getSubtotal()) };
	}
}