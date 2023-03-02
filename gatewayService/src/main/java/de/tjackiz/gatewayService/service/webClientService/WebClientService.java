package de.tjackiz.gatewayService.service.webClientService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WebClientService {

    public <T> Mono<T> get(@NotBlank String uri, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    public <T> Mono<T> post(@NotBlank String uri, @NotNull Object body, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    public <T> Mono<T> put(@NotBlank String uri, @NotNull Object body, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    public <T> Mono<T> delete(@NotBlank String uri, @NotNull UUID id, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);
}
