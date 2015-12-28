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

@Name("streetList")
public class StreetList extends EntityQuery<Street> {
	
	@Logger private Log log;

	/**
	 * 
	 */
	private static final long serialVersionUID = -166735901609778264L;

	private static final String EJBQL = "SELECT s FROM Street s " +
			                            "JOIN FETCH s.city c " +
			                            "JOIN FETCH c.state sta " +
			                            "JOIN FETCH sta.country co ";

	private static final String[] RESTRICTIONS = {
			"LOWER(s.name) LIKE LOWER(CONCAT(#{searchStreet.name},'%'))",
			"LOWER(c.name) LIKE LOWER(CONCAT(#{searchCity.name},'%'))",
			"LOWER(sta.name) LIKE LOWER(CONCAT(#{searchState.name},'%'))",
	        "LOWER(co.name) LIKE LOWER(CONCAT(#{searchCountry.name},'%'))",};

	private Street street = new Street();

	public StreetList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Street getStreet() {
		return street;
	}
}
