package loja.sistema.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import loja.sistema.model.Product;
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
	@Query("SELECT p FROM Product p ORDER BY p.id desc")
	List<Product> findAll();
	@Query("SELECT po FROM Product po WHERE po.name LIKE %:p%  OR po.quantidadeEmEstoque LIKE %:p% OR po.descricaoDoProduct LIKE %:p% OR po.precoDoProduct LIKE %:p% OR po.codProduct LIKE %:p%  ORDER BY po.name asc ")
	List<Product> buscarPorduct(@Param("p") String parametro);
}
