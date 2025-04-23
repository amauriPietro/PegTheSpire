package pegthespire.characters;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.animations.SpineAnimation;
import basemod.abstracts.CustomEnergyOrb;

import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
// import com.megacrit.cardcrawl.cards.blue.Streamline;
import pegthespire.cards.*;
import com.megacrit.cardcrawl.relics.Akabeko;

import basemod.abstracts.CustomPlayer;
import static pegthespire.PegTheSpireMod.*;
import pegthespire.PegTheSpireMod;

public class Roundrel extends CustomPlayer{
    public static final Logger logger = LogManager.getLogger(PegTheSpireMod.class.getName()); //Used to output to the console.


    public static class Enums {
        @SpireEnum
        public static PlayerClass ROUNDREL;
        @SpireEnum(name = "ROUNDREL_RED_COLOR")
        public static AbstractCard.CardColor RED_COLOR;
        @SpireEnum(name = "ROUNDREL_RED_COLOR")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 100;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 3;
    public static final int ORB_SLOTS = 0;

    private static final String ID = makeID("Roundrel");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;


    public Roundrel (String name, PlayerClass setClass) {
        super(
            name, 
            setClass,
            new CustomEnergyOrb(null, null, null), 
            new SpineAnimation(ROUNDREL_SKELETON_ATLAS, ROUNDREL_SKELETON_JSON, 0.7f)
        );

		this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
        initializeClass(null,
            ROUNDREL_SHOULDER_1,
            ROUNDREL_SHOULDER_2,
            ROUNDREL_CORPSE,
            getLoadout(), // loadout
            0F, 
            -20.0F, 
            220.0F, 
            290.0F,
            new EnergyManager(ENERGY_PER_TURN)            
        );

        loadAnimation(ROUNDREL_SKELETON_ATLAS, ROUNDREL_SKELETON_JSON, 1F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Sprite", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public ArrayList<String> getStartingRelics() {
        // TODO: add relic
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Akabeko.ID); // Akabeko
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        // TODO: customize
        CardCrawlGame.sound.playA("ATTACK_FLAME_BARRIER", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        // TODO: add cards
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Pebball.ID);
        retVal.add(Pebball.ID);
        retVal.add(Pebball.ID);
        retVal.add(Pebball.ID);
        return retVal;
    }

   public void damage(DamageInfo info) {
        // TODO: check if this is correct
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
            AnimationState.TrackEntry e1 = this.state.setAnimation(1, "Damage", false);
            this.state.addAnimation(1, "Damage", true, 0.0F);
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Sprite", false);
            this.state.addAnimation(0, "Sprite", true, 0.0F);
            e.setTimeScale(1.0F);
            e1.setTimeScale(1.0f);
        }
        super.damage(info);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 15;
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        // TODO: customize
        return "ATTACK_FLAME_BARRIER";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.RED_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return ROUNDREL_COLOR;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        // TODO: add card
        return null;
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Roundrel(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return ROUNDREL_COLOR;
    }

    @Override
    public Color getSlashAttackColor() {
        return ROUNDREL_COLOR;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.LIGHTNING,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.LIGHTNING};
    }

    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("images/not_implemented.png", "ORB_LIGHTNING_EVOKE"));
        panels.add(new CutscenePanel("images/not_implemented.png", "POWER_MANTRA"));
        panels.add(new CutscenePanel("images/not_implemented.png", "POWER_MANTRA"));
        return panels;
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }
    @Override
    public String getVampireText() {
        return TEXT[2];
    }
    public String getSensoryStoneText()
    {
        return TEXT[3];
    }








}
