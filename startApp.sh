#!/bin/bash

export SPRING_SERVER_PORT=8080
export AMBIENTE=prod

# URL
export TOKENIZACAO_VISA_AUTENTICACAO_URL=https://xpto.com


export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_CANAL=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_SESSAO=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_TICKET=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_USUARIO=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_TIPO_USUARIO=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_EMPRESA=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_DEPENDENCIA=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_IDIOMA=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_FLUXODADOS=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_PARALELO=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_PERIFERICO=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_REQUESTID=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_CLOUDSERVER=1
export API_AUTENTICACAO_CWS_TOKENIZACAO_VISA_HEADER_APIKEY=1

mvn clean package -DskipTests

java -Xms64m -Xmx512M -XX:ActiveProcessorCount=2 -Xlog:gc -Dfile.encoding=UTF-8 -jar target/tokenizacao-visa-0.0.1-SNAPSHOT.jar
