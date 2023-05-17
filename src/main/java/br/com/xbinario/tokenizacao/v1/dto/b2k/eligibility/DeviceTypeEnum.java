package br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility;


public enum DeviceTypeEnum {

    UNKNOWN("UNKNOWN"),
    MOBILE_PHONE("MOBILE_PHONE"),
    TABLET("TABLET"),
    WATCH("WATCH"),
    MOBILEPHONE_OR_TABLET("MOBILEPHONE_OR_TABLET");

    private final String name;

    private DeviceTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
