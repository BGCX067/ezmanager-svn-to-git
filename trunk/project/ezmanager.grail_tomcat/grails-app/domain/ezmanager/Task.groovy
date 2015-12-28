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
 * The Task entity.
 *
 * @author: Michel Petrovic, dotnetmobile@gmail.com    
 *
 *
 */
class Task {
    static mapping = {
         table 'TASK'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         userIdentityList column:'TASK_TASK_ID',joinTable:'USER_TASK'
         id name:"taskId", generator:'sequence', columns:'TASK_ID', params:[sequence:'SEQ_TASK']
         project column:'PROJECT_ID'
         status column:'STATUS_ID'
		 
		 columns {
			 name column:'NAME'
			 position column:'POSITION'
			 startDate column:'START_DATE'
			 endDate column:'END_DATE'
		 }

    }
    Long taskId
    String name
    Long position
    Date startDate
    Date endDate
    // Relation
    Project project
    // Relation
    BaseStatus status

    static hasMany = [ userIdentityList : UserIdentity ]

    static constraints = {
        taskId(nullable: true, size: 0..10)
        name(nullable: false, size: 1..100, blank: false)
        position(size: 0..10)
        startDate(nullable: true)
        endDate(nullable: true, validator: { val, obj -> val?.after(obj.startDate)})
        project()
        status()
        userIdentityList()
    }
    String toString() {
        return "${taskId}" 
    }
}
