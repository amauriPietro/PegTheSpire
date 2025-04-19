package pegthespire.characters;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import pegthespire.PegTheSpireMod;
// import pegthespire.characters.Roundrel.Enums.ROUNDREL_COLOR;

public class Roundrel {

    public static class Enums {
        @SpireEnum
        public static PlayerClass ROUNDREL;
        @SpireEnum(name = "ROUNDREL_COLOR")
        public static AbstractCard.CardColor ROUNDREL_COLOR;
        @SpireEnum(name = "ROUNDREL_COLOR")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 3;

    private static final String ID = makeID("Roundrel");

}
