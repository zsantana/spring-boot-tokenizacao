package br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility;


import javax.validation.constraints.NotBlank;

import br.com.xbinario.tokenizacao.v1.dto.base.DeviceInfo;
import lombok.Data;

@Data
public class RequestElegibilidadeDTO{

        @NotBlank(message = "Campo tokenRequestorID obrigatório")
        private String tokenRequestorID;

        @NotBlank(message = "Campo tokenReferenceID obrigatório")
        private String tokenReferenceID;

        @NotBlank(message = "Campo panReferenceID obrigatório")
        private String panReferenceID;

        private String lifeCycleTraceID;

        @NotBlank(message = "Campo panSource obrigatório")
        private PanSourceEnum panSource;

        private DeviceInfo deviceInfo;

        private String encryptedData;
        
}