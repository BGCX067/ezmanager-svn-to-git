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

class UserIdentityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userIdentityInstanceList: UserIdentity.list(params), userIdentityInstanceTotal: UserIdentity.count()]
    }

    def create = {
        def userIdentityInstance = new UserIdentity()
        userIdentityInstance.properties = params
        return [userIdentityInstance: userIdentityInstance]
    }

    def save = {
        def userIdentityInstance = new UserIdentity(params)
        if (userIdentityInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), userIdentityInstance.userIdentityId])}"
            redirect(action: "show", id: userIdentityInstance.userIdentityId)
        }
        else {
            render(view: "create", model: [userIdentityInstance: userIdentityInstance])
        }
    }

    def show = {
        def userIdentityInstance = UserIdentity.get(params.id)
        if (!userIdentityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userIdentityInstance: userIdentityInstance]
        }
    }

    def edit = {
        def userIdentityInstance = UserIdentity.get(params.id)
        if (!userIdentityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userIdentityInstance: userIdentityInstance]
        }
    }

    def update = {
        def userIdentityInstance = UserIdentity.get(params.id)
        if (userIdentityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userIdentityInstance.version > version) {
                    
                    userIdentityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userIdentity.label', default: 'UserIdentity')] as Object[], "Another user has updated this UserIdentity while you were editing")
                    render(view: "edit", model: [userIdentityInstance: userIdentityInstance])
                    return
                }
            }
            userIdentityInstance.properties = params
            if (!userIdentityInstance.hasErrors() && userIdentityInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), userIdentityInstance.userIdentityId])}"
                redirect(action: "show", id: userIdentityInstance.userIdentityId)
            }
            else {
                render(view: "edit", model: [userIdentityInstance: userIdentityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userIdentityInstance = UserIdentity.get(params.id)
        if (userIdentityInstance) {
            try {
                userIdentityInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userIdentity.label', default: 'UserIdentity'), params.id])}"
            redirect(action: "list")
        }
    }
}
