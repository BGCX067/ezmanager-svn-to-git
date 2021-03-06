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

class RoleIdentityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [roleIdentityInstanceList: RoleIdentity.list(params), roleIdentityInstanceTotal: RoleIdentity.count()]
    }

    def create = {
        def roleIdentityInstance = new RoleIdentity()
        roleIdentityInstance.properties = params
        return [roleIdentityInstance: roleIdentityInstance]
    }

    def save = {
        def roleIdentityInstance = new RoleIdentity(params)
        if (roleIdentityInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), roleIdentityInstance.roleIdentityId])}"
            redirect(action: "show", id: roleIdentityInstance.roleIdentityId)
        }
        else {
            render(view: "create", model: [roleIdentityInstance: roleIdentityInstance])
        }
    }

    def show = {
        def roleIdentityInstance = RoleIdentity.get(params.id)
        if (!roleIdentityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), params.id])}"
            redirect(action: "list")
        }
        else {
            [roleIdentityInstance: roleIdentityInstance]
        }
    }

    def edit = {
        def roleIdentityInstance = RoleIdentity.get(params.id)
        if (!roleIdentityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [roleIdentityInstance: roleIdentityInstance]
        }
    }

    def update = {
        def roleIdentityInstance = RoleIdentity.get(params.id)
        if (roleIdentityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (roleIdentityInstance.version > version) {
                    
                    roleIdentityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'roleIdentity.label', default: 'RoleIdentity')] as Object[], "Another user has updated this RoleIdentity while you were editing")
                    render(view: "edit", model: [roleIdentityInstance: roleIdentityInstance])
                    return
                }
            }
            roleIdentityInstance.properties = params
            if (!roleIdentityInstance.hasErrors() && roleIdentityInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), roleIdentityInstance.roleIdentityId])}"
                redirect(action: "show", id: roleIdentityInstance.roleIdentityId)
            }
            else {
                render(view: "edit", model: [roleIdentityInstance: roleIdentityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def roleIdentityInstance = RoleIdentity.get(params.id)
        if (roleIdentityInstance) {
            try {
                roleIdentityInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'roleIdentity.label', default: 'RoleIdentity'), params.id])}"
            redirect(action: "list")
        }
    }
}
