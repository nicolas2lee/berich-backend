package tao.berich.microservice.infra.fund;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "tao.berich.microservice.infra.fund.repository")
public class MongoConfig {
}
