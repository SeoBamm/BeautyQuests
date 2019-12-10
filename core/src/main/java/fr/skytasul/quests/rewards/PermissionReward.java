package fr.skytasul.quests.rewards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import fr.skytasul.quests.api.rewards.AbstractReward;
import fr.skytasul.quests.utils.MissingDependencyException;
import fr.skytasul.quests.utils.Utils;
import fr.skytasul.quests.utils.compatibility.Dependencies;
import fr.skytasul.quests.utils.types.Permission;

public class PermissionReward extends AbstractReward {
	
	public final List<Permission> permissions;

	public PermissionReward(){
		this(new ArrayList<>());
	}

	public PermissionReward(List<Permission> permissions) {
		super("permReward");
		if (!Dependencies.vault) throw new MissingDependencyException("Vault");
		this.permissions = permissions;
	}

	public String give(Player p){
		for (Permission perm : permissions) {
			perm.give(p);
		}
		return null;
	}

	
	protected void save(Map<String, Object> datas){
		datas.put("perms", Utils.serializeList(permissions, Permission::serialize));
	}

	protected void load(Map<String, Object> savedDatas){
		if (savedDatas.containsKey("perm")) {
			permissions.add(new Permission((String) savedDatas.get("perm"), false, null));
		}else if (savedDatas.containsKey("permissions")) { // TODO remove on 0.19
			Map<String, Boolean> map = (Map<String, Boolean>) savedDatas.get("permissions");
			for (Entry<String, Boolean> en : map.entrySet()) {
				permissions.add(new Permission(en.getKey(), en.getValue(), null));
			}
		}else {
			permissions.addAll(Utils.deserializeList((List<Map<String, Object>>) savedDatas.get("perms"), Permission::deserialize));
		}
	}

}
