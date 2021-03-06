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

class CountryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [countryInstanceList: Country.list(params), countryInstanceTotal: Country.count()]
    }

    def create = {
        def countryInstance = new Country()
        countryInstance.properties = params
        return [countryInstance: countryInstance]
    }

    def save = {
        def countryInstance = new Country(params)
        if (countryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'country.label', default: 'Country'), countryInstance.countryId])}"
            redirect(action: "show", id: countryInstance.countryId)
        }
        else {
            render(view: "create", model: [countryInstance: countryInstance])
        }
    }

    def show = {
        def countryInstance = Country.get(params.id)
        if (!countryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), params.id])}"
            redirect(action: "list")
        }
        else {
            [countryInstance: countryInstance]
        }
    }

    def edit = {
        def countryInstance = Country.get(params.id)
        if (!countryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [countryInstance: countryInstance]
        }
    }

    def update = {
        def countryInstance = Country.get(params.id)
        if (countryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (countryInstance.version > version) {
                    
                    countryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'country.label', default: 'Country')] as Object[], "Another user has updated this Country while you were editing")
                    render(view: "edit", model: [countryInstance: countryInstance])
                    return
                }
            }
            countryInstance.properties = params
            if (!countryInstance.hasErrors() && countryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'country.label', default: 'Country'), countryInstance.countryId])}"
                redirect(action: "show", id: countryInstance.countryId)
            }
            else {
                render(view: "edit", model: [countryInstance: countryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def countryInstance = Country.get(params.id)
        if (countryInstance) {
            try {
                countryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'country.label', default: 'Country'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'country.label', default: 'Country'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), params.id])}"
            redirect(action: "list")
        }
    }
}
