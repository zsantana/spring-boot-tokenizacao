package br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility;


public enum PanSourceEnum {
    
    KEY_ENTERED("KEY_ENTERED"),
    ON_FILE("ON_FILE"),
    MOBILI_BANCKING_APP("MOBILI_BANCKING_APP");

    private final String name;

    private PanSourceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
