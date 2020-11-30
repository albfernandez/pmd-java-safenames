package com.github.albfernandez.pmd.safenames;

import static com.github.albfernandez.pmd.safenames.NamingConventionsRule.isValidIdentifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NamingConventionsRuleIsValidIdentifierTest {

	public NamingConventionsRuleIsValidIdentifierTest() {
		super();
	}
	
	@Test
	public void testIsValidIdentifier() {		
		Assertions.assertFalse(isValidIdentifier(null));
		Assertions.assertFalse(isValidIdentifier(""));
		Assertions.assertFalse(isValidIdentifier("_"));
		Assertions.assertFalse(isValidIdentifier(" "));
		Assertions.assertFalse(isValidIdentifier("spaces and spaces"));
		
		Assertions.assertFalse(isValidIdentifier("invalidEnEspañol"));
		Assertions.assertFalse(isValidIdentifier("a-b"));
		Assertions.assertFalse(isValidIdentifier("a+b"));
		
		Assertions.assertTrue(isValidIdentifier("test"));
		Assertions.assertTrue(isValidIdentifier("valid"));
		Assertions.assertTrue(isValidIdentifier("kk"));
		Assertions.assertTrue(isValidIdentifier("_isValid"));
		Assertions.assertTrue(isValidIdentifier("__"));
		
		
	}
}
