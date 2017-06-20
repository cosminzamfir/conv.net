package hr;

import java.util.ArrayList;
import java.util.List;

public class Input {

	private List<Skill> skills = new ArrayList<>();
	private List<Resource> resources = new ArrayList<>();
	private List<SkillInstance> skillInstances = new ArrayList<>();
	private List<Transformation> transformations = new ArrayList<>();

	
	public Input() {
		super();
	}

	public Input(List<Skill> skills, List<Resource> resources, List<SkillInstance> skillInstances,
			List<Transformation> transformations) {
		super();
		this.skills = skills;
		this.resources = resources;
		this.skillInstances = skillInstances;
		this.transformations = transformations;
	}

	public void addResource(Resource resource) {
		this.resources.add(resource);
	}

	public void addSkill(Skill skill) {
		this.skills.add(skill);
	}

	public void addSkillInstance(SkillInstance skillInstance) {
		this.skillInstances.add(skillInstance);
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<SkillInstance> getSkillInstances() {
		return skillInstances;
	}

	public void setSkillInstances(List<SkillInstance> skillInstances) {
		this.skillInstances = skillInstances;
	}

	public List<Transformation> getTransformations() {
		return transformations;
	}

	public void setTransformations(List<Transformation> transformations) {
		this.transformations = transformations;
	}

}
