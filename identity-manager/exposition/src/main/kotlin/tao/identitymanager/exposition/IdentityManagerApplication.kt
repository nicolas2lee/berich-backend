package tao.identitymanager.exposition

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import reactor.core.publisher.Hooks

@SpringBootApplication
@ComponentScan(basePackages = ["tao.identitymanager"])
class IdentityManagerApplication

fun main(args: Array<String>) {
	Hooks.onOperatorDebug()
	runApplication<IdentityManagerApplication>(*args)
}
