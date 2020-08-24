package tao.identitymanager.infra.common

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["tao.identitymanager.infra"])
class MongoConfig {

/*    override fun getDatabaseName(): String {
        return "berich-dev"
    }*/
}