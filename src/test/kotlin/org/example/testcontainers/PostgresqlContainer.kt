package org.example.testcontainers

import org.testcontainers.containers.PostgreSQLContainer

class PostgresqlContainer : PostgreSQLContainer<PostgresqlContainer>(IMAGE) {

    override fun start() {
        super.start()
        System.setProperty("DB_URL", jdbcUrl)
        System.setProperty("DB_USERNAME", username)
        System.setProperty("DB_PASSWORD", password)

        // logger.info { "database started with jdbc url $jdbcUrl username $username password $password" }
    }

    override fun stop() { } // do nothing, JVM handles shut down

    companion object {
        val container = PostgresqlContainer()

        private const val IMAGE = "postgres:12.5"

        init {
            container.start()
        }
    }
}
