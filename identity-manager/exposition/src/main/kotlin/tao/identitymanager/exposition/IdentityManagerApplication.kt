package tao.identitymanager.exposition

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["tao.identitymanager"])
class IdentityManagerApplication

fun main(args: Array<String>) {
	runApplication<IdentityManagerApplication>(*args)
}
