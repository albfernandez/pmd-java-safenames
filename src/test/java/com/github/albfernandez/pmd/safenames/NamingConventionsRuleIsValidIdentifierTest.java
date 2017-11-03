package com.github.albfernandez.pmd.safenames;

import static com.github.albfernandez.pmd.safenames.NamingConventionsRule.isValidIdentifier;

import org.junit.Assert;
import org.junit.Test;

public class NamingConventionsRuleIsValidIdentifierTest {

	public NamingConventionsRuleIsValidIdentifierTest() {
		super();
	}
	
	@Test
	public void testIsValidIdentifier() {		
		Assert.assertFalse(isValidIdentifier(null));
		Assert.assertFalse(isValidIdentifier(""));
		Assert.assertFalse(isValidIdentifier("_"));
		Assert.assertFalse(isValidIdentifier(" "));
		Assert.assertFalse(isValidIdentifier("spaces and spaces"));
		
		Assert.assertFalse(isValidIdentifier("invalidEnEspañol"));
		Assert.assertFalse(isValidIdentifier("a-b"));
		Assert.assertFalse(isValidIdentifier("a+b"));
		
		Assert.assertTrue(isValidIdentifier("test"));
		Assert.assertTrue(isValidIdentifier("valid"));
		Assert.assertTrue(isValidIdentifier("kk"));
		Assert.assertTrue(isValidIdentifier("_isValid"));
		Assert.assertTrue(isValidIdentifier("__"));
		
		
	}
}
