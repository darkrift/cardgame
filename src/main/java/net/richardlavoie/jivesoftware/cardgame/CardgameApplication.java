package net.richardlavoie.jivesoftware.cardgame;

import net.richardlavoie.jivesoftware.cardgame.service.deck.DeckService;
import net.richardlavoie.jivesoftware.cardgame.service.game.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardgameApplication.class, args);
	}

	@Bean
	public GameService service() {
		return new GameService();
	}

	@Bean
	public DeckService deckService() {
		return new DeckService();
	}
}
