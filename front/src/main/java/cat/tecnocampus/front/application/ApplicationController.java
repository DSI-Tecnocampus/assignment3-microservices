package cat.tecnocampus.front.application;

import domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApplicationController {

    private WebClient webClient;

    private String productServiceUrl;

    public ApplicationController(WebClient webClient,
        @Value("${app.product-service.host}") String productServiceHost,
        @Value("${app.product-service.port}") int productServicePort)
    {
        this.webClient = webClient;
        productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/products";
    }

    public void createProduct(Product product) {
        webClient.post()
                .uri(productServiceUrl)
                .bodyValue(product)
                //.body(Mono.just(product), Product.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public List<Product> getProducts() {
        List<Product> products = webClient.get()
                .uri(productServiceUrl)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();

        return products;
    }
}
