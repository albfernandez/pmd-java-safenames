package com.github.albfernandez.pmd.safenames;

import java.util.List;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTConstructorDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFormalParameter;
import net.sourceforge.pmd.lang.java.ast.ASTFormalParameters;
import net.sourceforge.pmd.lang.java.ast.ASTLocalVariableDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMarkerAnnotation;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclarator;
import net.sourceforge.pmd.lang.java.ast.ASTName;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclarator;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclaratorId;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class NamingConventionsRule extends AbstractJavaRule {

	public NamingConventionsRule() {
		super();
	}

	@Override
	public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
		String classOrInterfaceName = node.getImage();
		if (!isValidIdentifier(classOrInterfaceName)) {
			addViolation(data, node, classOrInterfaceName);
		}
		return super.visit(node, data);
	}
	
	@Override
	public Object visit(ASTConstructorDeclaration node, Object data) {
		// Constructor is not taken into account, because it's reported in className
		return super.visit(node, data);
	}

	@Override
	public Object visit(ASTMethodDeclarator node, Object data) {
		if (isOverriddenMethod(node)) {
			return super.visit(node, data);
		}
		String methodName = node.getImage();
		if (!isValidIdentifier(methodName)) {
			addViolation(data, node, methodName);
		}
		return super.visit(node, data);
	}

	@Override
	public Object visit(ASTFieldDeclaration node, Object data) {
		checkVariableDeclarators(node, data);
		return super.visit(node, data);
	}

	@Override
	public Object visit(ASTLocalVariableDeclaration node, Object data) {
		checkVariableDeclarators(node, data);
		return super.visit(node, data);
	}

	@Override
	public Object visit(ASTFormalParameters node, Object data) {
		for (ASTFormalParameter formalParameter : node.findChildrenOfType(ASTFormalParameter.class)) {
			for (ASTVariableDeclaratorId variableDeclaratorId : formalParameter
					.findChildrenOfType(ASTVariableDeclaratorId.class)) {
				String variableName = variableDeclaratorId.getImage();
				if (!isValidIdentifier(variableName)) {
					addViolation(data, variableDeclaratorId, variableName);
				}
			}
		}
		return super.visit(node, data);
	}

	private Object checkVariableDeclarators(Node root, Object data) {
		for (ASTVariableDeclarator variableDeclarator : root.findChildrenOfType(ASTVariableDeclarator.class)) {
			for (ASTVariableDeclaratorId variableDeclaratorId : variableDeclarator
					.findChildrenOfType(ASTVariableDeclaratorId.class)) {
				String variableName = variableDeclaratorId.getImage();
				if (!isValidIdentifier(variableName)) {
					addViolation(data, variableDeclaratorId, variableName);
				}
			}
		}
		return data;
	}

	protected static boolean isValidIdentifier(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		if ("_".equals(name)) {
			return false;
		}
		int size = name.length();
		for (int i = 0; i < size; i++) {
			char c = name.charAt(i);
			boolean valid = c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c == '_';
			if (!valid) {
				return false;
			}
		}
		return true;
	}

	private boolean isOverriddenMethod(ASTMethodDeclarator node) {
		ASTClassOrInterfaceBodyDeclaration declaration = node
				.getFirstParentOfType(ASTClassOrInterfaceBodyDeclaration.class);
		List<ASTMarkerAnnotation> annotations = declaration.findDescendantsOfType(ASTMarkerAnnotation.class);
		for (ASTMarkerAnnotation ann : annotations) {
			ASTName name = ann.getFirstChildOfType(ASTName.class);
			if (name != null && name.hasImageEqualTo("Override")) {
				return true;
			}
		}
		return false;
	}
}
