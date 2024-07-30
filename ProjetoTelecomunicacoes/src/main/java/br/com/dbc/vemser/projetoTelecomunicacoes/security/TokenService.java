package br.com.dbc.vemser.projetoTelecomunicacoes.security;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.UsuarioEntity;
import br.com.dbc.vemser.projetoTelecomunicacoes.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    static final String HEADER_STRING = "Authorization";
    private final UsuarioService usuarioService;
    private static final String TOKEN_PREFIX = "BEARER ";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

//    public String getToken(UsuarioEntity usuarioEntity) {
//        String tokenTexto = usuarioEntity.getLogin() + ";" + usuarioEntity.getSenha(); // thiago.gomes;1234 > 923y978126391
//        String token = Base64.getEncoder().encodeToString(tokenTexto.getBytes());
//        return token;
//    }

    public String gerarTokenJWT(UsuarioEntity usuarioEntity) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        return TOKEN_PREFIX +
                Jwts.builder()
                        .setIssuer("vemser-api")
                        .claim(Claims.ID, usuarioEntity.getIdUsuario().toString())
                        .setIssuedAt(now)
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
    }

    public Optional<UsuarioEntity> isValid(String token) {
        if(token == null){
            return Optional.empty();
        }
        byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
        String decoded = new String(decodedBytes);
        String[] split = decoded.split(";");
        return usuarioService.findByLoginAndSenha(split[0], split[1]);
    }
}
