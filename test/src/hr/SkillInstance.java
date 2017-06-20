package hr;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SkillInstance {

	private Skill skill;

	
	public SkillInstance() {
		super();
	}
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
	
	@JsonIgnore
	public double getCost() {
		return skill.getCost();
	}
	
	
	
	
}
