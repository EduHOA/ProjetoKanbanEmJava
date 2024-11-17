package com.sociotech.ProjetoAulaPOO.model;

public enum Status {
    FAZER("A Fazer"),
    PROGRESSO("Em Progresso"),
    FEITO("Concluído");

    private final String descricaoSta;

    Status(String descricaoSta) {
        this.descricaoSta = descricaoSta;
    }

    public String getdescricaoSta() {
        return descricaoSta;
    }
}
