package br.com.devspring.domain.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "PESSOAFISICA"),
    PESSOJURIDICA(2, "PESSOJURIDICA");

    private int codigo;
    private String descricao;

    TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (TipoCliente tc: TipoCliente.values()) {
            if (codigo.equals(tc.getCodigo())){
                return tc;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
