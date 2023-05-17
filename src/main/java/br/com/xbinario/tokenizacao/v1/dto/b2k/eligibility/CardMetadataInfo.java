package br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility;

import lombok.Data;

@Data
public class CardMetadataInfo {
    
     private String cardIssuer;
     private String foregroundColor;
     private String backgroudColor;
     private String labelColor;
     private String shortDescription;
     private String longDescription;
     private String profileID;
     private CardArtDataDetails cardArtData;
     private String termsAndConditionsID;
     private String privacyPolicyURL;
     private String termsAndConditionsURL;
     private String contactInfo;

}
