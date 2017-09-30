package games;

import deckws.CardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CardGame {

    private List<CardType> supply = new ArrayList<>();
    private Map<String, List<CardType>> piles = new HashMap<>();

    public void addCards(List<CardType> deckCards) {
        supply.addAll(deckCards);
    }

    public void createPile(int pileSize, String pileName) {
        List<CardType> list = supply.subList(0, pileSize);
        piles.put(pileName, new ArrayList(list));
        list.clear();
    }

    public enum DistributionMethod {

        ROUND_ROBIN,
        FILL_PILE;
    }

    public void createPiles(int pileSize, DistributionMethod dist, String... pileNames) {
        for (String pileName : pileNames) {
            piles.put(pileName, new ArrayList());
        }
        switch (dist) {
            case ROUND_ROBIN:
                for (int i = 0; i < pileSize; i++) {
                    for (String pileName : pileNames) {
                        piles.get(pileName).add(supply.remove(0));
                    }
                }
                break;
            case FILL_PILE:
                for (String pileName : pileNames) {
                    for (int i = 0; i < pileSize; i++) {
                        piles.get(pileName).add(supply.remove(0));
                    }
                }
                break;
        }
    }

    public CardType showTopCard(String pileName) {
        return piles.get(pileName).get(0);
    }

    public List<CardType> showPile(String pileName) {
        return piles.get(pileName);
    }

    public void transferCards(String sourcePile, String destinationPile, int cardCount) {
        List<CardType> list = piles.get(sourcePile).subList(0, cardCount);
        piles.get(destinationPile).addAll(list);
        list.clear();
    }

    public void shuffleAll() {
        for (List<CardType> pile : piles.values()) {
            supply.addAll(pile);
            pile.clear();
        }
        piles.clear();
        shuffleCardList(supply);
    }

    public void shufflePile(String pileName) {
        shuffleCardList(piles.get(pileName));
    }

    private void shuffleCardList(List<CardType> cardList) {
        ThreadLocalRandom tlRandom = ThreadLocalRandom.current();
        for (int topIndex = cardList.size() - 1; topIndex > 0; topIndex--) {
            int randIndex = tlRandom.nextInt(topIndex + 1);
            CardType tempCard = cardList.get(randIndex);
            cardList.set(randIndex, cardList.get(topIndex));
            cardList.set(topIndex, tempCard);
        }
    }

    public int getSupplySize() {
        return supply.size();
    }

    public int getPileSize(String pileName) {
        List<CardType> pile = piles.get(pileName);
        if (pile != null) {
            return pile.size();
        } else {
            return 0;
        }
    }
}