package loja.sistema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import loja.sistema.model.Pedido;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {
	
      List<Pedido> findAll();
	@Query("SELECT pedido FROM Pedido pedido WHERE pedido.nomeCompleto LIKE %:p% OR pedido.codPedido LIKE %:p% ORDER BY pedido.nomeCompleto asc ")
	List<Pedido> buscarPedido(@Param("p") String parametro);
}
