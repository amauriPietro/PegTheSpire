package pegthespire.actions;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageFirstEnemiesAction extends AbstractGameAction {
    private DamageInfo info;
    private int numberOfTargets;

    public DamageFirstEnemiesAction(DamageInfo info, AttackEffect effect, int numberOfTargets) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
        this.numberOfTargets = numberOfTargets;
    }

    public void update() {
        int count = 0;

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (count >= numberOfTargets) {
                break;
            }
            if (!m.isDeadOrEscaped()) {
                this.addToTop(new DamageAction(m, new DamageInfo(this.info.owner, this.info.base, this.info.type), this.attackEffect));
                count++;
            }
        }

        this.isDone = true;
    }
}
