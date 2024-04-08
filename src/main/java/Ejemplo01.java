import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {
    public static void main(String[] args) {

        List<Integer> elementosMono = new ArrayList<>();
        List<Integer> elementosFlux = new ArrayList<>();

        //Creo el mono
        Mono<Integer> mono = Mono.just(120);

        //Creo el flux
        Flux<Integer> flux = Flux.just(1,2,3,4,5,6);

        //Me subscribo a ambos
        mono.subscribe(elementosMono::add);
        flux.subscribe(elementosFlux::add);

        System.out.println(elementosMono);
        System.out.println(elementosFlux);

    }
}
