package com.learnreactivespring.webflux.fluxandmonoplayground;

  import org.junit.jupiter.api.Test;
  import reactor.core.publisher.Flux;
  import reactor.core.publisher.Mono;
  import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
     public void fluxTest(){
        Flux<String> stringFlux= Flux.just("Java", "Python", "C#")
//                        .concatWith(Flux.error(new RuntimeException("Exception Ocurred")))
                .concatWith(Flux.just("After error"))
                .log();
            stringFlux.subscribe(System.out::println, (e)->System.err.println(e), ()->System.out.println("completado"));
    }

    @Test
    public void fluxTestElements_WithoutError(){
        Flux<String> stringFlux= Flux.just("Java", "Python", "C#").log();
        StepVerifier.create(stringFlux)
                .expectNext("Java")
                .expectNext("Python")
                .expectNext("C#")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithError(){
        Flux<String> stringFlux= Flux.just("Java", "Python", "C#").log()
                .concatWith(Flux.error(new RuntimeException("Exception Ocurred")))
                        .log();
        StepVerifier.create(stringFlux)
                .expectNext("Java")
                .expectNext("Python")
                .expectNext("C#")
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Exception Ocurred")
                .verify();
    }

    @Test
    public void fluxTestElementsCount_WithError(){
        Flux<String> stringFlux= Flux.just("Java", "Python", "C#").log()
                .concatWith(Flux.error(new RuntimeException("Exception Ocurred")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Exception Ocurred")
                .verify();
    }

    @Test
    public void fluxTestElements_WithError1(){
        Flux<String> stringFlux= Flux.just("Java", "Python", "C#").log()
                .concatWith(Flux.error(new RuntimeException("Exception Ocurred")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Java","Python","C#")
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Exception Ocurred")
                .verify();
    }

    @Test
    public void monoTest(){
        Mono<String> stringMOno= Mono.just("Java");
        StepVerifier.create(stringMOno.log())
                .expectNext("Java")
                .verifyComplete();
    }

    @Test
    public void monoTest_Error(){
         StepVerifier.create(Mono.error(new RuntimeException("Exception Ocurred")).log())
                 .expectError(RuntimeException.class)
                 .verify();
    }
}
