package org.nullbool.pi.core.engine.impl;

import java.util.Set;

public class SetHelper {

	public static int getLargest(Set<Integer> ints) {
		if (ints == null)
			return -1;
		int largest = -1;
		for (int i : ints) {
			if (i > largest) {
				largest = i;
			}
		}
		return largest;
	}
}