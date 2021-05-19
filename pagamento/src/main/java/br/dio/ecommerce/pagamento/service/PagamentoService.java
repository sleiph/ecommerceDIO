package br.dio.ecommerce.pagamento.service;

import br.dio.ecommerce.checkout.event.CheckoutCreatedEvent;
import br.dio.ecommerce.pagamento.entity.PagamentoEntity;

import java.util.Optional;

public interface PagamentoService {
    Optional<PagamentoEntity> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
