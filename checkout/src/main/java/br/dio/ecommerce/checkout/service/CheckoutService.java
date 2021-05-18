package br.dio.ecommerce.checkout.service;

import br.dio.ecommerce.checkout.entity.CheckoutEntity;
import br.dio.ecommerce.checkout.resource.checkout.CheckoutRequest;

import java.util.Optional;

public interface CheckoutService {
    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);

    Optional<CheckoutEntity> updateStatus(String checkoutCode, CheckoutEntity.Status status);
}
