package br.dio.ecommerce.pagamento.repository;

import br.dio.ecommerce.pagamento.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

}
