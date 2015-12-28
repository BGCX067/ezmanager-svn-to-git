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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("baseVatHome")
public class BaseVatHome extends EntityHome<BaseVat> {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6749162697657500848L;
	
	@In(create = true)
	BaseCurrencyHome baseCurrencyHome;

	public void setBaseVatVatId(Long id) {
		setId(id);
	}

	public Long getBaseVatVatId() {
		return (Long) getId();
	}

	@Override
	protected BaseVat createInstance() {
		BaseVat baseVat = new BaseVat();
		BaseCurrency currency = new BaseCurrency();
		
		baseVat.setBaseCurrency(currency);
		
		return baseVat;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		BaseCurrency baseCurrency = baseCurrencyHome.getDefinedInstance();
		if (baseCurrency != null) {
			getInstance().setBaseCurrency(baseCurrency);
		}
	}

	public boolean isWired() {
		if (getInstance().getBaseCurrency() == null)
			return false;
		return true;
	}

	public BaseVat getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Cost> getCosts() {
		return getInstance() == null ? null : new ArrayList<Cost>(getInstance()
				.getCosts());
	}

}
