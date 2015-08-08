/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

package org.topdank.banalysis.filter;

public class ConstantFilter<T> implements Filter<T> {
	
	protected ConstantType type;
	protected T cst;
	
	public ConstantFilter() {
		cst = null;
		type = ConstantType.UNCERTAINTY_NULL;
	}
	
	public ConstantFilter(T cst) {
		if (cst == null) {
			cst = null;
			type = ConstantType.UNCERTAINTY_NULL;
		} else {
			this.cst = cst;
			type = ConstantType.NORMAL;
		}
	}
	
	@Override
	public boolean accept(T t) {
		if (type == ConstantType.UNCERTAINTY_NULL)
			return true;
		return cst.equals(t);
	}
	
	private static enum ConstantType {
		NORMAL(), UNCERTAINTY_NULL();
	}
}
