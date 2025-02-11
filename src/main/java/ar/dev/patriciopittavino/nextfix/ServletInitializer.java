package ar.dev.patriciopittavino.nextfix;

import ar.dev.patriciopittavino.nextfix.model.Pelicula;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NextFixApplication.class);
	}

}
