package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioVendasVo;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}

	public List<RelatorioVendasVo> relatorioVendas() {
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioVendasVo (" +
					"produto.nome, " +
					"SUM(item.quantidade), " +
					"MAX(pedido.data) " +
				")" +
				"FROM Pedido pedido " +
				"JOIN pedido.itens item " +
				"JOIN item.produto produto " +
				"GROUP BY produto.nome " +
				"ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioVendasVo.class)
				.getResultList();
	}

	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p from Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
