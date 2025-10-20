package riccardogulin.u5d11.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import riccardogulin.u5d11.entities.User;

import java.util.Date;

@Component
public class JWTTools {
	@Value("${jwt.secret}")
	private String secret;

	public String createToken(User user) {
		// Jwts (proveniente da jjwt-api) ha principalmente 2 metodi: builder() e parser() che useremo rispettivamente per creare il token
		// e per verificare il token

		return Jwts.builder()
				.issuedAt(new Date(System.currentTimeMillis())) // Data di emissione del token (IAT - Issued At), va messa in millisecondi
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Data di scadenza del token (Expiration Date), anch'essa in millisecondi
				.subject(String.valueOf(user.getId())) // Subject, ovvero a chi appartiene il token <-- MAI METTERE DATI RISERVATI/SENSIBILI NEL PAYLOAD
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Firmo il token con un algoritmo specifico HMAC fornendogli un SECRET che solo il server conosce (serve per generare token e verificare token)
				.compact(); // Assemblo il tutto ottenendo una stringa finale che sarà il mio token

	}

	// public void verifyToken(){}
}
