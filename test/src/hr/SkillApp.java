package hr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SkillApp {

	private class DoubleHolder {
		private double d;

		public DoubleHolder(double d) {
			this.d = d;
		}

		public double get() {
			return d;
		}

		public double add(double value) {
			d += value;
			return d;
		}

	}

	private static final String SKILL = "Skill:";
	private static final String TARGET = "Target:";
	private static final String TRANSFORMATION = "Transformation:";
	private static final String RESOURCE = "Resource:";

	private List<Resource> resources = new ArrayList<>();
	private List<SkillInstance> target = new ArrayList<>();
	private Transformations transformations = Transformations.instance();
	private List<ResourceAssignment> covered = new ArrayList<>();
	private List<SkillInstance> uncovered = new ArrayList<>();
	private List<Resource> availableResources;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: java SkillApp <input_file_name>");
			System.exit(-1);
		}
		String fileName = args[0];
		SkillApp app = new SkillApp(fileName);
		System.out.println(app.input());
		app.process();
		System.out.println();
		System.out.println(app.output());

	}

	private SkillApp(String fileName) {
		load(fileName);
	}

	private void load(String fileName) {
		BufferedReader reader = null;
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				throw new FileNotFoundException(fileName);
			}
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#") || line.trim().isEmpty()) {
					continue;
				} else if (line.startsWith(SKILL)) {
					parseSkill(line.replace(SKILL, ""));
				} else if (line.startsWith(TARGET)) {
					parseTarget(line.replace(TARGET, ""));
				} else if (line.startsWith(TRANSFORMATION)) {
					parseTransformation(line.replace(TRANSFORMATION, ""));
				} else if (line.startsWith(RESOURCE)) {
					parseResource(line.replaceAll(RESOURCE, ""));
				} else {
					throw new RuntimeException("Invalid line: " + line);
				}
			}
			availableResources = new ArrayList<>(resources);
			uncovered = new ArrayList<>(target);

		} catch (Exception e) {
			throw new RuntimeException("Error loading file " + fileName, e);
		} finally {
			try {
				reader.close();
			} catch (Exception ignore) {
			}
		}
	}

	private void parseSkill(String s) {
		String[] tokens = parse(s, 2);
		Skill.instance(tokens[0], Double.valueOf(tokens[1]));
	}

	private void parseResource(String s) {
		String[] tokens = parse(s, -1);
		String[] skills = new String[tokens.length - 1];
		for (int i = 0; i < skills.length; i++) {
			skills[i] = tokens[i + 1];
		}
		resources.add(Resource.instance(tokens[0], skills));
	}

	private void parseTarget(String s) {
		String[] tokens = parse(s, 2);
		int n = Integer.parseInt(tokens[1]);
		for (int i = 0; i < n; i++) {
			target.add(SkillInstance.instance(Skill.get(tokens[0])));
		}
	}

	private void parseTransformation(String s) {
		String[] tokens = parse(s, 3);
		transformations.addTransformation(tokens[0], tokens[1], Double.valueOf(tokens[2]));
	}

	private String[] parse(String s, int tokens) {
		String[] res = s.split(",");
		if (res.length != tokens && tokens > 0) {
			throw new RuntimeException("Invalid token count for s. Expected: " + tokens);
		}
		return res;
	}

	public String input() {
		StringBuilder res = new StringBuilder();
		res.append("Current resources:\n==================\n");
		resources.stream().forEach(e -> res.append(e).append("\n"));

		res.append("\n");
		res.append("Target skills:\n==============\n");
		collect(target).entrySet().stream().forEach(e -> res.append(e.getKey() + "[" + e.getValue() + "]").append("\n"));

		res.append("\n");
		res.append(transformations);

		return res.toString();
	}

	public String output() {
		StringBuilder res = new StringBuilder();
		res.append("Assignments:\n===============\n");
		covered.stream().forEach(e -> res.append(e).append("\n"));

		res.append("\n");
		res.append("Uncovered skills:\n=================\n");
		collect(uncovered).entrySet().stream().forEach(e -> res.append(e.getKey() + "[" + e.getValue() + "]").append("\n"));

		res.append("\n");
		res.append("Available resources:\n====================\n");
		if (availableResources.isEmpty()) {
			res.append("None\n");
		} else {
			availableResources.stream().forEach(e -> res.append(e).append("\n"));
		}

		res.append("\n");
		res.append("Total cost:" + totalCost() + "\n");
		return res.toString();
	}

	public void process() {
		System.out.println("Processing. Initial cost: " + totalCost() + " ...");
		Iterator<Resource> resourceIter = resources.iterator();
		if (uncovered.isEmpty()) {
			System.out.println("Done.");
			return;
		}
		while (resourceIter.hasNext()) {
			Resource resource = resourceIter.next();
			SkillInstance targetSkill = null;
			double maxSaving = 0;
			double assignmentCost = 0;
			for (SkillInstance skillInstance : uncovered) {
				double cost = assignmentCost(resource, skillInstance);
				double saving = skillInstance.getCost() - cost;
				if (saving > maxSaving) {
					targetSkill = skillInstance;
					maxSaving = saving;
					assignmentCost = cost;
				}
			}
			if (maxSaving > 0) {
				uncovered.remove(targetSkill);
				ResourceAssignment ass = new ResourceAssignment(resource, targetSkill, assignmentCost);
				covered.add(ass);
				System.out.println("Created assignment " + ass + 
						";Saving: " + maxSaving + 
						"; New cost: " + totalCost());
				availableResources.remove(resource);
			}

		}
		System.out.println("Done");
	}

	private double totalCost() {
		DoubleHolder res = new DoubleHolder(0);
		uncovered.stream().forEach(skill -> res.add(skill.getCost()));
		covered.stream().forEach(assignment -> res.add(assignment.getCost()));
		return res.get();
	}

	private double assignmentCost(Resource resource, SkillInstance target) {
		if (resource.hasSkill(target.getSkill())) {
			return 0;
		}
		double minCost = Double.POSITIVE_INFINITY;
		for (Skill skill : resource.getSkills()) {
			Transformation t = Transformations.instance().get(skill, target.getSkill());
			if (t != null && t.getCost() < minCost) {
				minCost = t.getCost();
			}
		}
		return minCost;
	}

	private Map<String, Integer> collect(List<SkillInstance> l) {
		Map<String, Integer> res = new LinkedHashMap<>();
		for (SkillInstance elem : l) {
			if (!res.containsKey(elem.toString())) {
				res.put(elem.toString(), 0);
			}
			res.put(elem.toString(), res.get(elem.toString()) + 1);
		}
		return res;
	}

}
