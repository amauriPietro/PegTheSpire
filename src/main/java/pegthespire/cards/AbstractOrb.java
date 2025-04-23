package pegthespire.cards;

// import basemod.AutoAdd;
import basemod.abstracts.CustomCard;

public abstract class AbstractOrb extends CustomCard {

    public int pegs;

    public AbstractOrb(final String id,
                               final String name,
                               final String img,
                               final int cost,
                               final String rawDescription,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target,
                               final int pegs) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        this.pegs = pegs;
    }

    // public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
    //     super.displayUpgrades();
    //     if (upgradedDefaultSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
    //         defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
    //         isDefaultSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
    //     }

    // }

    // public void upgradeDefaultSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
    //     defaultBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
    //     defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Set the number to be equal to the base value.
    //     upgradedDefaultSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    // }

    public void blabla() {
        if (this.pegs > 0) {
            this.onBlabla();
        }
    }
    public abstract void onBlabla();
}