package com.denasu.sample.jetty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class SampleJettyApplicationTests
{
    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void test() {
        String good = restTemplate.getForObject("/", String.class);
        assertEquals(good, "Good");
    }
    @SpringBootConfiguration
    public static class TestConfig {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate() {
                @SuppressWarnings("unchecked")
				@Override
                public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
                    System.out.println("Good");
                    if (responseType == String.class) {
                        return (T) "Good";
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            };
        }
    }
}