package tao.berich.microservice.infra.fund.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tao.berich.microservice.infra.fund.entity.FundEntity;

@Repository
public interface FundRepository extends MongoRepository<FundEntity, String> {
}
