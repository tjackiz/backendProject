package de.tjackiz.gatewayService.service.webClientService.impl;

import de.tjackiz.gatewayService.service.webClientService.WebClientService;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


// TODO singleton fine or do we need prototype ?
// TODO different base urls but multiple requests at same time
@Service
@Slf4j
@Validated
public class WebClientServiceImpl implements WebClientService {

    private final WebClient webClient;

    public WebClientServiceImpl() {
        // www: https://www.baeldung.com/spring-5-webclient#2-creating-a-webclient-instance-with-timeouts
        // assign different timeouts for underlying http client
        // TODO move hardcoded example values to config
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .baseUrl("http://localhost:8082")
                .build();
    }

    @Override
    public <T> Mono<T> get(String uri, ParameterizedTypeReference<T> parameterizedTypeReference) {

        return webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(parameterizedTypeReference);
    }

    @Override
    public <T> Mono<T> post(String uri, Object body, ParameterizedTypeReference<T> parameterizedTypeReference) {
        return webClient.post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(parameterizedTypeReference);
    }

    @Override
    public <T> Mono<T> put(String uri, Object body, ParameterizedTypeReference<T> parameterizedTypeReference) {
        return webClient.put()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(parameterizedTypeReference);
    }

    @Override
    public <T> Mono<T> delete(String uri, UUID id, ParameterizedTypeReference<T> parameterizedTypeReference) {
        return webClient.delete()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(parameterizedTypeReference);
    }
}
