package pegthespire.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
// TODO: bouncing flask effect
// import com.megacrit.cardcrawl.cards.green.BouncingFlask;
import pegthespire.actions.DamageFirstEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import pegthespire.PegTheSpireMod;
import pegthespire.characters.Roundrel;

import static pegthespire.PegTheSpireMod.*;

public class Testorb extends AbstractOrb {

    /*
     * Pebball: Deal 6(9) damage to the first enemy. Starter card.
     */

    // TEXT DECLARATION
    public static final String ID = PegTheSpireMod.makeID(Testorb.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    
    // TODO: add a custom image for the card
    public static final String IMG = makeAttackCardPath("default.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Roundrel.Enums.RED_COLOR;

    private static final int COST = 1;

    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int PEGS = 20;
    

    // /STAT DECLARATION/

    // TODO: implement crits
    public Testorb() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, PEGS);
        baseDamage = DAMAGE;
        this.tags.add(CardTags.STARTER_STRIKE); // Makes it a starter card
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageFirstEnemiesAction(new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public void onBlabla() {
    }
}
