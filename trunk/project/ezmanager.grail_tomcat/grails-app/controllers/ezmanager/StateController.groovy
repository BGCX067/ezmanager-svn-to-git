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

import java.util.Date;

import ezmanager.State;

class StateController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {StateSearchCommand cmd ->
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def stateList

		def c = State.createCriteria()
		stateList = c.list(params) {

			if (cmd.name) {
				ilike("name", "%" + cmd.name + "%")
			}
			if (cmd.countryId) {
				and {
					if (cmd.countryId) {
						eq("country.countryId", cmd.countryId)
					}
				}
			}
		}

		return stateList
	}

	def list = { StateSearchCommand cmd ->

		def stateList = search(cmd)

		[name: cmd.name, countryId: cmd.countryId, stateInstanceList: stateList, stateInstanceTotal: stateList.totalCount]
	}

	def create = {
		def stateInstance = new State()
		stateInstance.properties = params
		return [stateInstance: stateInstance]
	}

	def save = {
		def stateInstance = new State(params)
		if (stateInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'state.label', default: 'State'), stateInstance.stateId])}"
			redirect(action: "show", id: stateInstance.stateId)
		}
		else {
			render(view: "create", model: [stateInstance: stateInstance])
		}
	}

	def show = {
		def stateInstance = State.get(params.id)
		if (!stateInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'state.label', default: 'State'), params.id])}"
			redirect(action: "list")
		}
		else {
			[stateInstance: stateInstance]
		}
	}

	def edit = {
		def stateInstance = State.get(params.id)
		if (!stateInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'state.label', default: 'State'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [stateInstance: stateInstance]
		}
	}

	def update = {
		def stateInstance = State.get(params.id)
		if (stateInstance) {
			stateInstance.properties = params
			if (!stateInstance.hasErrors() && stateInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'state.label', default: 'State'), stateInstance.stateId])}"
				redirect(action: "show", id: stateInstance.stateId)
			}
			else {
				render(view: "edit", model: [stateInstance: stateInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'state.label', default: 'State'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def stateInstance = State.get(params.id)
		if (stateInstance) {
			try {
				stateInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'state.label', default: 'State'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'state.label', default: 'State'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'state.label', default: 'State'), params.id])}"
			redirect(action: "list")
		}
	}
}

class StateSearchCommand {
	String name
	Long countryId
}