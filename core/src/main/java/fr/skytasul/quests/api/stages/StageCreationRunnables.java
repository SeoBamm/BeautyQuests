package fr.skytasul.quests.api.stages;

import org.bukkit.entity.Player;

import fr.skytasul.quests.gui.creation.stages.LineData;
import fr.skytasul.quests.structure.QuestBranch;

public abstract interface StageCreationRunnables<T extends AbstractStage> {
	
	/**
	 * Called when stage item clicked
	 * @param p player who click on the item
	 * @param datas datas of the line
	 */
	public abstract void start(Player p, LineData datas);

	/**
	 * Called when quest edition started
	 * @param datas all datas saved when stage creation
	 * @param stage Existing stage
	 */
	public abstract void edit(LineData datas, T stage);

	/**
	 * Called when quest creation finished
	 * @param datas all datas saved when stage creation
	 * @param branch quest created
	 * @return AsbtractStage created
	 */
	public abstract T finish(LineData datas, QuestBranch branch);
	
}
