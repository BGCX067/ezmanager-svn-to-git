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
package ezmanager


//package org.dotnetmobile.ezmanager.domain
/**
 * The Customer entity.
 *
 * @author: Michel Petrovic, dotnetmobile@gmail.com    
 *
 *
 */
class Customer {
    static mapping = {
         table 'CUSTOMER'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         addressList column:'CUSTOMER_ID',joinTable:'CUSTOMER_ADDRESS'
         invoiceList column:'CUSTOMER_ID',joinTable:'INVOICE_CUSTOMER'
         projectList column:'CUSTOMER_ID',joinTable:'PROJECT_CUSTOMER'
         id name:"customerId", generator:'sequence', columns:'CUSTOMER_ID', params:[sequence:'SEQ_CUSTOMER']
		 
		 columns {
			 name column:'NAME'
		 }
 
    }
    Long customerId
    String name

    static hasMany = [ addressList : Address , invoiceList : Invoice , projectList : Project ]

    static belongsTo = [  Address ]

    static constraints = {
        customerId(nullable: true, size: 0..10)
        name(nullable:false, size: 1..100, blank: false)
        addressList()
        invoiceList()
        projectList()
    }
    String toString() {
        return "${customerId}" 
    }
}
