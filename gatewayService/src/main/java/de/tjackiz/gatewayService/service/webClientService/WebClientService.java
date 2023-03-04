package de.tjackiz.gatewayService.service.webClientService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;

public interface WebClientService {

    <T> Mono<T> get(@NotBlank String uri, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    <T> Mono<T> post(@NotBlank String uri, @NotNull Object body, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    <T> Mono<T> put(@NotBlank String uri, @NotNull Object body, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    <T> Mono<T> delete(@NotBlank String uri, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    void delete(@NotBlank String uri);
}
