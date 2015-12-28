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

import org.dotnetmobile.ezmanager.entity.City;
import org.dotnetmobile.ezmanager.entity.Country;
import org.dotnetmobile.ezmanager.entity.State;
import org.dotnetmobile.ezmanager.entity.Street;
import org.dotnetmobile.ezmanager.session.StateHome;
  
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("cityHome")
public class CityHome extends EntityHome<City> {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5018419304901105914L;
	
	@In(create = true)
	StateHome stateHome;

	public void setCityCityId(Long id) {
		setId(id);
	}

	public Long getCityCityId() {
		return (Long) getId();
	}

	@Override
	protected City createInstance() {
		City city = new City();
	
		State state = new State();
		
		city.setState(state);
		
		return city;
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
		if (getInstance().getState() == null)
			return false;
		return true;
	}

	public City getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Street> getStreets() {
		return getInstance() == null ? null : new ArrayList<Street>(
				getInstance().getStreets());
	}

	@Factory(value = "city", scope = ScopeType.CONVERSATION)	
	@Override
	public City getInstance() {
		return super.getInstance();
	}

	@Override
	protected City loadInstance() {
		return (City) getEntityManager().createQuery(
				"select city from City city " + 
				"join fetch city.state state " + 
				"where city.cityId = :cityId").setParameter("cityId", getId()).getSingleResult();
	}

    public void changeCountryMenu(ValueChangeEvent event) {
        // Get selected city.
        Country country = (Country) event.getNewValue();
        State state = getInstance().getState();
        state.setCountry(country);
//        getInstance().setState(state);
    }

}
