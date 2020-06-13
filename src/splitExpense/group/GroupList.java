package splitExpense.group;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupList {
	private HashMap<Integer, Group> groupList; // HashMap to map groups with a unique id

	public GroupList() {
		this.groupList = new HashMap<>();
	}
// Use HashCode as id
	public void addGroup(Group group) {
		if (this.groupList.containsKey(group.hashCode())) {
			return;
		}
		this.groupList.put(group.getName().hashCode(), group);
	}

	public ArrayList<Group> getGroupList() {
		return new ArrayList<Group> (this.groupList.values());
	}

	public void deleteGroup(int key) {
		this.groupList.remove(key);
	}
}