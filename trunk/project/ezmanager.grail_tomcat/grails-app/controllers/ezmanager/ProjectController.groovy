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

import ezmanager.Project;

class ProjectController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}


	def search = {ProjectSearchCommand cmd ->
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def projectList
		def c = Project.createCriteria()
		
		projectList = c.list(params) {
			log.info("cmd.name "+ cmd.name)
			if (cmd.name) {
				ilike("name", "%" + cmd.name + "%")
			}
			if (cmd.startDate || cmd.endDate || cmd.statusId) {
				and {
					if (cmd.startDate) {
						ge("startDate", cmd.startDate)
					}
					if (cmd.endDate) {
						le("endDate", cmd.endDate)
					}
					if (cmd.statusId) {
						eq("status.statusId", cmd.statusId)
					}
				}
			}
		}
		return projectList
	}


	def list = { ProjectSearchCommand cmd ->
		def projectList = search(cmd)

		[name: cmd.name, startDate: cmd.startDate, endDate: cmd.endDate, statusId: cmd.statusId, projectInstanceList: projectList, projectInstanceTotal: projectList.totalCount]
	}

	def create = {
		def projectInstance = new Project()
		projectInstance.properties = params
		return [projectInstance: projectInstance]
	}

	def save = {
		def projectInstance = new Project(params)
		if (projectInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.projectId])}"
			redirect(action: "show", id: projectInstance.projectId)
		}
		else {
			render(view: "create", model: [projectInstance: projectInstance])
		}
	}

	def show = {
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
		else {
			[projectInstance: projectInstance]
		}
	}

	def edit = {
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [projectInstance: projectInstance]
		}
	}

	def update = {
		def projectInstance = Project.get(params.id)
		
		if (projectInstance) {
			projectInstance.properties = params
			if (!projectInstance.hasErrors() && projectInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.projectId])}"
				redirect(action: "show", id: projectInstance.projectId)
			}
			else {
				render(view: "edit", model: [projectInstance: projectInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def projectInstance = Project.get(params.id)
		if (projectInstance) {
			try {
				projectInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
}

class ProjectSearchCommand {
	String name
	Date startDate
	Date endDate
	Long statusId
}