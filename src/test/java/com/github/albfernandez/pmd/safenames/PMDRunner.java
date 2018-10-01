package com.github.albfernandez.pmd.safenames;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.cli.PMDCommandLineInterface;
import net.sourceforge.pmd.cli.PMDParameters;

public final class PMDRunner {

	public static final String RULESET_DEFAULT = "rulesets/safenames/safenames.xml";

	private PMDRunner() {
		throw new AssertionError("No instances allowed");
	}

	public static int run(String directory, String ruleset) throws Exception {
		int violations = run(new String[] { "-d", directory, "-R", ruleset, "-f", "text", "-language", "java" });
		return violations;

	}

	public static int run(String directory) throws Exception {
		return run(directory, RULESET_DEFAULT);
	}

	public static int run(String[] args) throws Exception {
		final PMDParameters params = PMDCommandLineInterface.extractParameters(new PMDParameters(), args, "pmd");
		final PMDConfiguration configuration = params.toConfiguration();

		try {
			int violations = PMD.doPMD(configuration);
			return violations;
		} catch (Exception e) {
			throw e;
		}
	}
}
