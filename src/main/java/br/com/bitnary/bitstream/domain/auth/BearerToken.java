package br.com.bitnary.bitstream.domain.auth;

import br.com.bitnary.bitstream.application.auth.exceptions.AuthenticationFailException;
import br.com.bitnary.bitstream.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BearerToken {
    private String type;
    private String token;
    private LocalDateTime expiresAt;

//    @Value("${api.token.secret_key}")
    @Getter(AccessLevel.NONE)
    private final String secretKey = "secret";

//    @Value("${api.token.duration}")
    private final int tokenDuration = 60000;

    public BearerToken(User user) {
        this.generateToken(user);
    }

    private void generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Instant expiresAt = generateExpirationDate();

            String token = JWT.create()
                    .withIssuer("br.com.bitstream")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);

            this.type = "Bearer";
            this.token = token;
            this.expiresAt = expiresAt.atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();

        } catch (Exception e) {
            throw e;
        }
    }

    public String validate() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("br.com.bitstream")
                    .build()
                    .verify(this.token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            System.out.println("@JWTVerificationException: " + e.getMessage());

            throw new AuthenticationFailException();
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(tokenDuration).toInstant(ZoneOffset.of("-03:00"));
    }
}
