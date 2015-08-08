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

package org.nullbool.piexternal.game.api.wrappers;

import org.nullbool.piexternal.game.api.accessors.IWrappedException;

/**
 * @author Bibl (don't ban me pls)
 * @created 26 Jun 2015 00:46:02
 */
public class WrappedException implements IWrappedException {

	private final IWrappedException _obj;
	
	public WrappedException(IWrappedException obj) {
		this._obj = obj;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#getThrowable()
	 */
	@Override
	public Throwable getThrowable() {
		return _obj.getThrowable();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#getReason_()
	 */
	@Override
	public String getReason_() {
		return _obj.getReason_();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#setReason_(java.lang.String)
	 */
	@Override
	public void setReason_(String var1) {
		_obj.setReason_(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#setThrowable(java.lang.Throwable)
	 */
	@Override
	public void setThrowable(Throwable var1) {
		_obj.setThrowable(var1);		
	}
}
