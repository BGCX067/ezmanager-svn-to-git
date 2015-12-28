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
 * Street generated by hbm2java
 */
@Entity
@Table(name = "STREET", schema = "EZMANAGER")
public class Street implements java.io.Serializable {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7428847915687308743L;
	
	private long streetId;
	private City city;
	private String name;
	private String remark;
	private Set<Address> addresses = new HashSet<Address>(0);

	public Street() {
	}

	public Street(long streetId, City city, String name) {
		this.streetId = streetId;
		this.city = city;
		this.name = name;
	}

	public Street(long streetId, City city, String name, String remark,
			Set<Address> addresses) {
		this.streetId = streetId;
		this.city = city;
		this.name = name;
		this.remark = remark;
		this.addresses = addresses;
	}

	@Id
	@GeneratedValue(generator = "seqStreet")
	@SequenceGenerator(name="seqStreet", sequenceName = "SEQ_STREET")
	@Column(name = "STREET_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getStreetId() {
		return this.streetId;
	}

	public void setStreetId(long streetId) {
		this.streetId = streetId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID", nullable = false)
	@NotNull
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "NAME", nullable = false, length = 200)
	@NotNull
	@Length(max = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMARK", length = 500)
	@Length(max = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
