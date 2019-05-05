package net.richardlavoie.jivesoftware.cardgame.rest.controller;

import net.richardlavoie.jivesoftware.cardgame.data.Deck;
import net.richardlavoie.jivesoftware.cardgame.service.deck.DeckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeckController {

    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    @PostMapping("/decks")
    public Deck createDeck() {
        return service.createDeck();
    }

    @GetMapping("/decks")
    public List<Deck> listDecks() {
        return service.getDecks();
    }
}
