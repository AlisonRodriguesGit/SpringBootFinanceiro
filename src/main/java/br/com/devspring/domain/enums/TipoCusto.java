package br.com.devspring.domain.enums;

public enum TipoCusto {

    FIXO(1, "FIXO"),
    VARIAVEL(2, "VARIAVEL");

    private int codigo;
    private String descricao;

    TipoCusto(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCusto toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (TipoCusto tc: TipoCusto.values()) {
            if (codigo.equals(tc.getCodigo())){
                return tc;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
