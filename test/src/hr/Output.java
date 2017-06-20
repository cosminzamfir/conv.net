package hr;

import java.util.ArrayList;
import java.util.List;

public class Output {

	private List<ResourceAssignment> assignments = new ArrayList<>();
	private List<Resource> availableResources = new ArrayList<>();
	private List<SkillInstance> uncoveredSkills = new ArrayList<>();

	public void addAssignment(ResourceAssignment value) {
		this.assignments.add(value);
	}
	
	public List<ResourceAssignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<ResourceAssignment> assignments) {
		this.assignments = assignments;
	}

	public List<Resource> getAvailableResources() {
		return availableResources;
	}

	public void setAvailableResources(List<Resource> availableResources) {
		this.availableResources = availableResources;
	}

	public List<SkillInstance> getUncoveredSkills() {
		return uncoveredSkills;
	}

	public void setUncoveredSkills(List<SkillInstance> uncoveredSkills) {
		this.uncoveredSkills = uncoveredSkills;
	}

}
