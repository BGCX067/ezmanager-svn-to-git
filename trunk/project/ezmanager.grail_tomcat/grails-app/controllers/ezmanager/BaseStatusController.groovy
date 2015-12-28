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
 
class BaseStatusController {
	String searchFilter
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}

	def search = {
		def statusList
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		searchFilter = params.searchFilter
		
		def c = BaseStatus.createCriteria()
		
		statusList = c.list(params) {
			if (searchFilter) {
				ilike("name", "%" + params.searchFilter + "%")
			}
		}
		
		return statusList
	}	
	
	def list = {
		def statusList = search()

		[searchFilter: searchFilter, baseStatusInstanceList: statusList, baseStatusInstanceTotal: statusList.totalCount]
	}
	

	def create = {
		def baseStatusInstance = new BaseStatus()
		baseStatusInstance.statusId = -1
		baseStatusInstance.properties = params
		return [baseStatusInstance: baseStatusInstance]
	}
	
	def save = {
		def baseStatusInstance = new BaseStatus(params)
		if (baseStatusInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'baseStatus.label', default: 'Status'), baseStatusInstance.statusId])}"
			redirect(action: "show", id: baseStatusInstance.statusId)
		}
		else {
			render(view: "create", model: [baseStatusInstance: baseStatusInstance])
		}
	}
	
	def show = {
		def baseStatusInstance = BaseStatus.get(params.id)
		if (!baseStatusInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseStatus.label', default: 'Status'), params.id])}"
			redirect(action: "list")
		}
		else {
			[baseStatusInstance: baseStatusInstance]
		}
	}
	
	def edit = {
		def baseStatusInstance = BaseStatus.get(params.id)
		if (!baseStatusInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseStatus.label', default: 'Status'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [baseStatusInstance: baseStatusInstance]
		}
	}
	
	def update = {
		def baseStatusInstance = BaseStatus.get(params.id)
		if (baseStatusInstance) {
			baseStatusInstance.properties = params
			if (!baseStatusInstance.hasErrors() && baseStatusInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'baseStatus.label', default: 'Status'), baseStatusInstance.statusId])}"
				redirect(action: "show", id: baseStatusInstance.statusId)
			}
			else {
				render(view: "edit", model: [baseStatusInstance: baseStatusInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseStatus.label', default: 'Status'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def baseStatusInstance = BaseStatus.get(params.id)
		if (baseStatusInstance) {
			try {
				baseStatusInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'baseStatus.label', default: 'Status'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'baseStatus.label', default: 'Status'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseStatus.label', default: 'Status'), params.id])}"
			redirect(action: "list")
		}
	}
}
