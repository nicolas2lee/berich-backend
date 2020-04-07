package tao.berich.microservice.infra.fund.dto;

import tao.berich.microservice.domain.model.Fund;

public class FundDto {
    private String code;
    private String chineseName;
    private String pseudo;

    public String getCode() {
        return code;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getPseudo() {
        return pseudo;
    }
    public Fund toDomain(){
        return new Fund(code, chineseName, pseudo);
    }
}
