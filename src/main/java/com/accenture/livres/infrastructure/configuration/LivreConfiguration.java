package com.accenture.livres.infrastructure.configuration;

import com.accenture.livres.application.port.input.LivreUseCase;
import com.accenture.livres.application.port.output.LivrePort;
import com.accenture.livres.application.service.LivreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LivreConfiguration {

    @Bean
    public LivreUseCase livreUseCase(LivrePort livrePort) {
        return new LivreService(livrePort);
    }
}
