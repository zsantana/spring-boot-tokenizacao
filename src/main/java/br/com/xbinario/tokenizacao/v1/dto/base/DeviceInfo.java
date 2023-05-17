package br.com.xbinario.tokenizacao.v1.dto.base;

import lombok.Data;

@Data
public class DeviceInfo {
    
    private String deviceID;
    private String deviceLanguageCode;
    private String deviceType;
    private String deviceName;
    private String deviceNumber;
    private String osType;
    private String osVersion;
    private String osBuildID;
    private String deviceIDType;
    private String deviceManufacturer;
    private String deviceBrand;
    private String deviceModel;
    private String deviceLocation;
    private String deviceIndex;
    private String deviceIPAddressV4;
    private String locationSource; // ENUM {WIFI, CELLULAR, GPS, OTHER}
    private String tokenProtectionMethd; //ENUM {SOFTWARE, TRUSTED_EXECUTION_ENVIROMENT, SECURE_ELEMENT, CLOUD}

}
