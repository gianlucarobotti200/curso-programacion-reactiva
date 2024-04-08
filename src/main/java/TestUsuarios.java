import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class TestUsuarios {

    private static final Logger log = LoggerFactory.getLogger(TestUsuarios.class);

    public static void main(String[] args) {
        Flux<String> nombres = Flux.just("Gianluca Robotti", "Julieta Lamia", "Octavio Robotti", "Cecilia Farias");

        Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
                .filter(usuario -> usuario.getApellido().equalsIgnoreCase("Lamia"))
                .doOnNext(usuario -> {
                    if(usuario == null){
                        throw new RuntimeException("Los nombres no pueden estar vacíos");
                    }
                    System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));

                })
                .map(usuario -> {
                    String nombre = usuario.getNombre().toLowerCase();
                    usuario.setNombre(nombre);
                    return usuario;
                });

        usuarios.subscribe(
                e -> log.info(e.toString()),
                error -> log.error(error.getMessage()),
                () -> log.info("Se ha finalizado la ejecución del observable con éxito")
        );
    }
}
