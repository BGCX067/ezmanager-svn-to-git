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
 
class StreetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def search = {StreetSearchCommand cmd ->

		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def streetList
		
		def c = Street.createCriteria()
		streetList = c.list(params) {
			
			if (cmd.name) {
				ilike("name", "%" + cmd.name + "%")
			}
			if (cmd.cityId) {
				and {
					if (cmd.cityId) {
						eq("city.cityId", cmd.cityId)
					}
				}
			}
		}

		return streetList
	}
	
    def list = { StreetSearchCommand cmd ->

		def streetList = search(cmd)
		
		[name: cmd.name, cityId: cmd.cityId, streetInstanceList: streetList, streetInstanceTotal: streetList.totalCount]
    }

    def create = {
        def streetInstance = new Street()
        streetInstance.properties = params
        return [streetInstance: streetInstance]
    }

    def save = {
        def streetInstance = new Street(params)
        if (streetInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'street.label', default: 'Street'), streetInstance.streetId])}"
            redirect(action: "show", id: streetInstance.streetId)
        }
        else {
            render(view: "create", model: [streetInstance: streetInstance])
        }
    }

    def show = {
        def streetInstance = Street.get(params.id)
        if (!streetInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'street.label', default: 'Street'), params.id])}"
            redirect(action: "list")
        }
        else {
            [streetInstance: streetInstance]
        }
    }

    def edit = {
        def streetInstance = Street.get(params.id)
        if (!streetInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'street.label', default: 'Street'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [streetInstance: streetInstance]
        }
    }

    def update = {
        def streetInstance = Street.get(params.id)
        if (streetInstance) {
            streetInstance.properties = params
            if (!streetInstance.hasErrors() && streetInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'street.label', default: 'Street'), streetInstance.streetId])}"
                redirect(action: "show", id: streetInstance.streetId)
            }
            else {
                render(view: "edit", model: [streetInstance: streetInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'street.label', default: 'Street'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def streetInstance = Street.get(params.id)
        if (streetInstance) {
            try {
                streetInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'street.label', default: 'Street'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'street.label', default: 'Street'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'street.label', default: 'Street'), params.id])}"
            redirect(action: "list")
        }
    }
}

class StreetSearchCommand {
	String name
	Long cityId
}