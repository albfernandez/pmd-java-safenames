package com.github.albfernandez.pmd.safenames;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class NamingConventionsRuleTest {

	public NamingConventionsRuleTest() {
		super();
	}
	
	@Test
	public void selfTest() throws Exception {
		Assertions.assertEquals(0, PMDRunner.run("src/test/java/com/github/albfernandez/pmd/safenames/"));
	}
	@Test
	public void testRejectedMethodsNames() throws Exception {
		Assertions.assertEquals(3, PMDRunner.run("src/test/java/examples/RejectedMethodNames.java"));
	}
	
	@Test
	public void testRejectedClassName() throws Exception {
		Assertions.assertEquals(1, PMDRunner.run("src/test/java/examples/RejectedClassNameÑo.java"));
	}
	
	@Test
	public void testRejectedClassName2() throws Exception {
		Assertions.assertEquals(1, PMDRunner.run("src/test/java/examples/RejectedClassNameÑo2.java"));
	}
	
	@Test
	public void testRejectedVariableNames() throws Exception {
		Assertions.assertEquals(3, PMDRunner.run("src/test/java/examples/RejectedVariableNames.java"));
	}
	
}
