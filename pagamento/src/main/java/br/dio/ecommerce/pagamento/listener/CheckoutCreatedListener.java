package br.dio.ecommerce.pagamento.listener;

import br.dio.ecommerce.checkout.event.CheckoutCreatedEvent;
import br.dio.ecommerce.pagamento.entity.PagamentoEntity;
import br.dio.ecommerce.pagamento.event.PagamentoCreatedEvent;
import br.dio.ecommerce.pagamento.service.PagamentoService;
import br.dio.ecommerce.pagamento.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;

    private final PagamentoService pagamentoService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent checkoutCreatedEvent) {
        log.info("checkoutCreatedEvent={}", checkoutCreatedEvent);
        final PagamentoEntity pagamentoEntity = pagamentoService.create(checkoutCreatedEvent).orElseThrow();
        final PagamentoCreatedEvent pagamentoCreatedEvent = PagamentoCreatedEvent.newBuilder()
                .setCheckoutCode(pagamentoEntity.getCheckoutCode())
                .setPagamentoCode(pagamentoEntity.getCode())
                .build();
        checkoutProcessor.output().send(
                MessageBuilder.withPayload(pagamentoCreatedEvent).build()
        );
    }
}
