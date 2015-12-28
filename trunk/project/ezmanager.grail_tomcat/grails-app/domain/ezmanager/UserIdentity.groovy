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
 * The UserIdentity entity.
 *
 * @author: Michel Petrovic, dotnetmobile@gmail.com    
 *
 *
 */
class UserIdentity {
	static mapping = {
		table 'USER_IDENTITY'
		// version is set to false, because this isn't available by default for legacy databases
		version false
		roleIdentityList column:'USER_IDENTITY_USER_IDENTITY_ID',joinTable:'USER_ROLE'
		taskList column:'USER_IDENTITY_USER_IDENTITY_ID',joinTable:'USER_TASK'
		id name:"userIdentityId", generator:'sequence', columns:'USER_IDENTITY_ID', params:[sequence:'SEQ_USER_IDENTITY']

		columns {
			name column:'NAME'
			password column:'PASSWORD'
		}
	}
	Long userIdentityId
	String name
	String password
	String isActive

	static hasMany = [ roleIdentityList : RoleIdentity , taskList : Task ]

	static belongsTo = [RoleIdentity , Task]

	static constraints = {
		userIdentityId(nullable: true, size: 0..10)
		name(nullable:false, size: 1..50, blank: false)
		password(nullable: false, size: 1..50, blank: false)
		isActive(nullable: false, size: 1..1, blank: false)
		roleIdentityList()
		taskList()
	}
	String toString() {
		return "${userIdentityId}"
	}
}
