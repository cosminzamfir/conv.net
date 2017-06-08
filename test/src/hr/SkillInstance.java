package hr;

public class SkillInstance {

	private Skill skill;

	public static SkillInstance instance(Skill skill) {
		return new SkillInstance(skill);
	}
	private SkillInstance(Skill skill) {
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	@Override
	public String toString() {
		return skill.toString();
	}
	public double getCost() {
		return skill.getCost();
	}
	
	
	
	
}
