package com.getir.readingisgood.config;

/*
@Configuration
@EnableMongoRepositories(basePackages = "com.getir.readingisgood.persist")
@RequiredArgsConstructor
*/
public class MongoConfig {
}

   /*  @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
       extends AbstractMongoClientConfiguration {

    private final Environment environment;

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

     @Bean
    TransactionTemplate transactionTemplate(MongoTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("spring.data.mongodb.database");
    }
}*/
