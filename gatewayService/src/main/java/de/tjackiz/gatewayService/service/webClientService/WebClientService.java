package de.tjackiz.gatewayService.service.webClientService;

import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface WebClientService {

    public <T> Mono<T> get(@NotEmpty String uri, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    public <T> Mono<T> post(@NotEmpty String uri, @NotNull T body, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    public <T> Mono<T> put(@NotEmpty String uri, @NotNull T body, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);

    public <T> Mono<T> delete(@NotEmpty String uri, @NotNull UUID id, @NotNull ParameterizedTypeReference<T> parameterizedTypeReference);
}
