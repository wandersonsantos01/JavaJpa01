package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto1 = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(10, pedido1, produto1));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(50, pedido1, produto2));
		pedido2.adicionarItem(new ItemPedido(80, pedido1, produto3));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido1);
		pedidoDao.cadastrar(pedido2);

		em.getTransaction().commit();

		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL: " + totalVendido);

		List<RelatorioVendasVo> relatorio = pedidoDao.relatorioVendas();
		relatorio.forEach(System.out::println);
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria consoles = new Categoria("CONSOLES");
		Categoria notebooks = new Categoria("NOTEBOOKS");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
		Produto ps5 = new Produto("Playstation 5", "Jogue", new BigDecimal("2000"), consoles );
		Produto mac = new Produto("Macbook Pro", "Programe", new BigDecimal("25000"), notebooks );

		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(consoles);
		categoriaDao.cadastrar(notebooks);

		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(ps5);
		produtoDao.cadastrar(mac);

		clienteDao.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();
	}

}
