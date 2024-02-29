package com.learnreactivespring.webflux.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest(){
        Flux<String> stringFlux= Flux.just("Java", "Python", "C#")
                        .concatWith(Flux.error(new RuntimeException("Exception Ocurred")));
            stringFlux.subscribe(System.out::println, (e)->System.err.println(e));
    }
}
