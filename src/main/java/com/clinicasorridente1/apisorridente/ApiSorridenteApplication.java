package com.clinicasorridente1.apisorridente;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiSorridenteApplication {

	@Bean //para ser usado toda vex que a aplicação for rodada
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);// para que quando usarmos um mapper ele não
//		 considerar os valores nulos. Assim, nas atualizações de dados, apenas o que for modificado será
//		 atualizado, o restante ficará normal e não será atribuído como null
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiSorridenteApplication.class, args);
	}

}
