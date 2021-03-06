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

package org.dotnetmobile.ezmanager.entity;

// Generated Jan 3, 2010 6:01:01 PM by Hibernate Tools 3.2.5.Beta

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;


/**
 * BaseVat generated by hbm2java
 */
@Entity
@Table(name = "BASE_VAT", schema = "EZMANAGER")
public class BaseVat implements java.io.Serializable {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4035655304504752458L;
	
	private long vatId;
	private BaseCurrency baseCurrency;
	private String name;
	private BigDecimal value;
	private Set<Cost> costs = new HashSet<Cost>(0);

	public BaseVat() {
	}

	public BaseVat(long vatId, BaseCurrency baseCurrency, String name,
			BigDecimal value) {
		this.vatId = vatId;
		this.baseCurrency = baseCurrency;
		this.name = name;
		this.value = value;
	}

	public BaseVat(long vatId, BaseCurrency baseCurrency, String name,
			BigDecimal value, Set<Cost> costs) {
		this.vatId = vatId;
		this.baseCurrency = baseCurrency;
		this.name = name;
		this.value = value;
		this.costs = costs;
	}

	@Id
	@GeneratedValue(generator = "seqBaseVat")
	@SequenceGenerator(name="seqBaseVat", sequenceName = "SEQ_BASE_VAT")
	@Column(name = "VAT_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getVatId() {
		return this.vatId;
	}

	public void setVatId(long vatId) {
		this.vatId = vatId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY_ID", nullable = false)
	@NotNull
	public BaseCurrency getBaseCurrency() {
		return this.baseCurrency;
	}

	public void setBaseCurrency(BaseCurrency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VALUE", nullable = false, precision = 4)
	@NotNull
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "baseVat")
	public Set<Cost> getCosts() {
		return this.costs;
	}

	public void setCosts(Set<Cost> costs) {
		this.costs = costs;
	}

}
