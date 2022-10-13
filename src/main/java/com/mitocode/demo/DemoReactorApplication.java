package com.mitocode.demo;

import io.reactivex.rxjava3.core.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@SpringBootApplication
public class DemoReactorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoReactorApplication.class);

	public void reactor(){
		Mono.just(new Persona(1,"Felipe",28))
				.doOnNext(p -> log.info("[Reactor] persona" + p))
				.subscribe(p -> log.info("[Reactor] persona" + p));
	}

	public void rxjava3(){
		Observable.just(new Persona(1,"Felipe",28))
				.doOnNext(p -> log.info("[Rxjava] persona" + p))
				.subscribe(p -> log.info("[RxJava3] persona: " + p));
	}

	public void mono(){
		Mono.just(new Persona(1,"Felipe",28))
				.subscribe(p -> log.info(p.toString()));
	}

	public void flux(){
		ArrayList<Persona> personas= new ArrayList<>();
		personas.add(new Persona(1,"Andredy", 31));
		personas.add(new Persona(2,"John", 32));
		personas.add(new Persona(3,"Fernando", 28));

		Flux.fromIterable(personas)
				.subscribe(p -> log.info(p.toString()));
	}

	public void fluxmMono(){
		ArrayList<Persona> personas= new ArrayList<>();
		personas.add(new Persona(1,"Andredy", 31));
		personas.add(new Persona(2,"John", 32));
		personas.add(new Persona(3,"Fernando", 28));

		Flux.fromIterable(personas);
		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList()
				.subscribe(p -> log.info(p.toString()));
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoReactorApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//mono();
		//flux();
		fluxmMono();

	}
}
