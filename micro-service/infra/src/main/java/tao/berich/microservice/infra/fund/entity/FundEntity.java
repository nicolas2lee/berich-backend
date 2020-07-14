package tao.berich.microservice.infra.fund.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tao.berich.microservice.domain.model.Fund;

@Document("fund")
public class FundEntity {
    @Id
    public String id;
    public String code;

    public String chineseName;
    public String pseudo;

    public Fund toDomain(){
        return new Fund(code, chineseName, pseudo);
    }
}
