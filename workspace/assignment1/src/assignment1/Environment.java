package assignment1;

import java.util.Random;

public class Environment {

	private boolean leftDirty;
	private boolean rightDirty;

	public Environment() {
	
		double leftRandom = Math.random();
		double rightRandom = Math.random();
		if(leftRandom >= 0.5) {
		this.leftDirty = true;
		} else {
			this.leftDirty = false;
		}
		if(rightRandom >= 0.5) {
			this.rightDirty = true;
		} else {
			this.rightDirty = false;
		}
	}

	public boolean isDirty(String location) {
		if (location == "Left") {
			if (leftDirty) {
				return true;
				
			} else {
				return false;
			}
		} else {
			if (rightDirty) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public String getLeft() {
		String string = "";
		if(leftDirty) {
			string = "Dirty";
		} else {
			string = "Clean";
		}
		return string;
	}
	
	public String getRight() {
		String string = "";
		if(rightDirty) {
			string = "Dirty";
		} else {
			string = "Clean";
		}
		return string;
	}
	
	public boolean setLeft() {
		leftDirty = false;
		return this.leftDirty;
	}
	
	public boolean setRight() {
		rightDirty = false;
		return this.rightDirty;
	}
}
