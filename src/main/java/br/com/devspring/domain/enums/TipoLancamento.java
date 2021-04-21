package br.com.devspring.domain.enums;

public enum TipoLancamento {

    CREDITO(1,"CRÉDITO"),
    DEBITO(2,"DÉBITO");

    private int codigo;
    private String descricao;

    TipoLancamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoLancamento toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (TipoLancamento tipoLancamento: TipoLancamento.values()) {
            if (codigo.equals(tipoLancamento.getCodigo())){
                return tipoLancamento;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

}
