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

import javax.faces.event.ValueChangeEvent;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("streetHome")
public class StreetHome extends EntityHome<Street> {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5242988337830320275L;

	@In(create = true)
	CityHome cityHome;
	
	@Out(scope =ScopeType.CONVERSATION)
	private State mySelectedState;

	@DataModel protected List<State> myStates;
	
	@DataModel protected List<City> myCities;
	
	public StreetHome() {
		mySelectedState = new State();
	}
	
	public void setStreetStreetId(Long id) {
		setId(id);
	}

	public Long getStreetStreetId() {
		return (Long) getId();
	}

	@Factory(value = "street", scope = ScopeType.CONVERSATION)	
	@Override
	public Street getInstance() {
		return super.getInstance();
	}

	@Override
	protected Street loadInstance() {
		Street street =  (Street) getEntityManager().createQuery(
				"SELECT s FROM Street s " + 
				"JOIN FETCH s.city c " +
				"JOIN FETCH c.state st " + 
				"JOIN FETCH st.country c " +
				"WHERE s.streetId = :streetId").setParameter("streetId", getId()).getSingleResult();
		
		City city = street.getCity();
		
		mySelectedState = city.getState();
		
		refreshStates();
		refreshCities();
		
		return street;
		
	}

	@Override
	protected Street createInstance() {
		Street street = new Street();
		
		City city = new City();
		
		mySelectedState = new State();
		
		street.setCity(city);
		
		return street;
	}
	
	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		City city = cityHome.getDefinedInstance();
		if (city != null) {
			getInstance().setCity(city);
		}
	}

	public boolean isWired() {
		if (getInstance().getCity() == null)
			return false;
		return true;
	}

	public Street getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Address> getAddresses() {
		return getInstance() == null ? null : new ArrayList<Address>(
				getInstance().getAddresses());
	}

    public void changeCountryMenu(ValueChangeEvent event) {
        // Get selected country.
        Country country = (Country) event.getNewValue();
        mySelectedState.setCountry(country);
        
        refreshStates();
        refreshCities();
    }

    public void changeStateMenu(ValueChangeEvent event) {
        // Get selected state.
    	mySelectedState = (State) event.getNewValue();
    	City city = getInstance().getCity();
    	city.setState(mySelectedState);
    	refreshCities();
    }
    
    @SuppressWarnings("unchecked")
	public void refreshStates() {
    	Query query = getEntityManager().createQuery("SELECT s FROM State s JOIN FETCH s.country c WHERE s.country.countryId = :selectedCountryId");
    	query.setMaxResults(200);
    	query.setParameter("selectedCountryId", mySelectedState.getCountry().getCountryId());
    	myStates =  query.getResultList();   
   	
    }

    @SuppressWarnings("unchecked")
	public void refreshCities() {
    	Query query = getEntityManager().createQuery("SELECT c FROM City c JOIN FETCH c.state s WHERE c.state.stateId = :selectedStateId");
    	query.setMaxResults(200);
    	query.setParameter("selectedStateId", mySelectedState.getStateId());
    	myCities =  query.getResultList();   
   	
    }
    
}
