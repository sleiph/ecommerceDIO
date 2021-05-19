package br.dio.ecommerce.pagamento.service;

import br.dio.ecommerce.checkout.event.CheckoutCreatedEvent;
import br.dio.ecommerce.pagamento.entity.PagamentoEntity;
import br.dio.ecommerce.pagamento.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class PagamentoServiceImpl implements PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    @Override
    public Optional<PagamentoEntity> create(CheckoutCreatedEvent checkoutCreatedEvent) {
        final PagamentoEntity pagamentoEntity = PagamentoEntity.builder()
                .checkoutCode(checkoutCreatedEvent.getCheckoutCode())
                .code(UUID.randomUUID().toString())
                .build();
        pagamentoRepository.save(pagamentoEntity);
        return Optional.of(pagamentoEntity);
    }
}
