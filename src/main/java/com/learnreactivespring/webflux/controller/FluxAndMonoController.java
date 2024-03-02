package com.learnreactivespring.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

@RestController
public class FluxAndMonoController {
    @GetMapping("/flux")
    public Flux<Integer> flux() {
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value="/fluxstream", produces= MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> fluxStream() {
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value="/fluxstreaminf", produces= MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> fluxStreamInf() {
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping("/mono")
    public Mono<Integer> getnMono() {
        return Mono.just(1)
                .log();
    }
}
