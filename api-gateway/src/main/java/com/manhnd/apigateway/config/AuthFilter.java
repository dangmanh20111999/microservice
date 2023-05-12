package com.manhnd.apigateway.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Flux;
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{
	
	public static String SECRET = "-DKPHCeMooWiW6Gn2cfZpHT5MB-50PSBdUEUAfl_L0G2yywqq-IaCat4B3aNoJL7qz6Ng1kdfvP-QrOVg2ezYg";

	 public static class Config {
	        // empty class as I don't need any particular configuration
	    }

	 private AuthFilter() {
		 super(Config.class);
	 }
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			  if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				  exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				  byte[] bytes = "Authorization header is missing in request".getBytes(StandardCharsets.UTF_8);
				  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				  return exchange.getResponse().writeWith(Flux.just(buffer));
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//            
            String[] parts = authHeader.split(" ");
            String[] split_string = parts[1].split("\\.");
//            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
//            	exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//				  byte[] bytes = "Authorization header is missing in request".getBytes(StandardCharsets.UTF_8);
//				  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//				  return exchange.getResponse().writeWith(Flux.just(buffer));
//            }
            if (split_string.length < 3) {
            	exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
				  byte[] bytes = "Invalid Token! ".getBytes(StandardCharsets.UTF_8);
				  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				  return exchange.getResponse().writeWith(Flux.just(buffer));
            }
            DecodedJWT jwt = null;
            try {
            	 jwt = JWT.decode(authHeader.substring(7));
            }catch(Exception ex) {
            	  exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            	  byte[] bytes = "Token does not exist !".getBytes(StandardCharsets.UTF_8);
				  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				  return exchange.getResponse().writeWith(Flux.just(buffer));
            }
            
            String getPayLoad = jwt.getPayload();
//            String[] split_string = parts[1].split("\\.");
//            if (split_string.length < 2) {
//            	exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
//				  byte[] bytes = "Index 1 out of bounds for length 1".getBytes(StandardCharsets.UTF_8);
//				  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//				  return exchange.getResponse().writeWith(Flux.just(buffer));
//            }
           
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(getPayLoad));
            JSONObject json = null;
			try {
				json = new JSONObject(payload);
			} catch (JSONException e) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				  byte[] bytes = "Authorization header is missing in request".getBytes(StandardCharsets.UTF_8);
				  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				  return exchange.getResponse().writeWith(Flux.just(buffer));
			}  

    		String username = null;
			try {
				username = json.getString("sub");
			} catch (JSONException e) {
				exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
				  byte[] bytes = "Token Error when get Username".getBytes(StandardCharsets.UTF_8);
				  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				  return exchange.getResponse().writeWith(Flux.just(buffer));
			}
         if(username == "" || username == null) {
        	 exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			  byte[] bytes = "Username does not exist".getBytes(StandardCharsets.UTF_8);
			  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
			  return exchange.getResponse().writeWith(Flux.just(buffer));
         }
      	   ServerHttpRequest request = exchange.getRequest().mutate().
      	      header("X-auth-username", username).
      	      build();
      	      return chain.filter(exchange.mutate().request(request).build());
		  };
	}
}