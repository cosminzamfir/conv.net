package hr;

public class Transformation {

	private Skill origin;
	private Skill target;
	private double cost;

	
	public Transformation() {
		super();
	}

	private Transformation(Skill origin, Skill target, double cost) {
		super();
		this.origin = origin;
		this.target = target;
		this.cost = cost;
	}

	public static Transformation instance(String origin, String target, double cost) {
		Skill o = Skill.get(origin);
		Skill t = Skill.get(target);
		return new Transformation(o, t, cost);
	}

	public Skill getOrigin() {
		return origin;
	}

	public void setOrigin(Skill origin) {
		this.origin = origin;
	}

	public Skill getTarget() {
		return target;
	}

	public void setTarget(Skill target) {
		this.target = target;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return origin.getName() + "->" + target.getName() + "[cost=" + cost + "]";
	}

}
