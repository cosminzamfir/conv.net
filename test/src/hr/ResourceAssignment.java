package hr;

public class ResourceAssignment {

	private Resource resource;
	private SkillInstance skill;
	private double cost;

	
	public ResourceAssignment() {
		super();
	}

	public ResourceAssignment(Resource resource, SkillInstance skill, double cost) {
		super();
		this.resource = resource;
		this.skill = skill;
		this.cost = cost;
	}

	public Resource getResource() {
		return resource;
	}

	public SkillInstance getSkill() {
		return skill;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String toString() {
		return resource.getName() + "->" + skill.getSkill().getName();
	}

}
