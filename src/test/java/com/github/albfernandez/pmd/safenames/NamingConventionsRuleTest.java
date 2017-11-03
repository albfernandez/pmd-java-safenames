package com.github.albfernandez.pmd.safenames;

import org.junit.Assert;
import org.junit.Test;


public class NamingConventionsRuleTest {

	public NamingConventionsRuleTest() {
		super();
	}
	
	@Test
	public void selfTest() throws Exception {
		Assert.assertEquals(0, PMDRunner.run("src/test/java/com/github/albfernandez/pmd/safenames/"));
	}
	@Test
	public void testRejectedMethodsNames() throws Exception {
		Assert.assertEquals(3, PMDRunner.run("src/test/java/examples/RejectedMethodNames.java"));
	}
	
	@Test
	public void testRejectedClassName() throws Exception {
		Assert.assertEquals(1, PMDRunner.run("src/test/java/examples/RejectedClassNameÑo.java"));
	}
	
	@Test
	public void testRejectedClassName2() throws Exception {
		Assert.assertEquals(1, PMDRunner.run("src/test/java/examples/RejectedClassNameÑo2.java"));
	}
	
	@Test
	public void testRejectedVariableNames() throws Exception {
		Assert.assertEquals(3, PMDRunner.run("src/test/java/examples/RejectedVariableNames.java"));
	}
	
}
