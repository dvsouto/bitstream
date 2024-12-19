package br.com.bitnary.bitstream;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@ComponentScan(basePackages = "br.com.bitnary.bitstream")
public class TestConfig {
}