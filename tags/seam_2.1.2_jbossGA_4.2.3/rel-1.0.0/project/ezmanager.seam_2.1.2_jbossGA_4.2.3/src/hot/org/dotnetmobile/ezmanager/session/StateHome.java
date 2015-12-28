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

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;


@Name("stateHome")
public class StateHome extends EntityHome<State> {
	
	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1269115237171580602L;
	
	@In(create = true)
	CountryHome countryHome;
	
	public void setStateStateId(Long id) {
		setId(id);
	}

	public Long getStateStateId() {
		return (Long) getId();
	}

	@Override
	protected State createInstance() {
		State state = new State();
		Country country = new Country();
		
		state.setCountry(country);
		
		return state;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		if (getInstance().getCountry() == null)
			return false;
		return true;
	}

	@Factory(value = "state", scope = ScopeType.EVENT)
	@Override
	public State getInstance() {
		return super.getInstance();
	}

	public State getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<City> getCities() {
		return getInstance() == null ? null : new ArrayList<City>(getInstance()
				.getCities());
	}
}
