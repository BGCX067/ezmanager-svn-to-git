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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("addressHome")
public class AddressHome extends EntityHome<Address> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2693852061969669529L;
	
	@In(create = true)
	StreetHome streetHome;

	public void setAddressAddressId(Long id) {
		setId(id);
	}

	public Long getAddressAddressId() {
		return (Long) getId();
	}

	@Override
	protected Address createInstance() {
		Address address = new Address();
		return address;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Street street = streetHome.getDefinedInstance();
		if (street != null) {
			getInstance().setStreet(street);
		}
	}

	public boolean isWired() {
		if (getInstance().getStreet() == null)
			return false;
		return true;
	}

	public Address getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
