package tao.berich.getfundlist.domain.model;

import java.util.Objects;

public class Fund {
    private final String code;
    private final String chineseName;
    private final String pseudo;

    public Fund(String code, String chineseName, String pseudo) {
        this.code = code;
        this.chineseName = chineseName;
        this.pseudo = pseudo;
    }

    public String getCode() {
        return code;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return Objects.equals(code, fund.code) &&
                Objects.equals(chineseName, fund.chineseName) &&
                Objects.equals(pseudo, fund.pseudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, chineseName, pseudo);
    }

    @Override
    public String toString() {
        return "Fund{" +
                "code='" + code + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }
}
