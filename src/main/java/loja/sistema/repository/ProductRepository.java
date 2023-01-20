package loja.sistema.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import loja.sistema.model.Product;
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
	@Query("SELECT p FROM Product p ORDER BY p.id desc")
	List<Product> findAll();
}
