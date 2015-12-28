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
package ezmanager.taglib.jquery

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * The JqueryDatePickerTagLib taglib.
 *
 * @author: Michel Petrovic, dotnetmobile@gmail.com
 *
 *
 */

class JqueryDatePickerTagLib {
	def jqDatePicker = { attrs, body ->
		def out = out
		def name = attrs.name
		def id = attrs.id ?: name
		String value = attrs.value  ?: ""
		String readonly = attrs.readonly ?: ""

		if (value && readonly) {
			// Sql date default date format
			def DateFormat odf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			// EzManager default date format
			def DateFormat df = new SimpleDateFormat( message(code:"default.date.format"));
			value = df.format(odf.parse(value))
		}

		// Create date text field and supporting hidden text fields
		String input = "<input type=\"text\" name=\"${name}\" id=\"${id}\" value=\"${value}\" "
		if (readonly) {
			input +=  "readonly=\"readonly\" "
		}
		input += "/>"

		out.println input
		
		out.println "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" />"
		out.println "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" />"
		out.println "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" />"

		// Code to parse selected date into hidden fields
		out.println "<script type=\"text/javascript\"> \$(document).ready(function(){"
		out.println "\$(\"#${name}\").datepicker({dateFormat: 'dd-mm-yy', "
		out.println "changeYear: true, "
		out.println "changeMonth: true, "
		out.println "onClose: function(dateText, inst) {"
  		out.println "\$(\"#${name}_month\").attr(\"value\",new Date(dateText).getMonth() +1);"
		out.println "\$(\"#${name}_day\").attr(\"value\",new Date(dateText).getDate());"
		out.println "\$(\"#${name}_year\").attr(\"value\",new Date(dateText).getFullYear());"
		out.println "}"
		out.println "});"
		out.println "})</script>"
	}
}
