/*  <EzManager: Web application for activities follow up. As main features, you can create projects, then associate tasks and calculate their corresponding invoices. Each project is associated to one or more customers identified by their addressee>
    Copyright (C) <2010>  <Michel Petrovic> <email: dotnetmobile@gmail.com>

    Contributor(s): _____________________.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.dotnetmobile.ezmanager.session;

import org.dotnetmobile.ezmanager.entity.*;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

import java.util.Arrays;

@Name("baseStatusList")
public class BaseStatusList extends EntityQuery<BaseStatus> {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 907148451245883675L;

	private static final String EJBQL = "select baseStatus from BaseStatus baseStatus";

	private static final String[] RESTRICTIONS = { "lower(baseStatus.name) like lower(concat(#{baseStatusList.baseStatus.name},'%'))", };

	private BaseStatus baseStatus = new BaseStatus();

	public BaseStatusList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public BaseStatus getBaseStatus() {
		return baseStatus;
	}
}
