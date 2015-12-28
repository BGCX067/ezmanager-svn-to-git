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

class CityController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {CitySearchCommand cmd ->

		def cityList

		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def c = City.createCriteria()

		cityList = c.list(params) {
			if (cmd.name) {
				ilike("name", "%" + cmd.name + "%")
			}
			and {
				if (cmd.stateId) {
					eq("state.stateId", cmd.stateId)
				}
			}
		}

		return cityList
	}

	def list = { CitySearchCommand cmd ->

		def cityList = search(cmd)

		[name: cmd.name, stateId: cmd.stateId, cityInstanceList: cityList, cityInstanceTotal: cityList.totalCount]
	}

	def create = {
		def cityInstance = new City()
		cityInstance.properties = params
		return [cityInstance: cityInstance]
	}

	def save = {
		def cityInstance = new City(params)
		if (cityInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'city.label', default: 'City'), cityInstance.cityId])}"
			redirect(action: "show", id: cityInstance.cityId)
		}
		else {
			render(view: "create", model: [cityInstance: cityInstance])
		}
	}

	def show = {
		def cityInstance = City.get(params.id)
		if (!cityInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'city.label', default: 'City'), params.id])}"
			redirect(action: "list")
		}
		else {
			[cityInstance: cityInstance]
		}
	}

	def edit = {
		def cityInstance = City.get(params.id)
		if (!cityInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'city.label', default: 'City'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [cityInstance: cityInstance]
		}
	}

	def update = {
		def cityInstance = City.get(params.id)
		if (cityInstance) {
			cityInstance.properties = params
			if (!cityInstance.hasErrors() && cityInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'city.label', default: 'City'), cityInstance.cityId])}"
				redirect(action: "show", id: cityInstance.cityId)
			}
			else {
				render(view: "edit", model: [cityInstance: cityInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'city.label', default: 'City'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def cityInstance = City.get(params.id)
		if (cityInstance) {
			try {
				cityInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'city.label', default: 'City'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'city.label', default: 'City'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'city.label', default: 'City'), params.id])}"
			redirect(action: "list")
		}
	}
}

class CitySearchCommand {
	String name
	Long stateId
}